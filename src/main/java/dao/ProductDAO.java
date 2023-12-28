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
			boolean isNew = resultSet.getBoolean("is_new");
			Product product = new Product(id, name, price, imgName, isNew);
			list.add(product);
		}
		return list; 
	}
	
	public static Product getProductById(String productId) throws SQLException {
		
		Connection connection = DBConnection.makeConnection();
		
		String sqlQuery = "SELECT * FROM product WHERE id =?";
		
		PreparedStatement stmt = connection.prepareStatement(sqlQuery);
		stmt.setString(1, productId);
		ResultSet resultSet = stmt.executeQuery();
		
		 Product product = null;
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("img_name");
			boolean isNew = resultSet.getBoolean("is_new");
			int quantity = resultSet.getInt("quantity");
			String description = resultSet.getNString("description");
			
			product = new Product(id, name, price, imgName, isNew, quantity, description);
					
		}
		return product;
	}

}
