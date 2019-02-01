// GRECO Vincent & GUILPAIN L�o

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
	
	//m�thodes
	
	//renvoie la date du d�but de la communication
	public Date getDebCom() {return this.debut;}
	
	
	//renvoie le num�ro de t�l�phone de l'emetteur
	public NumeroTelephone getNumeroEmetteur(){return this.emetteur;}
	
	
	//renvoie le num�ro de t�l�phone du recepteur
	public NumeroTelephone getNumeroRecepteur(){return this.recepteur;}
	
}