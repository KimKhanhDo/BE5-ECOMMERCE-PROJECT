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
 * Servlet implementation class RegisterServlet2
 */
@WebServlet("/Register")
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
		
		//doGet(request, response);
		
		// Register Account -->  Read data input from user
	
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNo = request.getParameter("phoneNo");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(firstName, lastName, email, phoneNo, username, password);

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


