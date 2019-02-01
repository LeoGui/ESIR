// GRECO Vincent & GUILPAIN L�o

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
	
	//m�thodes
	
	
	//appel en cours
	public boolean estEnCours(){return this.appelEnCours;}
	
	
	//mettre fin � l'appel
	public void terminerAppel(Date fin){
		this.fin = fin;
		this.appelEnCours = false;
	}
	
	
	//renvoie le num�ro de t�l�phone de l'emetteur
	public NumeroTelephone getEmetteur(){return this.emetteur;}
	
	
	//renvoie le num�ro de t�l�phone du recepteur
	public NumeroTelephone getRecepteur(){return this.recepteur;}
	
	
	//d�termine la dur�e de l'appel
	public int getDureeAppel(){return (int) ((fin.getTime() - debut.getTime())/(1000*60));}
	
}
