// GRECO Vincent & GUILPAIN Léo

package messagerie;
import java.util.Date;

public class Appel {
	
	//attributs
	private Date debut;
	private Date fin;
	private NumeroTelephone emetteur;
	private NumeroTelephone recepteur;
	private boolean appelEnCours;
	
	
	//constructeur
	public Appel (NumeroTelephone emetteur, NumeroTelephone recepteur ,Date dateDebut){
		this.debut = dateDebut;
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.appelEnCours = true;
	}
	
	//méthodes
	
	
	//appel en cours
	public boolean estEnCours(){return this.appelEnCours;}
	
	
	//mettre fin à l'appel
	public void terminerAppel(Date fin){
		this.fin = fin;
		this.appelEnCours = false;
	}
	
	
	//renvoie le numéro de téléphone de l'emetteur
	public NumeroTelephone getEmetteur(){return this.emetteur;}
	
	
	//renvoie le numéro de téléphone du recepteur
	public NumeroTelephone getRecepteur(){return this.recepteur;}
	
	
	//détermine la durée de l'appel
	public int getDureeAppel(){return (int) ((fin.getTime() - debut.getTime())/(1000*60));}
	
}
