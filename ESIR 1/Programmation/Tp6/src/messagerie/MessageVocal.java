// GRECO Vincent & GUILPAIN L�o

package messagerie;

public class MessageVocal extends AbstractMessage{

	//constructeur
	public MessageVocal(String message, AbstractCommMessage comMessage){
		super(message, comMessage);
	}
	
	
	//renvoie le message
	public String getMessage(){return message;}
}
