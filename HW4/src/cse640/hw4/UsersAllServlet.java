package cse640.hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cse640.project.controllers.ConnectionPool;

/**
 * Servlet implementation class UsersAllServlet
 */
@WebServlet("/UsersAllServlet")
public class UsersAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersAllServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "Users.jsp";
		String allUsers = "select * from users";
		ResultSet resultSet = null;
		Statement statement = null;
		List<Users> userList = new ArrayList<Users>();
		
		try {
			
        	ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
        	connection = pool.getConnection();
        	
        	if(connection != null) {
        		System.out.println("Connection is established");
        		System.out.println("Running SQL Statement: " + allUsers);
        		
        		statement = connection.createStatement();
        		resultSet = statement.executeQuery(allUsers);
        		
        		while(resultSet.next()) {
        			Users newUser = new Users(resultSet.getString(1));
        			newUser.setfname(resultSet.getString(3));
        			newUser.setmname(resultSet.getString(5));
        			newUser.setlname(resultSet.getString(4));
        			userList.add(newUser);
        		}
        		
        		request.setAttribute("userList", userList);
        		
        		pool.freeConnection(connection);
        	} else {
        		System.out.println("Connection could not be established");
        	}
		} catch(Exception e) {
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
