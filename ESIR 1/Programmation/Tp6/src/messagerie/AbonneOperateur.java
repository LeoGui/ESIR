// GRECO Vincent & GUILPAIN L�o

package messagerie;
import java.util.Date;

/**
 * Informations d'Abonn� g�r�es par l'op�rateur
 */
public class AbonneOperateur implements GestionCommunication
{
	//attributs
	private Telephone tel;
	private Forfait nomForfait;
	private String nomPersonne;
	private BoiteSMS b1;
	private NumeroTelephone num;	
	private Operateur operateur;
	private BoiteVocale b2;
	private int dureeAppels;
	private int Nbconsultations; 
	
	
	
	//constructeur
	public AbonneOperateur(Telephone tel,Forfait nomForfait,String nomPersonne,NumeroTelephone numero){
		this.tel=tel;
		this.num = numero;
		this.nomForfait = nomForfait;
		this.nomPersonne=nomPersonne;
		BoiteSMS b1 = new BoiteSMS();
		BoiteVocale b2 = new BoiteVocale();
		this.b2 = b2;
		this.b1 = b1;
		this.dureeAppels =0;
	}
	

	
  //------------------------------------------------------------------------
  // m�thodes de l'interface GestionCommunication
  //------------------------------------------------------------------------
 

  public boolean appeler(String numero, String msgVocalSiOccupe, Date debut){
		return this.operateur.etablirCommunication(this, numero, msgVocalSiOccupe, debut);
	}

	
  public void envoyerSMS(String numero, String sms, Date dateSMS) {
	  this.operateur.posterSMS(this, numero, sms, dateSMS);
  }
 
  
  public void recevoirSMS(MessageSMS message){
	  if(estHorsLigne()){this.b1.ajouterSMS(message);}
	  else {tel.recevoirSMS(message);}
  }
  
  
  public boolean accepterAppel(String numeroAppelant)
  {
	  if (this.estLibre())
	  {
		  return this.tel.accepterAppel(numeroAppelant);
	  }
	  return false;
  }

  
  public void cloreAppel(Date fin){
	 this.operateur.cloreAppel(this, fin);
  }

  //------------------------------------------------------------------------
  // autres m�thodes
  //------------------------------------------------------------------------

  // renvoie num�ro de t�l�phone de l'abonn�
  public NumeroTelephone getNum(){return num;}
  
  
  // renvoie le t�l�phone de l'abonn�
  public Telephone getTel(){return tel;}
  
  
  //  renvoie la boiteSMS de l'abonn�
  public BoiteSMS getBoiteSMS(){return b1;}
  
  
  // renvoie la boiteVocale de l'abonn�
  public BoiteVocale getBoiteVocale(){return b2;}
  
  
  // renvoie la dur�e de l'appel
  public int getDureeAppels(){return this.dureeAppels;}
  
  
  // renvoie le nombre de sms sur la boite
  public int getNbSMS(){return b1.getNbSMS();}
  
  
  // renvoie le nom de l'abonn�
  public String getNom(){return nomPersonne;}
  
  
  // renvoie le forfait de l'abonn�
  public Forfait getForfait(){return this.nomForfait;}
  
  
  // permet de consulter la messagerie
  public void ConsulterMessagerie(){
	  this.b2.lireNouveauxMessagesVocaux();
	  this.Nbconsultations += 1;
  }
  
  
  // compte le nombre de fois o� l'abonn� consulte la messagerie
  public int NbConsultations(){return this.Nbconsultations;}
  
  
  // transf�rer sur le t�l�phone les SMS du serveur
  public void synchroniser()
  {
	  int a = this.b1.getNbSMS();
	  int i = 0;
	  
	  //Tant que tous les messages n'ont pas �t� lus
	  while (i != a){
		  MessageSMS message = this.b1.getNouveauSMS(); //on recupere les message situ�s sur le serveur
		  this.tel.getBoite().ajouterSMS(message); //envoi au t�l�phone
		  i++;
	  }
  }

  
  boolean estHorsLigne(){
    if(tel.esteteint()){return true;}
	return false;
  }

  boolean estLibre()
  {
	  if(tel.estallume()){return true;}
		return false;
  }
  



} // AbonneOperateur