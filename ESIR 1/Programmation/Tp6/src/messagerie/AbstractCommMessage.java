// GRECO Vincent & GUILPAIN L�o

package messagerie;

import java.util.Date;

public abstract class AbstractCommMessage extends AbstractCommunication{
	//attributs

	protected AbstractMessage MessAbstrait;
	
	//constructeur
	public AbstractCommMessage(Date debut, NumeroTelephone recepteur, NumeroTelephone emetteur) {
			super(debut, emetteur, recepteur);
			
		}

	}

