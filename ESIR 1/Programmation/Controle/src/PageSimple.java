import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class PageSimple implements Repertoire{

	//attributs
	private List <Contact> repertoire;
	
	//constructeur
	public PageSimple(){
		repertoire = new LinkedList<Contact>();
		this.repertoire = repertoire;
	}

	
protected Iterator <Contact> chercher(List <Contact> repertoire,String nom){

	ListIterator <Contact> it = repertoire.ListIterator();
	while(it.hasNext()){
		Contact courant = it.next();
		if(courant.getNom().equals(nom)){
			return it;
		}
		else{return null;}
	}
}
	
public boolean estPresent(String nom){
	
	if(chercher(this.repertoire,nom) == null){
		return false;
	}
	else{return true;}
}
	
public String getNumero(String Nom){
	ListIterator <Contact> it = chercher(this.repertoire, Nom);
	
	if (it == null){
		return null;
	}
	else{
		Contact C1 = it.previous();
		return C1.getNumero();}

}

public void enregistrer(String nom, String numero){
	if (!estPresent(nom)){
		Contact c = new Contact(nom,numero);
		this.repertoire.add(c);
	}
}

public void modifier(String nom, String numero){
	if(chercher(this.repertoire,nom) != null){
		ListIterator <Contact> it = chercher(this.repertoire, nom);
		Contact C1 = it.previous();
		C1.setNumero(numero);

	}
}

public void supprimer(String nom){
	if(chercher(this.repertoire,nom) != null){
		ListIterator <Contact> it = chercher(this.repertoire, nom);
		it.remove();
	}

}
}
