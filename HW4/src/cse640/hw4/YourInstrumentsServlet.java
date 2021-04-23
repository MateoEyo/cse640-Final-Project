package cse640.hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cse640.project.controllers.ConnectionPool;

/**
 * Servlet implementation class YourInstrumentsServlet
 */
@WebServlet("/YourInstrumentsServlet")
public class YourInstrumentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourInstrumentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("*** ENTERING LIST YOUR INSTRUMENTS CODE ***");
		
		String url = "YourInstruments.jsp";
		ResultSet resultSet = null;
		Statement statement = null;
		List<Instrument> instrumentList = new ArrayList<Instrument>();
		HttpSession session = request.getSession(true);
		String user = (String) session.getAttribute("username");
		String getInstruments = 	"select instrument.name, instrument.category from instrument " +
									"inner join instrumentowner on INSTRUMENT.name = instrumentowner.name " +
									"where instrumentowner.id='" + user + "'";
		
		try {
			
        	ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
        	connection = pool.getConnection();
        	
        	if(connection != null) {
        		System.out.println("Connection is established");
        		System.out.println("Running SQL Statement: " + getInstruments);
        		
        		statement = connection.createStatement();
        		resultSet = statement.executeQuery(getInstruments);
        		
        		while(resultSet.next()) {
        			Instrument newInstrument = new Instrument(resultSet.getString(1));
        			newInstrument.setCategory(resultSet.getString(2));
        			instrumentList.add(newInstrument);
        		}
        		
        		request.setAttribute("instrumentList", instrumentList);
        		
        		pool.freeConnection(connection);
        	} else {
        		System.out.println("Connection could not be established");
        	}
        
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("*** EXITING LIST YOUR INSTRUMENTS CODE ***");
		
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
