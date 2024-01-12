package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ProductDAO;
import entity.Category;
import entity.Product;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
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

			String categoryId = request.getParameter("categoryId");
			String action = request.getParameter("action");
			String productName = request.getParameter("searchValue");

			// USE MODEL to get PRODUCT LIST (DATA), CATEGORY LIST
			ProductDAO productDao = new ProductDAO();
			CategoryDAO categoryDao = new CategoryDAO();

			List<Product> products;

			if ("SHOW_ALL".equals(action)) {
				products = productDao.showAllProducts();
			} else if (categoryId != null) {
				products = categoryDao.getProductByCategoryId(categoryId);
			} else if (productName != null) {
				products = ProductDAO.getProductBySearch(productName);
			} else {
				products = productDao.getLastestProducts();
			}

			List<Category> categories = categoryDao.getAllCategories();

			// send DATA TO VIEW
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("products", products);
			request.setAttribute("categories", categories);
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
