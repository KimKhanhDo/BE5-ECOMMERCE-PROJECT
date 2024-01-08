package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import sql.connection.DBConnection;

public class UserService {

	public User getUserByLogin(String email, String password) throws SQLException {

		// Check if the user exists and the password is correct
		Connection connection = DBConnection.makeConnection();
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";

		PreparedStatement preStmt = connection.prepareStatement(query);
		preStmt.setString(1, email);
		preStmt.setString(2, password);

		ResultSet resultSet = preStmt.executeQuery();
		// System.out.println(preStmt);

		if (resultSet.next()) {
			String userName = resultSet.getString("user_name");
			User user = new User(userName);
			// System.out.println(user.toString());
			return user;
		}
		return null;
	}


	public boolean registerAccount(User user) throws SQLException {

		Connection connection = DBConnection.makeConnection();
		String query = "INSERT INTO `user` (`first_name`, `last_name`, `email`, `phoneNo`, `user_name`, `password`) VALUES (?,?,?,?,?,?)";

		PreparedStatement insertStatement = connection.prepareStatement(query);
		insertStatement.setString(1, user.getFirstName());
		insertStatement.setString(2, user.getLastName());
		insertStatement.setString(3, user.getEmail());
		insertStatement.setInt(4, user.getPhoneNo());
		insertStatement.setString(5, user.getUserName());
		insertStatement.setString(6, user.getPassword());

		insertStatement.executeUpdate();

		if (insertStatement != null) {
			insertStatement.close();
		}
		return true;
	}
	
	public void registerAccount2(User user) throws SQLException {
        // Check if the user already exists
        if (checkExistUserByEmail(user.getEmail())) {
            System.out.println("This Account Already Exists. Please register a new one");
            return;
        }

        Connection connection = DBConnection.makeConnection();
        String query = "INSERT INTO `user` (`first_name`, `last_name`, `email`, `phoneNo`, `user_name`, `password`) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(query)) {
            insertStatement.setString(1, user.getFirstName());
            insertStatement.setString(2, user.getLastName());
            insertStatement.setString(3, user.getEmail());
            insertStatement.setInt(4, user.getPhoneNo());
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

