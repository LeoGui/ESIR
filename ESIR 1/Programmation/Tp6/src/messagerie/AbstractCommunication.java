// GRECO Vincent & GUILPAIN Léo

package messagerie;

import java.util.Date;

public abstract class AbstractCommunication {
	
	//attributs
	protected Date debut;
	protected NumeroTelephone emetteur;
	protected NumeroTelephone	recepteur;
	
	//constructeur
	public AbstractCommunication(Date debut, NumeroTelephone emetteur, NumeroTelephone recepteur){
		this.debut = debut;
		this.emetteur = emetteur;
		this.recepteur = recepteur;
	}
	
	//méthodes
	
	//renvoie la date du début de la communication
	public Date getDebCom() {return this.debut;}
	
	
	//renvoie le numéro de téléphone de l'emetteur
	public NumeroTelephone getNumeroEmetteur(){return this.emetteur;}
	
	
	//renvoie le numéro de téléphone du recepteur
	public NumeroTelephone getNumeroRecepteur(){return this.recepteur;}
	
}