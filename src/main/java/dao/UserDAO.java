package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;

import entity.User;
import sql.connection.DBConnection;

public class UserDAO {

	// SHA1
	public String convertToSHA1(String userPassword) {
		String salt = "asb@h;ds$vdghy?";
		String result = null;

		userPassword = userPassword + salt;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(messageDigest.digest(userPassword.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// SHA2
	public String convertToSHA2(String userPassword) {
		String salt = "asb@h;ds$vdghy?";
		String result = null;

		userPassword = userPassword + salt;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash = messageDigest.digest(userPassword.getBytes());
			result = Base64.encodeBase64String(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// BCrypt
	private String hashPassword(String password) {
		// Generate a BCrypt hash for the password
		 int costParameter = 10;
		return BCrypt.hashpw(password, BCrypt.gensalt(costParameter));
	}

	public User getUserByEmailAndPassword(String email, String password) throws SQLException {

		// Hash the password using BCrypt before checking in the database
		String hashedPassword = hashPassword(password);

		// Check if the user exists and the password is correct
		Connection connection = DBConnection.makeConnection();
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";

		try (PreparedStatement preStmt = connection.prepareStatement(query)) {
			preStmt.setString(1, email);
			preStmt.setString(2, hashedPassword);

			try (ResultSet resultSet = preStmt.executeQuery()) {
				if (resultSet.next()) {
					String userName = resultSet.getString("user_name");
					User user = new User(userName);
					return user;
				}
			}
		} catch (SQLException e) {
			// Handle SQLException appropriately
			e.printStackTrace();
		}

		return null;
	}

	public boolean checkExistUserByEmail(String email) throws SQLException {

		Connection connection = DBConnection.makeConnection();
		String checkQuery = "SELECT * FROM user WHERE email = ?";

		try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
			checkStatement.setString(1, email);
			return checkStatement.executeQuery().next();
		}

	}

	public void registerAccount(User user) throws SQLException {

		// Check if the user already exists
		if (checkExistUserByEmail(user.getEmail())) {
			System.out.println("This Account Already Exists. Please register a new one");
			return;
		}

		// Hash the password before storing it in the database
		String hashedPassword = hashPassword(user.getPassword());
		user.setPassword(hashedPassword);

		Connection connection = DBConnection.makeConnection();
		String query = "INSERT INTO `user` (`user_name`, `password`, `full_name`, `email`, `gender`, `hobbies`) VALUES (?,?,?,?,?,?)";

		try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
			insertStatement.setString(1, user.getUserName());
			insertStatement.setString(2, user.getPassword());
			insertStatement.setString(3, user.getFullName());
			insertStatement.setString(4, user.getEmail());
			insertStatement.setString(5, user.getGender());

			// Check if hobbies is null before joining
			if (user.getHobbies() != null) {
				// Assuming hobbies is stored as a comma-separated string in the database
				insertStatement.setString(6, String.join(",", user.getHobbies()));
			} else {
				insertStatement.setNull(6, Types.VARCHAR);
			}

			insertStatement.executeUpdate();
		}
	}

}
