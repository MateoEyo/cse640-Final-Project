package cse640.hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cse640.project.controllers.ConnectionPool;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("*** STARTING ADD USER CODE ***");
		
		String url = "AlbumServlet";
		String user = request.getParameter("newUser");
		String fname = request.getParameter("fName");
		String mname = request.getParameter("mName");
		String lname = request.getParameter("lName");
		String password = request.getParameter("newPassword");
		String confirm = request.getParameter("confirmPassword");
		HttpSession session = request.getSession(true);
		
		if(!password.equals(confirm)) {
			url = "Login.jsp";
			request.setAttribute("error", "Passwords did not match");
		} else {
			boolean result = addUser(user, password, fname, mname, lname);
			if(!result) {
				url = "Login.jsp";
				request.setAttribute("error", "Username is taken");
			} else {
				session.setAttribute("username", user);
			}
		}
		
		System.out.println("*** ENDING ADD USER CODE ***");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

	/*
	 * Add new user to database
	 */
	private boolean addUser(String user, String password, String fname, String mname, String lname) {
		
		boolean doesUsernameExist = checkIfUserExists(user);
		
		if(doesUsernameExist) {
			return false;
		} else {
			insertIntoUsers(user, password, fname, mname, lname);
		}
		
		return true;
	}
	
	/*
	 * Check if username is already taken
	 */
	private boolean checkIfUserExists(String user) {
		
		ResultSet resultSet = null;
		Statement statement = null;
		String checkTemplate = "select ID from users where ID='" + user + "'";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
	    		System.out.println("Checking if Username exists");
	    		
	    		statement = connection.createStatement();
	    		resultSet = statement.executeQuery(checkTemplate);
	    		
	    		if(!resultSet.next()) {
	    			return false;
	    		} else {
	    			return true;
	    		}
	    	} else {
	    		System.out.println("Connection could not be established");
	    	}
	    	pool.freeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/*
	 * Insert newly created user in table
	 */
	private void insertIntoUsers(String user, String password, String fname, String mname, String lname) {
		
		String checkTemplate = "insert into users values(?, ?, ?, ?, ?)";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
	    		PreparedStatement ps = connection.prepareStatement(checkTemplate);
	    		ps.setString(1, user);
	    		ps.setString(2, password);
	    		ps.setString(3, fname);
	    		ps.setString(4, lname);
	    		ps.setString(5, mname);
	    		System.out.println("Insert into users table");
	    		ps.executeUpdate();
	    	}
	    	pool.freeConnection(connection);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
