package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.util.codec.binary.Base64;

import entity.User;
import sql.connection.DBConnection;


public class UserDAO {
	
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
	
	public User getUserByEmailAndPassword(String email, String password) throws SQLException {
		
		 // Hash the password before checking in the database
       String hashedPassword = convertToSHA1(password);

		// Check if the user exists and the password is correct
		Connection connection = DBConnection.makeConnection();
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";

		PreparedStatement preStmt = connection.prepareStatement(query);
		preStmt.setString(1, email);
		preStmt.setString(2, hashedPassword);

		ResultSet resultSet = preStmt.executeQuery();

		if (resultSet.next()) {
			String userName = resultSet.getString("user_name");
			User user = new User(userName);
			return user;
		}
		return null;
	}

	
	public void registerAccount(User user) throws SQLException {
       // Check if the user already exists
       if (checkExistUserByEmail(user.getEmail())) {
           System.out.println("This Account Already Exists. Please register a new one");
           return;
       }


       // Hash the password before storing it in the database
       String hashedPassword = convertToSHA1(user.getPassword());
       user.setPassword(hashedPassword);
       
       Connection connection = DBConnection.makeConnection();
       String query = "INSERT INTO `user` (`first_name`, `last_name`, `email`, `phoneNo`, `user_name`, `password`) VALUES (?,?,?,?,?,?)";

       try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
           insertStatement.setString(1, user.getFirstName());
           insertStatement.setString(2, user.getLastName());
           insertStatement.setString(3, user.getEmail());
           insertStatement.setString(4, user.getPhoneNo());
           insertStatement.setString(5, user.getUserName());
           insertStatement.setString(6, user.getPassword());

           insertStatement.executeUpdate();
       }
   }
	
	public boolean checkExistUserByEmail(String email) throws SQLException {
	    Connection connection = DBConnection.makeConnection();
	    String checkQuery = "SELECT * FROM user WHERE email = ?";

	    try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
	        checkStatement.setString(1, email);
	        return checkStatement.executeQuery().next();
	    }

}

}
