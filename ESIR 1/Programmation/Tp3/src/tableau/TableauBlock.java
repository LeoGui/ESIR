//Greco Vincent et Guilpain Léo

package tableau;

import tableau.Block;
import tableau.Tableau2x;
import types.*;

public class TableauBlock<T> implements Tableau <T> {

	//attributs
	private Tableau2x<Block<T>>tetris;
	private int capaciteTableau;
	private int capaciteBlock;
	private int taille;
	
	//constructeur
	public TableauBlock(int capaT,int capaB){
		this.capaciteTableau = capaT;
		this.capaciteBlock = capaB;
		tetris = new Tableau2x<Block<T>>(capaciteTableau);
		taille=0;
	}
	
	public TableauBlock(int capaT){
		this.capaciteBlock = 128;
		this.capaciteTableau = capaT;
		tetris = new Tableau2x<Block<T>>(capaciteTableau);
		taille=0;
	}
	
	//méthodes

		// Le tableau est plein
	public boolean full() {return false;}
	
		// le tableau est vide
	public boolean empty(){return taille ==0;}      
	
		// renvoie la taille du tableau
	public int size(){return taille;}
		
	
		//inserer l'élement x à la position i
	public void set(int i,T x){
		assert i>=0 && i<=capaciteBlock : "i superieur ou egal à 0 et i plus petit que la capacite du block";
		tetris.get(i / capaciteBlock).set(i%capaciteBlock, x);} 
	
	
		//affiche le block placé en position i
	public T get (int i){
		assert i>=0 && i<tetris.size() : "i superieur ou egal à 0 et i plus petit que taille";
		return tetris.get(i/capaciteBlock).get(i%capaciteBlock);}     
	
	
		//Si le bloc est plein, créer un nouveau block
	public void push_back(T x) {
		Block <T> block1 = new Block<T>(capaciteBlock);
				if  (tetris.empty()){
					tetris.push_back(block1);
					block1.push_back(x);
				}
				Block <T> courant = tetris.get(tetris.size()-1);
				if ( courant.full()){                         
					tetris.push_back(block1);
					block1.push_back(x);
				}
				else {
					courant.push_back(x);
				}
				taille = taille + 1;
			}			
		
	
	
	public void pop_back(){
		assert !tetris.empty() : "le tableau ne doit pas être vide";
		taille--;
	}   //supprimer la derniere valeur du tableau


	
}
