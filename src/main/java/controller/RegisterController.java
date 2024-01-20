package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

/**
 * Servlet implementation class RegisterController2
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// This controller used to test with String[] hobbies ONLY
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
	    String[] hobbies = request.getParameterValues("hobby");
	    User user = new User(userName, password, fullName, email, gender, hobbies);

	    UserDAO userDAO = new UserDAO();

		try {
			if (userDAO.checkExistUserByEmail(email)) {
				response.sendRedirect("register.jsp?errorMessage=Email already exists. Please choose another one.");
			} else {
				userDAO.registerAccount(user);
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("register.jsp?errorMessage=Error, please try again");
		}
	}
		
	}
	


             