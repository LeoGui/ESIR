//Greco Vincent et Guilpain Léo

package tableau;

import types.Tableau;
import tableau.Block;

public class Tableau2x<T> implements Tableau<T>{

	//attribut
	private Block<T> block;
	int capacite;
	
	//constructeur
	public Tableau2x (int capa){
		assert capa >0 : "capacité doit etre superieur à 0";
		this.capacite = capa;
		block = new Block<T>(capa);
	}
	
	// méthodes
	public boolean full() {return false;}  // le tableau est pas plein
	
	public boolean empty(){return block.empty();}      // le tableau est vide
	
	public int size(){return block.size();}             // renvoie la taille du tableau
	
	public void set(int i, T x){
		assert 0<=i : "i doit etre superieure ou egale à i";
		block.set(i,x);}     //inserer l'élement x à la position i
	
	public T get (int i){
		assert 0<=i : "i doit etre superieure ou egale à i";
		return block.get(i);}     //affiche l'élement placer en position i
	
	public void push_back(T x) {
		if (block.full()){                         //Si le bloc est plein, creer temp qui est un nouveau bloc 2 fois plus grand
			Block<T> temp = new Block<T>(block.size() * 2);
			for(int i = 0; i<block.size();i++){
				temp.push_back(block.get(i));
			}
			temp.push_back(x);
			block = temp;
		}
		else {block.push_back(x);}
	}
	
	
	
	public void pop_back(){block.pop_back();}   //supprimer la derniere valeur du tableau


}

