package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import sql.connection.DBConnection;

public class CategoryDAO {
	public List<Category> getAllCategories() throws SQLException {

		// connect to MYSQL SERVER
		Connection connection = DBConnection.makeConnection();

		// Create object statement to execute SQL QUERY
		Statement stmt = connection.createStatement();
		String sqlQuery = "SELECT * FROM category ORDER BY priority;";

		// execute SQL
		ResultSet resultSet = stmt.executeQuery(sqlQuery);

		// return RESULT SET

		// READ ROW BY ROW
		List<Category> list = new ArrayList<Category>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int priority = resultSet.getInt("priority");
			Category category = new Category(id, name, priority);
			list.add(category);
		}
		return list;
	}

}
