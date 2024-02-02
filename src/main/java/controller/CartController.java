package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDAO;
import dao.ProductDAO;
import entity.Cart;
import entity.Category;
import entity.Product;
import model.ProductInCart;

/**
 * Servlet implementation class CartController2
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("ACTION");

			if (action == null) {
				action = "DEFAULT";
			}
			switch (action) {
			case "ADD_TO_CART": {
				addToCart(request, response);
				break;
			}
			case "VIEW_CART": {
				showCartDetail(request, response);
				break;
			}
			case "REMOVE": {
				removeItemFromCart(request, response);
				break;
			}
			default:
				break;
			}

		} catch (NumberFormatException | ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		String productId = request.getParameter("productId");

		Cart cart;

		HttpSession session = request.getSession();

		if (session.getAttribute("cart") == null) {
			cart = new Cart();
			cart.setItems(new HashSet<ProductInCart>());
		} else {
			cart = (Cart) session.getAttribute("cart");
		}

		// Check Object PRODUCT (retrieve from productID) exist in cart
		Product product = ProductDAO.getProductById(Integer.parseInt(productId));
		ProductInCart productInCart = new ProductInCart(product.getId(), product.getImgName(), product.getName(),
				product.getPrice(), product.getPrice(), 1);

		// If the product is already in the cart, update its quantity
		if (cart.getItems().contains(productInCart)) {
			for (ProductInCart item : cart.getItems()) {
				if (item.getId() == productInCart.getId()) {
					productInCart.setQuantity(item.getQuantity() + 1);
					productInCart.setSubTotal(productInCart.getQuantity() * productInCart.getPrice());
				}
			}
			cart.getItems().remove(productInCart);
			cart.getItems().add(productInCart);

		} else {
			// If the product is not in the cart, add it with quantity 1
			cart.getItems().add(productInCart);
		}
		cart.setTotal(cart.getTotal() + product.getPrice());

		session.setAttribute("cart", cart);

		response.sendRedirect("ProductDetail?productId=" + productId);
	}

	public void showCartDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		CategoryDAO categoryDao = new CategoryDAO();
		List<Category> categories = categoryDao.getAllCategories();

		RequestDispatcher rd = request.getRequestDispatcher("/shopping-cart.jsp");
		request.setAttribute("categories", categories);
		rd.forward(request, response);

	}

	public void removeItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String productId = request.getParameter("productId");
		HttpSession session = request.getSession();

		Cart productInCart = (Cart) session.getAttribute("cart");
		Product product = ProductDAO.getProductById(Integer.parseInt(productId));

		if (productInCart != null) {
			// Get the existing item from the set
			ProductInCart existingItem = null;
			for (ProductInCart item : productInCart.getItems()) {
				if (item.getId() == product.getId()) {
					existingItem = item;
					break;
				}
			}

			if (existingItem != null) {
				// Decrease the quantity by 1
				int currentQuantity = existingItem.getQuantity();
				if (currentQuantity > 1) {
					existingItem.setQuantity(currentQuantity - 1);
					existingItem.setSubTotal(existingItem.getQuantity() * existingItem.getPrice());
				} else {
					// If the quantity is 1, remove the product from the cart
					productInCart.getItems().remove(existingItem);
				}

				// Update the total in the cart
				productInCart.setTotal(productInCart.getTotal() - existingItem.getPrice());

				session.setAttribute("cart", productInCart);
			}
		}

		// Redirect to the showCartDetail method --> forward to "/cart-set.jsp"
		showCartDetail(request, response);
	}
	
//	 public void checkOut(HttpServletRequest request, HttpServletResponse response)
//	            throws ServletException, IOException, SQLException {
//
//	        HttpSession session = request.getSession();
//	        CartSet productInCart = (CartSet) session.getAttribute("cart");
//
//	     // Get userId from session
//	        Integer userId = null;
//	        User user = (User) session.getAttribute("user");
//	        if (user != null) {
//	            userId = user.getId();
//	        } else {
//	            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//	            request.setAttribute("errorMessage", "Please sign in to continue to checkout");
//	            rd.forward(request, response);
//	            return; 
//	        }
//
//	        Order order = new Order();
//	        order.setUserId(userId);
//	        order.setTotalPrice(productInCart.getTotal());
//	        order.setDateOfOrder(new Date());
//
//	        // Insert the new order into the database
//	        OrderDAO orderDAO = new OrderDAO();
//	        orderDAO.createNewOrder(order);
//
//	        
//
//	        // Loop over items in the cart & insert each as an order detail
//	        for (ProductInCart item : productInCart.getItems()) {
//	            OrderDetail orderDetail = new OrderDetail();
//	            orderDetail.setProductId(item.getId());
//	            orderDetail.setQuantity(item.getQuantity());
//	            orderDetail.setPrice(item.getPrice());
//
//	            // Insert the order detail into the database
//	            orderDAO.createNewOrderDetail(orderDetail);
//	        }
//	        
//			RequestDispatcher rd = request.getRequestDispatcher("check-out.jsp");
//		    rd.forward(request, response);
//	    }
	  
}




