package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		
		/*
		String[] tempArray = new String[]{"Un","Deux","Trois"};
		List<String> list = Arrays.asList(tempArray);
		
		for(String k :list){
			
			System.out.println(k);
			
		}  */
		
		
		List<Produit> lp = new ArrayList<Produit>();
		
		Produit p1= new Produit(1, "danon", (float) 40.0);
		Produit p2= new Produit(1, "tide", (float) 100.0);
		Produit p4= new Produit(1, "blé", (float) 1000.0);
		Produit p5= new Produit(1, "lait", (float) 70.0);
		
		lp.add(p1);
		lp.add(p2);
		lp.add(p4);
		lp.add(p5);
		
		for ( Produit p : lp){
			System.out.println(p.designation);
		}

		
		
		
		
	}

}
