package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDAO;
import entity.Category;
import entity.ProductInCart;

/**
 * Servlet implementation class CartController
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

		HttpSession session = request.getSession();

		// Retrieve shoppingCartItems from the session
		@SuppressWarnings("unchecked")
		HashMap<Integer, ProductInCart> shoppingCartItems = (HashMap<Integer, ProductInCart>) session
				.getAttribute("cart");

		if (shoppingCartItems == null) {
			shoppingCartItems = new HashMap<>();
		}

		// If the product is already in the cart, update its quantity
		if (shoppingCartItems.containsKey(Integer.parseInt(productId))) {
			ProductInCart productInCart = shoppingCartItems.get(Integer.parseInt(productId));
			productInCart.setQuantity(productInCart.getQuantity() + 1);
		} else {
			// If the product is not in the cart, add it with quantity 1
			ProductInCart productInCart = new ProductInCart();
			productInCart.setQuantity(1);
			shoppingCartItems.put(Integer.parseInt(productId), productInCart);
		}

		// Print the quantity of each product to the console
		System.out.println("Quantity of each product in the cart:");
		for (Map.Entry<Integer, ProductInCart> item : shoppingCartItems.entrySet()) {
			System.out.println("Product ID: " + item.getKey() + ", Quantity: " + item.getValue().getQuantity());
		}

		int totalDistinctProducts = shoppingCartItems.size();

		session.setAttribute("cart", shoppingCartItems);

		session.setAttribute("totalDistinctProducts", totalDistinctProducts);

		response.sendRedirect("ProductDetail?productId=" + productId);
	}
	

	public void showCartDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		
		CategoryDAO categoryDao = new CategoryDAO();
		List<Category> categories;
		
		  HttpSession session = request.getSession();
		  
		    @SuppressWarnings("unchecked")
			HashMap<Integer, ProductInCart> shoppingCartItems = (HashMap<Integer, ProductInCart>) session
		            .getAttribute("cart");

		    
		    categories = categoryDao.getAllCategories();
		    request.setAttribute("categories", categories);

		    
		    RequestDispatcher rd = request.getRequestDispatcher("/shopping-cart.jsp");
		    request.setAttribute("cart", shoppingCartItems);
		    rd.forward(request, response);
	}


}
