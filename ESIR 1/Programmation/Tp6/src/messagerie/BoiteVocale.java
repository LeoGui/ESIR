// GRECO Vincent & GUILPAIN L�o

package messagerie;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



public class BoiteVocale {
		//attributs
		private List<MessageVocal>boiteVocale;
		private int nbMesVoc;
		
		
		//constructeurs
		public BoiteVocale(){
			List<MessageVocal>boiteVocale = new ArrayList<MessageVocal>();
			this.boiteVocale =boiteVocale;
			this.nbMesVoc = 0;
		}
		
		//m�thodes
		
		//ajouter un MessageVoc dans la boiteVocale
		public void ajouterMesVoc(MessageVocal message){
			this.boiteVocale.add(message);
			this.nbMesVoc = nbMesVoc+1;
		}
		
		
		//renvoie le nombre de message dans la boiteSMS
		public int getNbMesVoc(){return this.nbMesVoc;}
		
		
		//lis les nouveaux messages
		public void lireNouveauxMessagesVocaux()
		{
			ListIterator <MessageVocal> it = boiteVocale.listIterator();
			if (boiteVocale.isEmpty())
			{
				System.out.println("Vous n'avez pas de nouveaux messages vocaux");
			}
			else
			{
				while(it.hasNext())
				{
					MessageVocal mes = it.next();
					System.out.println(mes.getMessage()); //affichage du message
				}
			}
		}
		
		
		// renvoie le message
		public MessageVocal getMessage(MessageVocal messageVoc){return messageVoc;}
		
		
}

