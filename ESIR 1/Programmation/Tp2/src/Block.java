
public class Block <T> implements Tableau <T>{
	
	//attribut
	private int taille ;
	private int capa ;
	private <T> tab ;
	
	//constructeur
	Block<T> (capa){
		tab = new Array <T> (capa);
		taille = 0;
		this.capa = capa;
	}

	// Méthodes
	boolean full() {return(taille == capa);}  // le tableau est plein
	
	boolean empty(){return(taille ==0);}      // le tableau est vide
	
	int getsize(){return taille;}             // renvoie la taille du tableau
	
	void set(int i, T x){assert i<taille ; return tab.set(i,x);}     //inserer l'élement x à la position i
	
	T get (int i){assert i<taille ; return tab.get(i);}     //affiche l'élement placer en position i
	
	void pushBack(T x) {assert taille<capa                   //insérer l'element x à la fin 
		tab.set(taille,x);
		taille ++;
	}
	
	void popBack(){assert taille >0;taille --;}   //supprimer la derniere valeur du tableau
	
}
