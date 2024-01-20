package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import sql.connection.DBConnection;

public class ProductDAO {

	public List<Product> getLastestProducts() throws SQLException {

		// connect to MYSQL SERVER
		Connection connection = DBConnection.makeConnection();

		// Create object statement to execute SQL QUERY
		Statement stmt = connection.createStatement();
		String sqlQuery = "SELECT * FROM product WHERE is_new = 1";

		// execute SQL
		ResultSet resultSet = stmt.executeQuery(sqlQuery);

		// return RESULT SET

		// READ ROW BY ROW
		List<Product> list = new ArrayList<Product>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean is_new = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getNString("description");

			Product product = new Product(id, name, price, imgName, is_new, quantity, description);
			list.add(product);
		}
		return list;
	}

	public static Product getProductById(int productId) throws SQLException {

		Connection connection = DBConnection.makeConnection();
		String sqlQuery = "SELECT * FROM product WHERE id =?";
		
		// Convert the String productId to an int before setting it in the PreparedStatement
	    //int productIdInt = Integer.parseInt(productId);

		PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
		preStmt.setInt(1, productId);
		ResultSet resultSet = preStmt.executeQuery();

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean is_new = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getNString("description");

			Product product = new Product(id, name, price, imgName, is_new, quantity, description);
			return product;
		}
		return null;
	}

	public List<Product> showAllProducts() throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();

		String showProductSQL = "select * from product";
		ResultSet resultSet = stmt.executeQuery(showProductSQL);

		List<Product> list = new ArrayList<Product>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean is_new = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getNString("description");

			Product product = new Product(id, name, price, imgName, is_new, quantity, description);
			list.add(product);
		}
		return list;
	}

	public static List<Product> getProductBySearch(String productName) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		String sqlQuery = "SELECT * FROM product WHERE name LIKE ?";

		PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
		preStmt.setString(1, "%" + productName + "%");
		ResultSet resultSet = preStmt.executeQuery();

		List<Product> list = new ArrayList<Product>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean is_new = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getNString("description");

			Product product = new Product(id, name, price, imgName, is_new, quantity, description);
			list.add(product);
		}
		return list;

	}
}
