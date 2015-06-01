package test;

import java.util.Date;
import java.util.GregorianCalendar;

public class DifferenceMinutes {
	
	static int d = new Date().getSeconds();

	public static void main(String[] args) {
		/** La minute de départ */
		Date minute1 = new GregorianCalendar(2012, 11, 11, 12, 59).getTime();

		/** La minute d'arrivée */
		Date minute2 = new GregorianCalendar(2012, 11, 11, 13, 13).getTime();

		// Calcul de différence en nombre de minutes entre les deux minutes
		long diff = minute2.getTime() - minute1.getTime();

		System.out.printf("Nombre de Minute entre %tT et %tT est\n %d Minutes",
				minute1, minute2, (diff / (1000 * 60)));
		System.out.println();
		
		System.out.println(d);
		
		int o = new Date().getSeconds();
		System.out.println(o);

	}
}
