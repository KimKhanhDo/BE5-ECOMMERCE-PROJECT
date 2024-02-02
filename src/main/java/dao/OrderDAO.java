package dao;

import sql.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.OrderDetail;

public class OrderDAO {

	public int createNewOrder(Order order) throws SQLException {

		Connection connection = DBConnection.makeConnection();
		String query = "INSERT INTO `order` (`user_id`, `submit_day`, `total`) VALUES (?, ?, ?)";

		PreparedStatement preStm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preStm.setInt(1, order.getUserId());
		preStm.setDate(2, new Date(System.currentTimeMillis()));
		preStm.setDouble(3, order.getTotal());

		preStm.executeUpdate();
		ResultSet resultSet = preStm.getGeneratedKeys();
		if (resultSet.next()) {
			int orderId = resultSet.getInt(1);
			return orderId;
		}
		return 0;
	}

	public void createNewOrderDetail(OrderDetail orderDetail) throws SQLException {

		Connection connection = DBConnection.makeConnection();
		String query = "INSERT INTO `order-details` (`order_id`, `product_id`, `quantity`, `price`, `subTotal`) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement preStm = connection.prepareStatement(query);
			preStm.setInt(1, orderDetail.getOrderId());
			preStm.setInt(2, orderDetail.getProductId());
			preStm.setInt(3, orderDetail.getQuantity());
			preStm.setDouble(4, orderDetail.getPrice());
			preStm.setDouble(5, orderDetail.getSubTotal());
			preStm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Order> getOrdersByUserId(int userId) throws SQLException {

		List<Order> orderList = new ArrayList<>();

		Connection connection = DBConnection.makeConnection();
		String query = "SELECT * FROM order WHERE user_id = ?";

		PreparedStatement preStm = connection.prepareStatement(query);
		preStm.setInt(1, userId);

		ResultSet resultSet = preStm.executeQuery();
		while (resultSet.next()) {
			int orderId = resultSet.getInt("id");
			Date submitDay = resultSet.getDate("submit_day");
			double total = resultSet.getDouble("total");

			Order order = new Order(orderId, userId, submitDay, total);
			orderList.add(order);
		}
		return orderList;
	}

	public List<OrderDetail> getOrderDetailsById(int orderId) throws SQLException {

		List<OrderDetail> list = new ArrayList<>();

		Connection connection = DBConnection.makeConnection();
		String query = "SELECT * FROM `order-details` WHERE order_id = ?";

		PreparedStatement preStm = connection.prepareStatement(query);
		preStm.setInt(1, orderId);

		ResultSet resultSet = preStm.executeQuery();
		while (resultSet.next()) {
			int orderDetailId = resultSet.getInt("id");
			int productId = resultSet.getInt("product_id");
			int quantity = resultSet.getInt("quantity");
			double price = resultSet.getDouble("price");
			double subTotal = resultSet.getDouble("subTotal");

			OrderDetail orderDetail = new OrderDetail(orderDetailId, orderId, productId, quantity, price, subTotal);
			list.add(orderDetail);
		}
		return list;

	}

}
