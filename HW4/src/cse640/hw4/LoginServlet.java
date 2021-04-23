package cse640.hw4;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cse640.project.controllers.UsersController;
import cse640.project.controllers.ConnectionPool;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long 			serialVersionUID	= 1L;
    private static Connection			connection			= null;
    private UsersController             uc					= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
        String url = "/AlbumServlet";
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        
        try {
        	ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
        	connection = pool.getConnection();
        	
        	if(connection != null) {
        		System.out.println("Connection is established");
        		uc = new UsersController(connection);
        		if (uc.findUser(user, password) == true) {
        			session.setAttribute("username", user);
        		} else {
        			url = "/Login.jsp";
        			request.setAttribute("error", "Incorrect password or user does not exist");
        		}
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
}
