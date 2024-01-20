package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/Authentication")
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthenticationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Logout
		request.getSession().removeAttribute("user");
		response.sendRedirect("Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Login
		try {
			String action = request.getParameter("action");

			switch (action) {
			case "LOGIN": {
				doLogIn(request, response);
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doLogIn(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByEmailAndPassword(email, password);

		if (user == null) {
			String errorMessage = "Your username or password is incorrect, please re-enter.";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("Home");
		}
	}

//	private void doLogOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession();
//		session.removeAttribute("user");
//		response.sendRedirect("Home");
//	}
}
