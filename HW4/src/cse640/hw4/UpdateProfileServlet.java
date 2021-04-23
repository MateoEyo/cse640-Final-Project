package cse640.hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cse640.project.controllers.ConnectionPool;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
		System.out.println("*** UPDATE PROFILE SERVLET STARTING ***");
		
		String url = "/ProfileServlet";
		HttpSession session = request.getSession(true);
		
		String user = (String) session.getAttribute("username");
		String password = request.getParameter("password");
		String fname = request.getParameter("fname");
		String mname = request.getParameter("mname");
		String lname = request.getParameter("lname");
		
		String checkTemplate = 	"update users " +
								"set password = ?, fname = ?, lname = ?, mname = ? " +
								"where id = '" + user + "'";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
	    		PreparedStatement ps = connection.prepareStatement(checkTemplate);
	    		ps.setString(1, password);
	    		ps.setString(2, fname);
	    		ps.setString(3, lname);
	    		ps.setString(4, mname);
	    		System.out.println("Execute SQL statement: " + ps.toString());
	    		ps.executeUpdate();
	    	}
	    	pool.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

}
