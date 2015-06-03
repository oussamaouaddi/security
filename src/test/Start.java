package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Start {
	static Conn cn = new Conn();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {

			// les variables
			int temps = 0, prix = 0, frequence = 0, nbConnexion = 0, classe = 0, id_client = 0, quantite = 0;
			int nbre_prod = 0;
			int duree_minute = 0;
			float montant = 0;
			float montant_finale = 0;
			String admin;
			char reponse = ' ', mode = ' ', reponsee = ' ', choice = ' ';
			String k = null;
			Scanner scc = new Scanner(System.in);
			Connection conn = cn.getConnexion();
			// liste des produits à acheter
			Produit p = new Produit();
			List<Produit> lp = p.getListProduit();
			Map<Integer, Produit> mp = p.getListProduitMap();

			do {// tant que reponse n'est pas O ou N
				System.out.println("Choisissez votre role : ");
				System.out.println("1 - administrateur");
				System.out.println("2 - client");
				choice = sc.nextLine().charAt(0);

				if (choice != '1' && choice != '2')
					System.out
							.println("Mode inconnu, veuillez réitérer votre choix.");

			} while (choice != '1' && choice != '2');

			switch (choice) {
			case '1':

				System.out.println("Bonjour administrateur  !");
				System.out.println("entrez le mot de passe :");
				admin = sc.nextLine();

				if (!admin.equalsIgnoreCase("admin")) {

					System.out
							.println("vous avez entez un fausse mot de passe");
					break;
				}

				else {

					System.out
							.println("voilà les attaques suspects (changement de classe de classification)  :");

					String sqlattaque = "select pseudo_client,Classeancienne,Classenouvelle from attaque ";

					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sqlattaque);

					String name;
					int class_an;
					int class_new;
					while (rs.next()) {

						name = rs.getString(1);
						class_an = rs.getInt(2);
						class_new = rs.getInt(3);

						if (class_an != class_new)
							System.out
									.println(" * pseudo " + name
											+ "\t classe ancienne  :"
											+ class_an
											+ "\t nouvelle classe  :"
											+ class_new + " ");

					}

					System.out.println();
					System.out
							.println("\t lancement de l'algorithme de kmeans sur les anciennes classes");

					try {
						KMeans km = new KMeans();
						km.verifAttaque(
								"select id_attaque, pseudo_client,Classeancienne from attaque ",
								1);
					} catch (Exception e) {
						e.printStackTrace();
					}  
					//

					System.out
							.println("\t lancement de l'algorithme de kmeans sur les nouvelles classes");

					try {

						KMeans km = new KMeans();
						km.verifAttaque(
								"select id_attaque, pseudo_client,Classenouvelle from attaque ",
								2);
					} catch (Exception e) {
						e.printStackTrace();
					}

					
					try {
						sqlattaque = "select ancienCluster,newCluster from attaque ";

						rs = statement.executeQuery(sqlattaque);

						int cluster_an;
						int cluster_new;
						while (rs.next()) {

							cluster_an = rs.getInt(1);
							cluster_new = rs.getInt(2);
							
							//System.out.println(cluster_an+ " "+cluster_new);
							
							if(cluster_an  != cluster_new){
							JOptionPane.showMessageDialog(null,"alert");
								System.out.println(" Attention !! il y a une attaque ! le cluster ancien et different du nouveau cluster");
							JOptionPane.showMessageDialog(null, " Attention !! il y a une attaque ! le cluster ancien et different du nouveau cluster");
							}
						
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				break;

			case '2':

				// System.out.println("Vous avez juste la moyenne.");
				// break;

				// start

				String pseudo;
				System.out.println("\t Bonjour  Client! ");
				System.out.println("entrez votre pseudo svp :");

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
					id_client = rs.getInt(7);
				}

				if (pseudo.equals(k)) {
					System.out.println(" Bienvenue ! vous êtes Mr " + pseudo
							+ " et vous avez " + nbConnexion
							+ " connexion à l'application");
					Chrono ch = new Chrono();
					Chrono.doIT();

					do {
						if (nbConnexion >= 30) {

							// classification
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

							String sql1 = "update client set classement='"
									+ classe + "' where pseudo ='" + pseudo
									+ "'";
							PreparedStatement statement1 = conn
									.prepareStatement(sql);

							statement1.executeUpdate(sql1);

							String sql01 = "update attaque set Classenouvelle='"
									+ classe
									+ "' where pseudo_client ='"
									+ pseudo + "'";
							PreparedStatement statement01 = conn
									.prepareStatement(sql01);

							statement1.executeUpdate(sql01);

						} else if (nbConnexion == 30) {

							String sql121 = "insert into attaque(id_client,pseudo_client,Classeancienne) values ("
									+ id_client
									+ ",'"
									+ pseudo
									+ "',"
									+ classe
									+ ")";

							PreparedStatement statement121 = conn
									.prepareStatement(sql121);

							statement121.executeUpdate(sql121);

						}

						else {

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
								do {

									do {// tant que reponse n'est pas O ou N
										mode = ' ';
										int lenght = lp.size();
										int lenght_map = mp.size();
										System.out
												.println("\t ************la liste des produit**********");

										for (int i = 0; i < lenght; i++) {
											System.out.println("  * " + (i + 1)
													+ " :"
													+ lp.get(i).designation
													+ "\t prix :"
													+ lp.get(i).prix + " €");

										}
										/*
										 * for(int i = 0 ; i<lenght;i++) {
										 * System.out
										 * .println("  * "+(i+1)+" :"+lp.get
										 * (i).designation
										 * +"\t prix :"+lp.get(i).prix+" €");
										 * 
										 * }
										 */

										System.out
												.println("Choisissez le produit que vous voullez  : ");
										mode = sc.next().charAt(0);

										if (mode != '1' && mode != '2'
												&& mode != '3' && mode != '4')
											System.out
													.println("Mode inconnu, veuillez réitérer votre choix.");

										System.out
												.println("Choisissez le nombre de produit  : ");
										quantite = sc.nextInt();

									} while (mode != '1' && mode != '2'
											&& mode != '3' && mode != '4');

									String pp = String.valueOf(mode);
									int id_prod = Integer.parseInt(pp);
									Produit pr = mp.get(id_prod);
									montant = pr.getPrix() * quantite;

									String sql11 = "insert into achat(numProduit,numClient,quantite) values ("
											+ id_prod
											+ ","
											+ id_client
											+ ","
											+ quantite + ")";

									PreparedStatement statement11 = conn
											.prepareStatement(sql);

									statement11.executeUpdate(sql11);

									System.out.println("produit numero "
											+ id_prod + " avec montant :"
											+ montant + "  bien inséré");

									do {
										System.out
												.println("Souhaitez-vous  acheter un nouveau produit ?(O/N)");
										reponsee = sc.next().charAt(0);

									} while (reponsee != 'O' && reponsee != 'N');
									nbre_prod++;

									montant_finale += montant;

									System.out
											.println("montant total d'achat des produit est  "
													+ montant_finale
													+ " nbre de produit d'achat:"
													+ nbre_prod);
								} while (reponsee == 'O');
							}

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

					duree_minute = ch.minute;

					System.out
							.println("la duree en minutes de l'operation est   "
									+ duree_minute);
					String sql12 = "insert into historique(id_client,nbre_connexion,duree_connexion,frequence_achat,montant_achat) values ("
							+ id_client
							+ ","
							+ nbConnexion
							+ ","
							+ duree_minute
							+ ","
							+ nbre_prod
							+ ","
							+ montant_finale + ")";

					PreparedStatement statement12 = conn
							.prepareStatement(sql12);

					statement12.executeUpdate(sql12);

					System.out.println("historique bien enregistré !");

					String sql122 = "update client ,historique set duree=(select avg(duree_connexion)   from historique where id_client="
							+ id_client
							+ "), prixmoyen=(select avg(montant_achat)  from historique where id_client="
							+ id_client
							+ "), frequence=(select avg(frequence_achat) from historique  where id_client="
							+ id_client
							+ ") where client.id_client=historique.id_client and  client.id_client="
							+ id_client;

					PreparedStatement statement122 = conn
							.prepareStatement(sql122);

					statement122.executeUpdate(sql122);

					System.out.println("table Client was updated !");

				} else

					System.out
							.println("pardon mais vous etes pas encore inscrit");
				System.out.println("finished");

				rs.close();

			}

			System.out.println("**************au revoir*****************");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
