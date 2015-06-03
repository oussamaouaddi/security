package test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Produit {
	int id_prod;
	String designation;
	Float prix;
	static Conn cn = new Conn();

	static Connection conn = cn.getConnexion();

	public Produit() {
		id_prod = 0;
		designation = null;
		prix = (float) 0;
	}

	public int getId_prod() {
		return id_prod;
	}

	public Produit(int id_prod, String designation, Float prix) {
		super();
		this.id_prod = id_prod;
		this.designation = designation;
		this.prix = prix;
	}

	public Map<Integer, Produit> getListProduitMap() {

		Map<Integer, Produit> mp = new HashMap<Integer, Produit>();

		Produit p1 = new Produit(1, "danon", (float) 40.0);
		Produit p2 = new Produit(2, "tide", (float) 100.0);
		Produit p4 = new Produit(3, "blé", (float) 1000.0);
		Produit p5 = new Produit(4, "lait", (float) 70.0);

		mp.put(1, p1);
		mp.put(2, p2);
		mp.put(3, p4);
		mp.put(4, p5);

		return mp;
	}

	public List<Produit> getListProduit() {

		List<Produit> lp = new ArrayList<Produit>();

		Produit p1 = new Produit(1, "danon", (float) 40.0);
		Produit p2 = new Produit(2, "tide", (float) 100.0);
		Produit p4 = new Produit(3, "blé", (float) 1000.0);
		Produit p5 = new Produit(4, "lait", (float) 70.0);

		lp.add(p1);
		lp.add(p2);
		lp.add(p4);
		lp.add(p5);

		return lp;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public static void main(String[] args) {

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
