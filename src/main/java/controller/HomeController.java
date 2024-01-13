package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
			String searchValue = request.getParameter("searchValue");

			// USE MODEL to get PRODUCT LIST (DATA), CATEGORY LIST
			ProductDAO productDao = new ProductDAO();
			CategoryDAO categoryDao = new CategoryDAO();

			List<Product> products = new ArrayList<>();

			if (action != null) {
				switch (action) {
				case "SHOW_ALL":
					products = productDao.showAllProducts();
					break;
				case "CATEGORY":
					if (categoryId != null) {
						products = categoryDao.getProductByCategoryId(categoryId);
					}
					break;
				case "SEARCH":
					if (searchValue != null) {
						products = ProductDAO.getProductBySearch(searchValue);
					}
					break;
				}
				// Showing latest products if no specific action is provided
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
