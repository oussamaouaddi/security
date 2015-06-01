package test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ForTest {

	public static void main(String[] args) {
		
		
		/*
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;

		int a =0;
		while (elapsedTime < 1*60*1000) {
		    //perform db poll/check
		    elapsedTime = (new Date()).getTime() - startTime;
		    a++;
		}
		System.out.println(a);
		System.out.println(elapsedTime);  */
		/*
		TimerTask task = new TimerTask()
		{
			@Override
			public void run() 
			{
				System.out.println("Hello World !");
			}	
		};
		
		Timer timer = new Timer();
		//timer.scheduleAtFixedRate(task, 0, 1000);
		timer.schedule(task, 2000);
		*/
		
		Produit p = new Produit();
		Map<Integer, Produit> mp = p.getListProduitMap();
        int lenght_map=mp.size();
        for(Produit pa : mp.values()){
        	//System.out.println(pa);
        	}
        
        
        Set s = mp.keySet();
        Iterator it = s.iterator();
        while (it.hasNext()){
           Integer cle = (Integer) it.next(); // tu peux typer plus finement ici
           Produit valeur = mp.get(cle); // tu peux typer plus finement ici
           System.out.println(cle+" "+valeur.designation);
        }
        
        
        System.out.println("suite");
        
        Produit k = mp.get(1);
        System.out.println(k.designation);
        Produit pm=mp.get(1);
        //System.out.println(pm);
        
//        Iterator it = (Iterator) mp.values();
        while(it.hasNext())
            System.out.println(it.next());
        
        
        
		 for(int i = 0 ; i<lenght_map;i++)
			{
				//System.out.println("  * "+(i+1)+" :"+mp.get(i).designation+"\t prix :"+mp.get(i).prix+" €");
				
			}
		 
		 
		 
		 
		 for(Integer key : mp.keySet()){
			   Produit value = mp.get(key);
			  // System.out.println(value);
			   //do something to value
			}
		
		
		
		/*
		
		Chrono ch = new Chrono();
		ch.doIT();
		
		
		String m = ch.getTime();
		//System.out.println(m);  */
		
		
		
		
		
	}

	}


