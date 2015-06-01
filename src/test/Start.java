package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Start {
	static Conn cn = new Conn();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			int temps = 0, prix = 0, frequence = 0, nbConnexion = 0, classe,id_client=0,quantite=0;
			int nbre_prod=0;int duree_minute=0;
			float montant =0;
			float montant_finale =0;
			
			
			
			char reponse = ' ',  mode =' ',reponsee=' ';
			String k = null;

			Scanner scc = new Scanner(System.in);
			Connection conn = cn.getConnexion();
			
			// liste des produits à acheter
			Produit p = new Produit();
			List<Produit> lp = p.getListProduit();
			Map<Integer, Produit> mp = p.getListProduitMap();
			
			String pseudo;
			System.out.println("\tBonjour ! ");
			System.out.println("entrez votre pseudo svp ");

			pseudo = scc.next();

			String sql = "select pseudo,duree,prixmoyen,frequence,nbredefois,classement,id_client from client"
					+ " where pseudo ='" + pseudo + "'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				k = rs.getString(1);
				temps = rs.getInt(2);
				prix = rs.getInt(3);
				frequence = rs.getInt(4);
				nbConnexion = rs.getInt(5);
				classe = rs.getInt(6);
				id_client=rs.getInt(7);
			}

			if (pseudo.equals(k)) {
				System.out.println(" Bienvenue ! vous êtes Mr " + pseudo
						+ " et vous avez " + nbConnexion
						+ " connexion à l'application");
				Chrono ch = new Chrono();

				do {
					if (nbConnexion >= 30) {

						
						//classification
						if (temps <= 15) {

							System.out
									.println("----------vous êtes  déja  dans T1--------------");

							if (prix <= 10000) {

								System.out
										.println("----------vous êtes  aussi  dans P1--------------");

								if (frequence < 10) {
									System.out
											.println("***********vous êtes  finalement dans la classe C1*********");
									classe = 1;

								} else {

									System.out
											.println("***********vous êtes  finalement dans la classe C2*********");
									classe = 2;

								}

							} else {

								System.out
										.println("----------vous êtes  aussi  dans P2--------------");

								if (frequence < 10) {
									System.out
											.println("***********vous êtes  finalement dans la classe C3*********");
									classe = 3;

								} else {

									System.out
											.println("***********vous êtes  finalement dans la classe C4*********");
									classe = 4;

								}

							}

						}

						else if (temps > 15 && temps < 30) {
							System.out
									.println("------------vous êtes  déja  dans T2------------");
							if (prix <= 10000) {

								System.out
										.println("----------vous êtes  aussi  dans P1--------------");

								if (frequence < 10) {
									System.out
											.println("***********vous êtes  finalement dans la classe C5*********");
									classe = 5;

								} else {

									System.out
											.println("***********vous êtes  finalement dans la classe C6*********");
									classe = 6;

								}

							} else {

								System.out
										.println("----------vous êtes  aussi  dans P2--------------");

								if (frequence < 10) {
									System.out
											.println("***********vous êtes  finalement dans la classe C7*********");
									classe = 7;

								} else {

									System.out
											.println("***********vous êtes  finalement dans la classe C8*********");
									classe = 8;

								}

							}
						}

						else {
							System.out
									.println("--------------vous êtes  déja  dans T3-------------");

							if (prix <= 10000) {

								System.out
										.println("----------vous êtes  aussi  dans P1--------------");

								if (frequence < 10) {
									System.out
											.println("***********vous êtes  finalement dans la classe C9*********");
									classe = 9;

								} else {

									System.out
											.println("***********vous êtes  finalement dans la classe C10*********");
									classe = 10;

								}

							} else {

								System.out
										.println("----------vous êtes  aussi  dans P2--------------");

								if (frequence < 10) {
									System.out
											.println("***********vous êtes  finalement dans la classe C11*********");
									classe = 11;

								} else {

									System.out
											.println("***********vous êtes  finalement dans la classe C12*********");
									classe = 12;

								}

							}

						}

						String sql1 = "update client set classement='" + classe
								+ "' where pseudo ='" + pseudo + "'";
						PreparedStatement statement1 = conn
								.prepareStatement(sql);

						statement1.executeUpdate(sql1);

					} else {

						System.out
								.println("vous êtes déja hors classement tant que vous n avez pas encore nbre de connexion supérieur à 30");

						String sql1 = "update client set classement= -1"
								+ " where pseudo ='" + pseudo + "'";
						PreparedStatement statement1 = conn
								.prepareStatement(sql);

						statement1.executeUpdate(sql1);
					}

					try {
						nbConnexion++;

						String sql1 = "update client set nbredefois="
								+ nbConnexion + " where pseudo ='" + pseudo
								+ "'";
						PreparedStatement statement1 = conn
								.prepareStatement(sql);

						statement1.executeUpdate(sql1);
						System.out
								.println("maintenant vous avez le nombre de connexion = "
										+ nbConnexion);
						
						
						
						
						try {
							do{
							
							do{//tant que reponse n'est pas O ou N
						        mode = ' ';
						        int lenght=lp.size();
						        int lenght_map=mp.size();
						        System.out.println("\t ************la liste des produit**********");
						        
						        for(int i = 0 ; i<lenght_map;i++)
								{
									System.out.println("  * "+(i+1)+" :"+mp.get(i).designation+"\t prix :"+mp.get(i).prix+" €");
									
								}
						        /*
								for(int i = 0 ; i<lenght;i++)
								{
									System.out.println("  * "+(i+1)+" :"+lp.get(i).designation+"\t prix :"+lp.get(i).prix+" €");
									
								}  */
								
								
						        System.out.println("Choisissez le produit que vous voullez  : ");
						        mode = sc.next().charAt(0);
						       
						        if(mode != '1' && mode != '2' && mode != '3' && mode != '4')
						          System.out.println("Mode inconnu, veuillez réitérer votre choix.");
						        
						        System.out.println("Choisissez le nombre de produit  : ");
						       quantite = sc.nextInt();

						      }while (mode != '1' && mode != '2' && mode != '3' && mode != '4');
							
							String pp = String.valueOf(mode);
							int id_prod=Integer.parseInt(pp);
							//System.out.println("totale : "+quantite*prix);
							
							String sql11 = "insert into achat(numProduit,numClient,quantite) values ("+id_prod+","+id_client+","+quantite+")";
								
							PreparedStatement statement11 = conn
									.prepareStatement(sql);

							statement11.executeUpdate(sql11);
							
							System.out.println("produit numero "+id_prod+" bien inséré");
							
							do {
								System.out
										.println("Souhaitez-vous  acheter un nouveau produit ?(O/N)");
								reponsee = sc.next().charAt(0);

							} while (reponsee != 'O' && reponsee != 'N');
							nbre_prod++;
						} while (reponsee == 'O'); }

							
						 catch (Exception e) {
							e.printStackTrace();
 						}
						
						
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}

					do {
						System.out
								.println("Souhaitez-vous rester connecté et acheter un nouveau produit ?(O/N)");
						reponse = sc.next().charAt(0);

					} while (reponse != 'O' && reponse != 'N');
				} while (reponse == 'O');
				
				duree_minute=ch.minute;

			} else

				System.out.println("pardon mais vous etes pas encore inscrit");
			System.out.println("finished");

			rs.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
