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
 * Servlet implementation class AddInstrumentServlet
 */
@WebServlet("/AddInstrumentServlet")
public class AddInstrumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInstrumentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("*** STARTING ADD INSTRUMENT CODE ***");
		
		HttpSession session = request.getSession(true);
		
		String url = "/YourInstrumentsServlet";
		String instrumentName = request.getParameter("instrumentname");
		String category = request.getParameter("category");
		String user = (String) session.getAttribute("username");
		
		boolean result = addInstrument(instrumentName, category, user);
		if(result) {
			request.setAttribute("success", "Successfully added!");
		} else {
			request.setAttribute("didNotAdd", "You already own this instrument.");
		}
		
		System.out.println("*** ENDING ADD INSTRUMENT CODE ***");
		
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

	/*
	 * ************ FUNCTIONS BELOW ************
	 */
	
	/*
	 * Adds instrument to database
	 */
	private boolean addInstrument(String name, String category, String user) {
		
		boolean doesInstrumentExist = checkIfInstrumentExists(name);
		boolean doesUserOwnInstrument = checkIfUserOwnsInstrument(name, user);
		
		if(doesInstrumentExist && doesUserOwnInstrument) {
			return false;
		} else if(doesInstrumentExist && !doesUserOwnInstrument) {
			insertIntoInstrumentOwner(name, user);
		} else {
			insertIntoInstrument(name, category);
			insertIntoInstrumentOwner(name, user);
		}
		return true;
	}
	
	/*
	 * Checks if instrument is in instrument database
	 */
	private boolean checkIfInstrumentExists(String name) {
		
		ResultSet resultSet = null;
		Statement statement = null;
		String checkTemplate = "select name from instrument where name='" + name + "'";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01");
			connection = pool.getConnection();
			
			if (connection != null) {
				System.out.println("Connection is established");
				System.out.println("Checking if Instrument exists");
				
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/*
	 * Checks if user already has instrument
	 */
	private boolean checkIfUserOwnsInstrument(String name, String user) {
		
		ResultSet resultSet = null;
		String checkTemplate = "select * from INSTRUMENTOWNER where name = ? and ID = ?";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATIO1");
			connection = pool.getConnection();
			
			if(connection != null) {
				System.out.println("Connection is established");
				
				PreparedStatement ps = connection.prepareStatement(checkTemplate);
				ps.setString(1, name);
				ps.setString(2, user);
				System.out.println("Checking if user owns instrument");
				resultSet = ps.executeQuery();
				
				if(!resultSet.next()) {
					return false;
				} else {
					return true;
				}
			} else {
				System.out.println("Connection could not be established");
			}
			pool.freeConnection(connection);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/*
	 * Inserts into instruments table when new instrument
	 */
	private void insertIntoInstrument(String name, String category) {
		
		String checkTemplate = "insert into instrument values(?, ?)";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
	    		PreparedStatement ps = connection.prepareStatement(checkTemplate);
	    		ps.setString(1,  name);
	    		ps.setString(2, category);
	    		System.out.println("Insert into instrument table");
	    		ps.executeUpdate();
	    	}
	    	pool.freeConnection(connection);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Inserts into instrument owner table when user adds instrument
	 */
	private void insertIntoInstrumentOwner(String name, String user) {
		
		String checkTemplate = "insert into instrumentowner values(?, ?)";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
		    	PreparedStatement ps = connection.prepareStatement(checkTemplate);
		    	ps.setString(1, user);
		    	ps.setString(2, name);
		    	System.out.println("Insert into albumowner table");
	    		ps.executeUpdate();
	    	}
	    	pool.freeConnection(connection);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
