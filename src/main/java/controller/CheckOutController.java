package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import entity.Cart;
import entity.Order;
import entity.OrderDetail;
import entity.User;
import model.ProductInCart;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String action = request.getParameter("action");

			if (action == null) {
				action = "DEFAULT";
			}
			switch (action) {
			case "CHECK_OUT": {
				submitCart(request, response);
				break;
			}
			default:
				break;
			}

		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	public void submitCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		Cart productInCart = (Cart) session.getAttribute("cart");

		int userId = user.getId();
		Date submitDay = new Date(System.currentTimeMillis());
		double totalPrice = productInCart.getTotal();

		Order order = new Order(userId, submitDay, totalPrice);

		OrderDAO orderDAO = new OrderDAO();
		int orderId = orderDAO.createNewOrder(order);

		for (ProductInCart item : productInCart.getItems()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(orderId);
			orderDetail.setProductId(item.getId());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setPrice(item.getPrice());
			orderDetail.setSubTotal(item.getSubTotal());

			// Insert order detail into the database
			orderDAO.createNewOrderDetail(orderDetail);
		}
		request.setAttribute("orderId", orderId);
		request.setAttribute("totalPrice", totalPrice);
		showOrderDetail(request, response);
	}
	
	public void showOrderDetail(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		 int orderId = (int) request.getAttribute("orderId");	
		 
		 OrderDAO orderDAO = new OrderDAO();
		 List<OrderDetail> orderDetail = orderDAO.getOrderDetailsById(orderId);
		 
		 request.setAttribute("orderDetail", orderDetail);
		  request.setAttribute("orderId", orderId);
		 request.setAttribute("totalPrice", request.getAttribute("totalPrice"));
		 
		 RequestDispatcher rd = request.getRequestDispatcher("order-info.jsp");
		 
		 rd.forward(request, response);
		 
	}
	

}
