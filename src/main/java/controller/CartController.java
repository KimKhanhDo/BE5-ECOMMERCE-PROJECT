package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
			cart.setItems(new HashMap<Product, Integer>());
		} else {
			cart = (Cart) session.getAttribute("cart");
		}

		// Check Object PRODUCT (retrieve from productID) exist in cart
		Product product = ProductDAO.getProductById(Integer.parseInt(productId));

		// If the product is already in the cart (Map items), update its quantity
		if (cart.getItems().containsKey(product)) {
			int newQuantity = cart.getItems().get(product) + 1;
			cart.getItems().put(product, newQuantity);
		} else {
			// If the product is not in the cart, add it with quantity 1
			cart.getItems().put(product, 1);
		}

		session.setAttribute("cart", cart);

		// Print the quantity of each product to the console
		System.out.println("Quantity of each product in the cart:");
		for (Entry<Product, Integer> entry : cart.getItems().entrySet()) {
			System.out.println("Product ID: " + entry.getKey().getId() + ", Quantity: " + entry.getValue() + ", Price: "
					+ entry.getKey().getPrice());
		}

		response.sendRedirect("ProductDetail?productId=" + productId);
	}

	public void showCartDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		CategoryDAO categoryDao = new CategoryDAO();
		List<Category> categories = categoryDao.getAllCategories();

		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		RequestDispatcher rd = request.getRequestDispatcher("/shopping-cart.jsp");
		request.setAttribute("cart", cart);
		request.setAttribute("categories", categories);
		rd.forward(request, response);

	}
	
	public void removeItemFromCart(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {

	    String productId = request.getParameter("productId");
	    HttpSession session = request.getSession();

	    Cart cart = (Cart) session.getAttribute("cart");
	    Product product = ProductDAO.getProductById(Integer.parseInt(productId));
	    
	    if (cart.getItems().containsKey(product)) {
	        // Decrease the quantity by 1
	        int currentQuantity = cart.getItems().get(product);
	        if (currentQuantity > 1) {
	            cart.getItems().put(product, currentQuantity - 1);
	        } else {
	            // If the quantity is 1, remove the product from the cart
	            cart.getItems().remove(product);
	        }
	        session.setAttribute("cart", cart);
	    }

	    // Redirect to the showCartDetail method --> forward to "/shopping-cart.jsp"
	    showCartDetail(request, response);
	}


}
