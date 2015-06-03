package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Attaque {
	int key, value;
	String name;
}

class KMeans {
	static List<List<Attaque>> cluster;
	static double mean[];
	static double prev_mean[];
	static Conn cn = new Conn();
	static Scanner scan = new Scanner(System.in);
	static Connection conn = cn.getConnexion();
	
	static Statement statement ;
	
	public void verifAttaque(String query,int p ) throws SQLException{
		String sql1 = null;
		PreparedStatement statement1;

		try {
	
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		ResultSet rs = null;
		 //query = "select id_attaque, pseudo_client,Classeancienne from attaque ";
		List<Attaque> data = new LinkedList<>();
		try {
			rs = statement.executeQuery(query);
			while (rs.next()) {
				Attaque dc = new Attaque();
				dc.key = Integer.parseInt(rs.getString(1));
				dc.value = Integer.parseInt(rs.getString(3));
				dc.name = rs.getString(2);
				data.add(dc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Enter the number of clusters:");
		int k = scan.nextInt();
		mean = new double[k];
		prev_mean = new double[k];
		cluster = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			cluster.add(new LinkedList<Attaque>());
			mean[i] = (double) data.get(i).value;
		}

		System.out.println("Initial Mean Values:");
		for (double i : mean) {
			System.out.println(i);
		}
		System.out.println("+++ START OF CLUSTERING +++\n\n");

		int iter = 0;
		do {
			iter++;
			System.out.println("\nITERATION " + iter + "\n");
			System.arraycopy(mean, 0, prev_mean, 0, mean.length);
			for (List<Attaque> i : cluster) {
				i.clear();
			}
			for (Attaque i : data) {
				cluster.get(getListNumber(i.value)).add(i);
			}
			calculateNewMeans();
			displayClusters();
		} while (!equalMeans());

		System.out.println("\n\n+++ FINAL ANSWER +++");
		int listno = 1;
		for (List<Attaque> l1 : cluster) {
			System.out.println("Cluster no.: " + (listno ));
			System.out.print("{ ");
			for (Attaque i : l1) {
				System.out.print(i.name + " ");
				
				if(p==1)
					
					sql1 = "update attaque set ancienCluster='"
							+ listno + "' where pseudo_client ='" +i.name+"'";
				
				
				else if(p==2)
					 sql1 = "update attaque set newCluster='"
								+ listno + "' where pseudo_client ='" +i.name+"'";
						
				 statement1 = conn
						.prepareStatement(sql1);

				statement1.executeUpdate(sql1);
				
			}

			System.out.println("}");
			listno++;
		}
		
		
	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		
		new KMeans().verifAttaque("select id_attaque, pseudo_client,Classeancienne from attaque ",1);

	}

	static int getListNumber(int element) {
		// Here we calculate the Euclidean distance of 'element' from all
		// values in 'mean' array.
		double least_dist = Double.MAX_VALUE;
		double new_dist = 0;
		int listno = 0;
		for (int i = 0; i < mean.length; i++) {
			new_dist = Math.abs(mean[i] - ((double) element));
			if (new_dist < least_dist) {
				least_dist = new_dist;
				listno = i;
			}
		}
		return listno;
	}

	static void calculateNewMeans() {
		// Here we update the 'mean' array to reflect the changes in the
		// clusters.
		int ctr = 0;
		for (List<Attaque> l : cluster) {
			mean[ctr] = 0;
			for (Attaque i : l) {
				mean[ctr] += i.value;
			}
			mean[ctr] /= l.size();
			ctr++;
		}

	}

	static boolean equalMeans() {
		// Here we compare the 'prev_mean' and 'mean' arrays to see if they
		// are equal or not.
		int j = 0;
		for (double i : mean) {
			if (prev_mean[j] != i) {
				return false;
			}
			j++;
		}
		return true;
	}

	static void displayClusters() {
		int listno = 0;
		for (List<Attaque> l1 : cluster) {
			System.out.println("Cluster no.: " + (listno + 1));
			System.out.print("{ ");
			for (Attaque i : l1) {
				System.out.print(i.value + " ");
			}
			System.out.println("}");
			System.out.println("--- Cluster Mean = " + mean[listno] + " ---");
			listno++;
		}
	}
}