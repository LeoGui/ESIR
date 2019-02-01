// GRECO Vincent & GUILPAIN Léo

package messagerie;

public class AbstractMessage{
	
	//Attibuts
	protected String message;
	protected AbstractCommMessage messageAb;

	//Constructeur 
	public AbstractMessage(String message, AbstractCommMessage messageAb) {
		this.messageAb =  messageAb;
		this.message = message;
	}
}