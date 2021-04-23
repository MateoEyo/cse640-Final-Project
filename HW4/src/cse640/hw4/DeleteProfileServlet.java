package cse640.hw4;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class DeleteProfileServlet
 */
@WebServlet("/DeleteProfileServlet")
public class DeleteProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/Login.jsp";
		Statement statement = null;
		HttpSession session = request.getSession(true);
		
		String user = (String) session.getAttribute("username");
		String deleteFromAlbum = "delete from albumowner where id='" + user + "'";
		String deletefromInstrument = "delete from instrumentowner where id='" + user + "'";
		String deleteUser = "delete from users where id='" + user + "'";
		
		System.out.println("*** DELETING ALL ENTRIES RELATED TO " + user + " ***");
		
		try {
        	ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
        	connection = pool.getConnection();
        	
        	if(connection != null) {
        		System.out.println("Connection is established");
        		
        		System.out.println("Running SQL statement: " + deleteFromAlbum);
        		System.out.println("Running SQL statement: " + deletefromInstrument);
        		System.out.println("Running SQL statement: " + deleteUser);
        		statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        		
        		connection.setAutoCommit(false);
        		statement.addBatch(deleteFromAlbum);
        		statement.addBatch(deletefromInstrument);
        		statement.addBatch(deleteUser);
        		statement.executeBatch();
        		connection.commit();
        		
        		System.out.println("User: " + user + " has been deleted");
        	} else {
        		System.out.println("Connection could not be established");
        	}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		session.invalidate();
		System.out.println("*** DELETE PROFILE SERVLET END ***");
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

}
