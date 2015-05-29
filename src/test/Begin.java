package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Begin {

	static  Conn cn = new Conn();
	 
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = cn.getConnexion();
		
		String pseudo ; 
		System.out.println("\tBonjour ! ");
		System.out.println("entrez votre pseudo svp ");

		pseudo =  sc.next();
		
		try {
			
			
			String sql = "select pseudo from client";
		      Statement statement = conn.createStatement();
		      ResultSet rs = statement.executeQuery(sql);
		      
		      while ( rs.next()){
		    	//  System.out.println(rs.getString(1));
		    	  
		    	  String k =rs.getString(1); 
		    	  if(pseudo.equals(k)){
		    		  System.out.println(" Bienvenue ! vous êtes Mr "+pseudo);

		    	  }else
			    	  System.out.println("pardon mais vous etes pas déja inscrit");
		    	    
		      }
		      
		      
			
		} catch (Exception e) {
           e.printStackTrace();
		}
		 
	      
	      /*
	 
	      //insertion de l'image
	      statement.setString(1, nom);
	      statement.setInt(2, 2);
	      

	      statement.setBinaryStream(3, stream, (int)file.length());
	      statement.executeUpdate();
	      
	      System.out.println("bien insere");
	   connection.close();
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
