
public class Tableau2x <T>{

	//attribut
	private Block<T>block;
	
	//constructeur
	Tableau2x<T>(capa){
		block = new Block<T>(capa);
	}
	
	// méthodes
	boolean full() {return false;}  // le tableau n'est pas plein
	
	boolean empty(){return block.empty();}      // le tableau est vide
	
	int getsize(){return block.getsize();}             // renvoie la taille du tableau
	
	void set(int i, T x){return block.set(i,x);}     //inserer l'élement x à la position i
	
	T get (int i){return block.get(i);}     //affiche l'élement placer en position i
	
	void pushBack(T x) {
		if block.full(){                         //Si le bloc est plein, creer temp qui est un nouveau bloc 2 fois plus grand
			Block<T> temp = new Block<T>(block.size * 2);
			for(int i = 0; i<block.getsize();i++){
				temp.pushBack(block.get(i));
			}
			temp.pushBack(x);
			block = temp;
		}
		else {block.pushBack(x);}
	}
	
	
	
	void popBack(){block.popBack();}   //supprimer la derniere valeur du tableau
	
}

