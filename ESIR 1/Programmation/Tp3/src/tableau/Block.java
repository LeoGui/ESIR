//Greco Vincent et Guilpain L�o

package tableau;

import types.*;

public class Block<T> implements Tableau<T>{
	
	//attribut
	private int taille ;
	private int capa ;
	private Array<T> tab ;
	
	//constructeur
	public Block (int capa){
		tab = new Array <T> (capa);
		taille = 0;
		assert capa >0 : "capacit� doit etre superieur � 0";
		this.capa = capa;
	}

	// Méthodes
	public boolean full() {return(taille == capa);}  // le tableau est plein
	
	public boolean empty(){return(taille ==0);}      // le tableau est vide
	
	public int size(){return taille;}             // renvoie la taille du tableau
	
	public void set(int i, T x){
		assert 0<=i && i < size(): "i doit etre superieure ou egale � i";
		tab.set(i,x);}     //inserer l'élement x à la position i
	
	public T get (int i){
		assert 0<=i && i <size() : "i doit etre superieure ou egale � i";
		return tab.get(i);}     //affiche l'élement placer en position i
	
	public void push_back(T x) {assert taille<capa;                   //insérer l'element x à la fin 
		tab.set(taille,x);
		taille ++;
	}
	
	public void pop_back(){assert taille >0;taille --;}   //supprimer la derniere valeur du tableau	
}
