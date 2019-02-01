// GRECO Vincent & GUILPAIN L�o

package messagerie;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BoiteSMS {
	//attributs
	private List<MessageSMS>boiteSMS;
	private int nbSMS;
	
	
	//constructeurs
	public BoiteSMS(){
		List<MessageSMS>boiteSMS = new ArrayList<MessageSMS>();
		this.boiteSMS =boiteSMS;
		this.nbSMS = 0;
	}
	
	//m�thodes
	
	//ajouter un MessageSMS dans la boiteSMS
	public void ajouterSMS(MessageSMS message){
		this.boiteSMS.add(message);
		this.nbSMS = nbSMS+1;
	}
	
	
	//renvoie le nombre de message dans la boiteSMS
	public int getNbSMS(){return this.nbSMS;}
	
	
	//renvoie le message 
	public MessageSMS getMessage(MessageSMS sms){
		return sms;
	}
	

	//supprime tous les sms pr�sent dans la boite
	public void supprimer(){boiteSMS.clear();}

	
	//renvoie les nouveaux messages
	public MessageSMS getNouveauSMS()
	{
		ListIterator <MessageSMS> it = boiteSMS.listIterator();
		while(it.hasNext())
		{
			MessageSMS cur = it.next();
			it.remove();
			return cur;
		}
		return null;
	}
	
}
