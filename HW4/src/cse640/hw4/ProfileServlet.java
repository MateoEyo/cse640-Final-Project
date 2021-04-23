package cse640.hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("*** ENTERING PROFILE CODE ***");
		
		String url = "Profile.jsp";
		ResultSet resultSet = null;
		Statement statement = null;
		HttpSession session = request.getSession(true);
		String user = (String) session.getAttribute("username");
		String getUser = "select * from users where users.id='" + user + "'";
		Users userProfile = null;
		
		try {
			
        	ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
        	connection = pool.getConnection();
        	
        	if(connection != null) {
        		System.out.println("Connection is established");
        		System.out.println("Running SQL Statement: " + getUser);
        		
        		statement = connection.createStatement();
        		resultSet = statement.executeQuery(getUser);
        		while(resultSet.next()) {
        			userProfile = new Users(resultSet.getString(1));
        			userProfile.setPassword(resultSet.getString(2));
        			userProfile.setfname(resultSet.getString(3));
        			userProfile.setlname(resultSet.getString(4));
        			userProfile.setmname(resultSet.getString(5));
        		}
        		request.setAttribute("userProfile", userProfile);
        		
        		pool.freeConnection(connection);
        	} else {
        		System.out.println("Connection could not be established");
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
