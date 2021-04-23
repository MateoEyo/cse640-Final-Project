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
 * Servlet implementation class YourAlbums
 */
@WebServlet("/YourAlbumsServlet")
public class YourAlbumsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection			connection			= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourAlbumsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("*** ENTERING LIST YOUR ALBUM CODE ***");
		
		String url = "YourAlbums.jsp";
		ResultSet resultSet = null;
		Statement statement = null;
		List<Album> albumList = new ArrayList<Album>();
		HttpSession session = request.getSession(true);
		String user = (String) session.getAttribute("username");
		String getAlbums = 	"select ALBUM.name, album.artist, album.genre, albumowner.method from album " +
							"inner join albumowner on album.name = albumowner.name " + 
							"where albumowner.id='" + user + "'";
		
		try {
			
        	ConnectionPool pool = ConnectionPool.getInstance("jdbc/M0MATI01"); 
        	connection = pool.getConnection();
        	
        	if(connection != null) {
        		System.out.println("Connection is established");
        		System.out.println("Running SQL Statement: " + getAlbums);
        		
        		statement = connection.createStatement();
        		resultSet = statement.executeQuery(getAlbums);
        		
        		while(resultSet.next()) {
        			Album newAlbum = new Album(resultSet.getString(1));
        			newAlbum.setArtist(resultSet.getString(2));
        			newAlbum.setGenre(resultSet.getString(3));
        			newAlbum.setMedia(resultSet.getString(4));
        			albumList.add(newAlbum);
        		}
        		
        		request.setAttribute("albumList", albumList);
        		
        		pool.freeConnection(connection);
        	} else {
        		System.out.println("Connection could not be established");
        	}
        
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("EXITING LIST YOUR ALBUM CODE ***");
		
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
