package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conn {
	public Connection conn=null;
	
	public Conn(){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/security";
			String user = "root";
			String passwd = "";
			 conn = DriverManager.getConnection(url, user, passwd);
			
		} catch (SQLException e) {
						e.printStackTrace();
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	public Connection getConnexion(){
		
		return conn;
	}

}
