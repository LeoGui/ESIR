// GRECO Vincent & GUILPAIN L�o

package messagerie;

public class Forfait {
	
	//Attributs 
	protected String Illimite;
	protected String UneHeure;
	protected String Acte;
	protected float Prix;
	protected double PrixSMS;
	protected double PrixAppel;
	protected String nom;
	
	//Constructeur
	public Forfait(String nom)
	{
		this.nom = nom ;
		
		//si forfait : "1h"
		if (nom.equals("1h")){
			UneHeure = nom;
			Prix = 20;
			PrixSMS = 0.07; //Prix pour 1 sms
			PrixAppel = 0.15; //Prix pour 1 minute d'appel
		}
		
		//si forfait "illimite"
		else if(nom.equals("illimite")){
			Illimite = nom;
			Prix = 40;
			PrixSMS = 0;
			PrixAppel = 0;
		}
		
		//si forfait "acte"
		else if(nom.equals("acte")){
			Acte = nom;
			Prix = 20;
			PrixSMS = 0.07;
			PrixAppel = 0.15;
		}
	}
	
	
	//renvoie le nom du forfait
	public String getNom(){return nom;}

}
