// GRECO Vincent & GUILPAIN L�o

package messagerie;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Operateur{
/**
 * Un Op�rateur g�re des abonn�s et des communications
 */
	//attributs 
	private List<AbonneOperateur> liste;
	private String nomOperateur;
	private List<Appel> listeAp;

	
	//Constructeur
	public Operateur(String nomOperateur){
		List<AbonneOperateur> liste = new ArrayList <AbonneOperateur>();
		this.liste = liste;
		this.nomOperateur = nomOperateur;
		List<Appel> listeAp = new LinkedList <Appel>();
		this.listeAp = listeAp;
	}	
	
	
  /**
   * Une personne souscrit un abonnement et re�oit un t�l�phone
   */
  public Telephone souscrire(String nomPersonne, String nomForfait) {
    NumeroTelephone numero = new NumeroTelephone();
    Telephone tel = null;
    Forfait nomF = new Forfait(nomForfait);
    AbonneOperateur abonne = new AbonneOperateur(tel,nomF,nomPersonne,numero);
    Telephone telephone = new Telephone(abonne);
    liste.add(abonne);
    return telephone;
  }


	/*	
   * �tablir une communication
   * @param emetteur
   * @param numeroDestinataire
   * @param msgVocal : message en cas d'indisponibilit�
   * @param dateAppel
   * @return vrai si la communication a �t� �tablie
   */
  public boolean etablirCommunication(AbonneOperateur emetteur, String numeroDestinataire,String msgVocal,Date dateAppel)
  {
	  ListIterator <AbonneOperateur> it = liste.listIterator();
	  while(it.hasNext())
	  {
		  AbonneOperateur recepteur = it.next();
		  NumeroTelephone numemet = emetteur.getNum(); // on r�cup�re le numero de telephone de l'emetteur
		  String Numemetteur = numemet.getNum(); // on r�cup�re le numero de telephone(String) de l'emetteur

		  if (numemet.equals(recepteur.getNum())){ //recepteur est connu par l'op�rateur

			  //si le recepteur est libre
			  if (recepteur.estLibre()){

				  //si le recepteur accepte l'appel la communication est effectu�e
				  if (recepteur.accepterAppel(Numemetteur)){
					  System.out.println("Appel en cours");
					  Appel appel = new Appel(emetteur.getNum(), recepteur.getNum(), dateAppel);
					  this.listeAp.add(appel); //ajout � la liste des appels en cours
					  return true;
				  }

				  //sinon la communication ne se fait pas
				  else{
					  System.out.println("Pas de communication");
					  AbstractCommMessage comMessage = new CommMessageVocal(dateAppel,recepteur.getNum(),emetteur.getNum());
					  MessageVocal message = new MessageVocal(msgVocal,comMessage);
					  recepteur.getBoiteVocale().ajouterMesVoc(message); //laisser un message sur la boite vocale
				  }
			  }
			  
			  //si le recepteur est indisponible
			  else if(recepteur.estHorsLigne()) {
				  System.out.println("Correspondant indisponible");
				  AbstractCommMessage comMessage = new CommMessageVocal(dateAppel,recepteur.getNum(),emetteur.getNum());
				  MessageVocal message = new MessageVocal(msgVocal,comMessage);
				  recepteur.getBoiteVocale().ajouterMesVoc(message);
			  }

		  }

		  //recepteur inconnu par l'op�rateur
		  else {System.out.println("Utilisateur inconnu");}
	  }
	  return false;
  }


  /**
   * poster un SMS
   * @param emetteur
   * @param numeroDestinataire
   * @param sms : le texte du SMS
   * @pamra dateEnvoi
   */
  public void posterSMS(AbonneOperateur emetteur, String numeroDestinataire, String sms, Date dateEnvoi){
	  int i = 0;
	  AbonneOperateur recepteur1 = getAbonne(numeroDestinataire);
	  AbstractCommMessage comMessage = new CommSMS(dateEnvoi, emetteur.getNum(), recepteur1.getNum());
	  MessageSMS message = new MessageSMS(sms, comMessage);

	  //Tant qu'on a pas atteint le max de la liste et tant qu'on a pas trouv� l'abonn� en question.
	  while ( (i<liste.size()) && (!numeroDestinataire.equals(liste.get(i).getNum().getNum()))) {

		  //si le recepteur fait partie de la liste des abonn�s
		  if(numeroDestinataire.equals(liste.get(i).getNum().getNum())) {
			  AbonneOperateur recepteur2 = liste.get(i);
			  BoiteSMS br = recepteur2.getBoiteSMS();
			  BoiteSMS be = emetteur.getBoiteSMS();
			  br.ajouterSMS(message);
			  be.ajouterSMS(message);
			  System.out.println("Message Envoy� !");
			  message = null;
			  break;
		  }
		  i = i+1;
	  }
  }

  /**
   * un abonn� met fin � une communication
   * @param abonne : celui qui cl�t
   * @param date de fin de communication
   */
  public void cloreAppel(AbonneOperateur abonne, Date fin){
	  Appel appel = getAppel(abonne); //retrouver l'appel correspondant � l'abonn�
	  appel.terminerAppel(fin); //terminer l'appel et l'appel s'est termin� � la date fin 
  }
  
  
  //permet de renvoyer la facture de l'abonn�
  public void facturation(AbonneOperateur abonne){
	  System.out.println("Facture de : "+abonne.getNom());
	  System.out.println("Montant : "+getMontantCom(abonne));
  }
  
  
  //renvoie le montant total de la facture
  public double getMontantCom(AbonneOperateur abonne){
	  double montanttotal = 0;
	  double minutesappels = abonne.getDureeAppels();
	  double montant1 = 0; //montant "1h"
	  double montantacte = 0; //montant "acte"
	  double montantmes = abonne.getNbSMS(); //montant messages
	  double consultations = abonne.NbConsultations() * 0.07; //prix relatif aux consultations de la boite vocale
	  String forfait = abonne.getForfait().getNom();

	  //si la duree de l'appel depasse 60 minutes
	  if (minutesappels > 60){ 
		  montant1 = abonne.getDureeAppels() - 60; //minutes suppl�mentaires
		  montant1 = montant1 * 0.15;
	  }


	  else{montantacte = minutesappels * 0.15;}

	  //l'abonne envoie plus d'un message
	  if (montantmes > 0){montantmes = montantmes * 0.07;}


	  //si le forfait est : "illimit�"
	  if (forfait.equals("illimit�")){montanttotal = 40;}

	  //si le forfait est : "1h"
	  else if (forfait.equals("1h")){montanttotal = 20 + montant1 + montantmes + consultations; } // on rajoute les frais supplementaires

	  //si le forfait est : "a l'acte"
	  else if (forfait.equals("a l'acte")){montanttotal = montantacte + montantmes + consultations;}

	  return montanttotal;
  }
  
  
  //renvoie l'appel correspondant � un abonn�
  public Appel getAppel(AbonneOperateur abonne){
	  ListIterator <Appel> it= this.listeAp.listIterator();
	  while(it.hasNext()){
		  Appel cur = it.next();
		  
		  //abonn� emetteur ou recepteur
		  if(cur.getEmetteur().equals(abonne) || cur.getRecepteur().equals(abonne)){return cur;}
	  }
	  return null;
  }
  
  
  //renvoie l'abonne en fonction du num�ro
  public AbonneOperateur getAbonne(String telephone){
	  ListIterator <AbonneOperateur> it= liste.listIterator();
	  while(it.hasNext()){
		  AbonneOperateur cur = it.next();

		  //abonn� emetteur ou recepteur
		  if(cur.getNum().equals(telephone)){return cur;}
	  }
	  return null;
  }
  
  
  //renvoie la liste d'abonn�
  public List<AbonneOperateur> getListe(){return liste;}
  
  
  //renvoie le nom de l'operateur
  public String getNomOperateur(){return nomOperateur;}
} // Operateur