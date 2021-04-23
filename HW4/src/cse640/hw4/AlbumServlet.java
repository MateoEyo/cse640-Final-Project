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

@WebServlet("/AlbumServlet")
public class AlbumServlet extends HttpServlet{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;

	public AlbumServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/index.jsp";
        String allAlbums = "select * from album";
        ResultSet resultSet = null;
        Statement statement = null;
        List<Album> albumList = new ArrayList<Album>();
        
        try {
        	
        	ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
        	connection = pool.getConnection();
        	
        	if(connection != null) {
        		System.out.println("Connection is established");
        		System.out.println("Running SQL Statement: " + allAlbums);
        		
        		statement = connection.createStatement();
        		resultSet = statement.executeQuery(allAlbums);
        		
        		while(resultSet.next()) {
        			Album newAlbum = new Album(resultSet.getString(1));
        			newAlbum.setArtist(resultSet.getString(2));
        			newAlbum.setGenre(resultSet.getString(3));
        			newAlbum.setInstrument(resultSet.getString(4));
        			albumList.add(newAlbum);
        		}
        		
        		request.setAttribute("albumList", albumList);
        		
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
		doGet(request, response);
	}
}
