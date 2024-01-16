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

	ProductDAO productDao = new ProductDAO();
	CategoryDAO categoryDao = new CategoryDAO();
	List<Product> products;
	List<Category> categories;

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
			String action = request.getParameter("action");
			if (action == null) {
				action = "DEFAULT";
			}


			switch (action) {
			case "SHOW_PRODUCT_BY_CATEGORY": {
				products = getProductByCategory(request, response);
				break;
			}
			case "SHOW_ALL": {
				products = getAllProducts(request, response);
				break;
			}
			case "SEARCH": {
				products = getSearchedProducts(request, response);
				break;
			}
			default:
				products = getLastestHomePage(request, response);
			}
			
			categories = categoryDao.getAllCategories();
			dispatchAttributeToView(request, response);

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

	private void dispatchAttributeToView(HttpServletRequest request, HttpServletResponse respone)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		request.setAttribute("products", products);
		request.setAttribute("categories", categories);
		rd.forward(request, respone);
	}

	private List<Product> getProductByCategory(HttpServletRequest request, HttpServletResponse respone)
			throws SQLException, ServletException, IOException {

		String categoryId = request.getParameter("categoryId");
		return categoryDao.getProductByCategoryId(categoryId);
	}

	private List<Product> getAllProducts(HttpServletRequest request, HttpServletResponse respone)
			throws SQLException, ServletException, IOException {

		return productDao.showAllProducts();
	}

	private List<Product> getSearchedProducts(HttpServletRequest request, HttpServletResponse respone)
			throws SQLException, ServletException, IOException {

		String searchValue = request.getParameter("searchValue");
		return ProductDAO.getProductBySearch(searchValue);
	}

	private List<Product> getLastestHomePage(HttpServletRequest request, HttpServletResponse respone)
			throws SQLException, ServletException, IOException {

		return productDao.getLastestProducts();
	}

//	String categoryId = request.getParameter("categoryId");
//	String action = request.getParameter("action");
//	String searchValue = request.getParameter("searchValue");
//
//	// USE MODEL to get PRODUCT LIST (DATA), CATEGORY LIST
//	ProductDAO productDao = new ProductDAO();
//	CategoryDAO categoryDao = new CategoryDAO();
//
//	List<Product> products = new ArrayList<>();
//
//	if (action != null) {
//		switch (action) {
//		case "SHOW_ALL":
//			products = productDao.showAllProducts();
//			break;
//		case "CATEGORY":
//			if (categoryId != null) {
//				products = categoryDao.getProductByCategoryId(categoryId);
//			}
//			break;
//		case "SEARCH":
//			if (searchValue != null) {
//				products = ProductDAO.getProductBySearch(searchValue);
//			}
//			break;
//		}
//		// Showing latest products if no specific action is provided
//	} else {
//		products = productDao.getLastestProducts();
//	}
//
//	List<Category> categories = categoryDao.getAllCategories();
//
//	// send DATA TO VIEW
//	RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//	request.setAttribute("products", products);
//	request.setAttribute("categories", categories);
//	rd.forward(request, response);

}
