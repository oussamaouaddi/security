package test;

public class genericite<T, S> { 
	  //Variable d'instance de type T
	  private T valeur1;

	  //Variable d'instance de type S
	  private S valeur2;
	        
	  //Constructeur par défaut
	  public genericite(){
	    this.valeur1 = null;
	    this.valeur2 = null;
	  }        

	  //Constructeur avec paramètres
	  public genericite(T val1, S val2){
	    this.valeur1 = val1;
	    this.valeur2 = val2;
	  }
	        
	  //Méthodes d'initialisation des deux valeurs
	  public void setValeur(T val1, S val2){
	    this.valeur1 = val1;
	    this.valeur2 = val2;
	  }
	 
	  //Retourne la valeur T
	  public T getValeur1() {
	    return valeur1;
	  }
	 
	  //Définit la valeur T
	  public void setValeur1(T valeur1) {
	    this.valeur1 = valeur1;
	  }
	 
	  //Retourne la valeur S
	  public S getValeur2() {
	    return valeur2;
	  }
	 
	  //Définit la valeur S
	  public void setValeur2(S valeur2) {
	    this.valeur2 = valeur2;
	  }  
	  
	  public static void main(String[] args) {
		  genericite<String, Boolean> dual = new genericite<String, Boolean>("toto", true);
		  System.out.println("Valeur de l'objet dual : val1 = " + dual.getValeur1() + ", val2 = " + dual.getValeur2());
		                
		  genericite<Double, Character> dual2 = new genericite<Double, Character>(12.2585, 'C');
		  System.out.println("Valeur de l'objet dual2 : val1 = " + dual2.getValeur1() + ", val2 = " + dual2.getValeur2()); 
		}
	}