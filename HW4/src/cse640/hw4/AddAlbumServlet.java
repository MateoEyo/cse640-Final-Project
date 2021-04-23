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
 * Servlet implementation class AddAlbumServlet
 */
@WebServlet("/AddAlbumServlet")
public class AddAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAlbumServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("*** STARTING ADD ALBUM CODE ***");
		
		HttpSession session = request.getSession(true);
		
		String url = "/YourAlbumsServlet";
		String albumName = request.getParameter("albumname");
		String artistName = request.getParameter("artistname");
		String genre = request.getParameter("genre");
		String media = request.getParameter("media");
		String instrument = request.getParameter("instrument");
		String user = (String) session.getAttribute("username");
		
		boolean result = addAlbum(albumName, artistName, genre, media, instrument, user);
		if(result) {
			addMusician(artistName);
			request.setAttribute("success", "Successfully added!");
		} else {
			request.setAttribute("didNotAdd", "You already own this album.");
		}
		
		System.out.println("*** ENDING ADD ALBUM CODE ***");
		
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
	 * Adds an album to the album table and owner table
	 */
	private boolean addAlbum(String albumName, String artistName, String genre, String media, String instrument, String user) {	
		
		boolean doesAlbumExist = checkIfAlbumExists(albumName);
		boolean doesUserOwnAlbum =  checkIfUserOwnsAlbum(albumName, user);
		
		if(doesAlbumExist && doesUserOwnAlbum) {
			return false;
		} else if(doesAlbumExist && !doesUserOwnAlbum) {
			insertIntoAlbumOwner(albumName, user, media);
		} else {
			insertIntoAlbum(albumName, artistName, genre, instrument);
			insertIntoAlbumOwner(albumName, user, media);
		}
		return true;
	}
	
	/*
	 * Adds a new musician to the database
	 */
	private void addMusician(String artistName) {
		boolean doesMusicianExist = checkifMusicianExists(artistName);
		if(!doesMusicianExist) {
			insertIntoMusicians(artistName);
		}
	}
	
	/*
	 * Checks if an album exists and returns true/false whether it does or not
	 */
	private boolean checkIfAlbumExists(String albumName) {
		
		ResultSet resultSet = null;
		Statement statement = null;
		String checkTemplate = "select name from album where name='" + albumName + "'";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
	    		System.out.println("Checking if Album exists");
	    		
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
	 * Checks if user owns album 
	 */
	private boolean checkIfUserOwnsAlbum(String albumName, String user) {
		
		ResultSet resultSet = null;
		String checkTemplate = "select * from ALBUMOWNER where name = ? and ID = ?";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
	    		
		    	PreparedStatement ps = connection.prepareStatement(checkTemplate);
		    	ps.setString(1, albumName);
		    	ps.setString(2,  user);
		    	System.out.println("Checking if user owns album");
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
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return true;
	}
	
	/*
	 * Checks if musician is already in database
	 */
	private boolean checkifMusicianExists(String artistName) {
		
		ResultSet resultSet = null;
		Statement statement = null;
		String checkTemplate = "select * from musicians where musician = '" + artistName + "'";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Checking if musician exists");
	    		
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
	 * Insert album into album owner table to show user owns album
	 */
	private void insertIntoAlbumOwner(String albumName, String user, String media) {
		
		String checkTemplate = "insert into albumowner values(?, ?, ?)";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
		    	PreparedStatement ps = connection.prepareStatement(checkTemplate);
		    	ps.setString(1, user);
		    	ps.setString(2, albumName);
		    	ps.setString(3, media);
		    	System.out.println("Insert into albumowner table");
	    		ps.executeUpdate();
	    	}
	    	pool.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Insert into albums table when new album
	 */
	private void insertIntoAlbum(String albumName, String artistName, String genre, String instrument) {
		
		String checkTemplate = "insert into album values(?, ?, ?, ?)";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
		    	PreparedStatement ps = connection.prepareStatement(checkTemplate);
		    	ps.setString(1, albumName);
		    	ps.setString(2,  artistName);
		    	ps.setString(3,  genre);
		    	ps.setString(4, instrument);
		    	System.out.println("Insert into album table");
	    		ps.executeUpdate();
	    	}
	    	pool.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Insert into musicians table when new musician
	 */
	private void insertIntoMusicians(String artistName) {
		
		String checkTemplate = "insert into musicians(musician) values(?)";
		
		try {
			
			ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
	    	connection = pool.getConnection();
	    	
	    	if(connection != null) {
	    		System.out.println("Connection is established");
		    	PreparedStatement ps = connection.prepareStatement(checkTemplate);
		    	ps.setString(1, artistName);
		    	System.out.println("Insert into musicians table");
	    		ps.executeUpdate();
	    	}
	    	pool.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
