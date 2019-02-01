// GRECO Vincent & GUILPAIN Léo

package messagerie;
import java.util.Date;
/**
 * utilisation du téléphone par l'abonné
 */

public class Telephone implements GestionCommunication{
	
	//attributs
	private boolean etat;
	private BoiteSMS b1;
	protected Date datefin;
	protected Date datedebut;
	protected NumeroTelephone num;
	protected String msgVocal;
	protected Forfait nomForfait;
	protected String nomPersonne;
	protected AbonneOperateur abonne;
	
	
	//constructeur
	public Telephone(AbonneOperateur abonne){
		BoiteSMS b1 = new BoiteSMS();
		this.b1 = b1;
		this.abonne = abonne;
	}

	//--------------------------------------------------
  // méthodes de l'interface GestionCommunication
  //------------------------------------------------------------------------

	public boolean appeler(String numero, String msgVocalSiOccupe, Date debut){
		return this.abonne.appeler(numero, msgVocalSiOccupe, debut);
	}


	public void envoyerSMS(String numero, String sms, Date dateSMS){
		this.abonne.envoyerSMS(numero, sms, dateSMS);
	}


	public void recevoirSMS(MessageSMS message){
		this.b1.ajouterSMS(message);
	}


	public boolean accepterAppel(String numeroAppelant){
		return true;
	}


	public void cloreAppel(Date fin) {
		this.abonne.cloreAppel(fin);
	}

  //------------------------------------------------------------------------
  // méthodes propres
  //------------------------------------------------------------------------


	//renvoie la boiteSMS du telephone
	public BoiteSMS getBoite(){return b1;}


	//le téléphone est allumé
	public boolean estallume() {return etat;}


	//le telephone est eteint
	public boolean esteteint() {return !etat;}


	public void allumer() {
		this.etat = true;
		System.out.println("allumer");
	}

	public void eteindre() {
		this.etat = false;
		System.out.println("eteindre");
	}


	//renvoie l'abonne correspondant au telephone
	public AbonneOperateur getAbonne(){return abonne;}

} // Telephone