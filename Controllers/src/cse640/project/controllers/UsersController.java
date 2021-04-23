package cse640.project.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UsersController
{
    private Connection dbConnection = null;
    private String     fullName     = "";
    
    private Map<String,String> usersIDMap = null;
    
    public Map<String, String> getUsersIDMap()
    {
        return usersIDMap;
    }
    public void populateUsersIDMap()
    {
        ResultSet rs = null;
        usersIDMap = new HashMap<String,String>();
        String template = "SELECT * FROM Users";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
            while (rs.next())
            {
                usersIDMap.put(rs.getString("ID"), (rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("LNAME")));
            } // end while
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    /**
     * 
     */
    public UsersController(Connection dbConnection)
    {
        this.dbConnection = dbConnection;
    }
    public int insertUser(String id, String password, int access, String name)
    {
        int rc = 0;
        String template = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, id);
            ps.setString(2, password);
            ps.setInt(3, access);
            ps.setString(4, name);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public int updateUser(String id, String password, int access, String name)
    {
        int rc = 0;
        String template = "UPDATE USERS SET ACCESS = ?, NAME = ? where ID = ? and PASSWORD = ?";
        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setInt(1, access);
            ps.setString(2, name);
            ps.setString(3, id);
            ps.setString(4, password);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public int deleteUser(String id, String password)
    {
        int rc = 0;
        String template = "DELETE FROM USERS WHERE ID=? and PASSWORD=?";
        try
        {
            System.out.println("About to delete user " + id + " with password " + password);
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, id);
            ps.setString(2, password);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public boolean findUser(String id, String password)
    {
        System.out.println("I will look for user");
        boolean rc = false;
        fullName     = "";
        String template = "SELECT * FROM users WHERE ID = '" + id + "' AND PASSWORD = '" + password + "'";
        System.out.println(template);
        try
        {
            Statement s = dbConnection.createStatement();
            ResultSet rs = s.executeQuery(template);
            if (rs.next())
            {
                System.out.println("Found user");
                rc = true;
                setFullName((rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("LNAME")));
            }
            else
            {
                System.out.println("Did not find  user");
            } // end if else
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public ResultSet getUsersList(String keyword)
    {
        ResultSet rs = null;
        String template = "SELECT * FROM Users" + " WHERE NAME LIKE '%" + StringUtil.fixSqlFieldValue(keyword) + "%'";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rs);
    }
    public ResultSet getUsersList()
    {
        ResultSet rs = null;
        String template = "SELECT * FROM Users";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rs);
    }
    public ResultSet getUserRecord(String id, String password)
    {
        ResultSet rs = null;
        String template = "SELECT * FROM USERS WHERE ID= '" + id + "' AND PASSWORD = '" + password + "'";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rs);
    }
    public String getFullName()
    {
        return fullName;
    }

}

