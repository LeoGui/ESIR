//Greco Vincent et Guilpain Léo

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
		assert capa >0 : "capacité doit etre superieur à 0";
		this.capa = capa;
	}

	// MÃ©thodes
	public boolean full() {return(taille == capa);}  // le tableau est plein
	
	public boolean empty(){return(taille ==0);}      // le tableau est vide
	
	public int size(){return taille;}             // renvoie la taille du tableau
	
	public void set(int i, T x){
		assert 0<=i && i < size(): "i doit etre superieure ou egale à i";
		tab.set(i,x);}     //inserer l'Ã©lement x Ã  la position i
	
	public T get (int i){
		assert 0<=i && i <size() : "i doit etre superieure ou egale à i";
		return tab.get(i);}     //affiche l'Ã©lement placer en position i
	
	public void push_back(T x) {assert taille<capa;                   //insÃ©rer l'element x Ã  la fin 
		tab.set(taille,x);
		taille ++;
	}
	
	public void pop_back(){assert taille >0;taille --;}   //supprimer la derniere valeur du tableau	
}
