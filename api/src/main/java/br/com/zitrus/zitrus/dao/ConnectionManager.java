package br.com.zitrus.zitrus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	    private static String url = "jdbc:h2:mem:zitrus";    
	    private static String driverName = "org.h2.Driver";   
	    private static String username = "sa";   
	    private static String password = "123";
	    private static Connection con;

	    public static Connection getConnection() {
	        try {
	            Class.forName(driverName);
	            try {
	                con = DriverManager.getConnection(url, username, password);
	            } catch (SQLException ex) {
	            	ex.printStackTrace();
	                System.out.println("Failed to create the database connection."); 
	            }
	        } catch (ClassNotFoundException ex) {
	        	ex.printStackTrace();
	            System.out.println("Driver not found."); 
	        }
	        return con;
	}
}
