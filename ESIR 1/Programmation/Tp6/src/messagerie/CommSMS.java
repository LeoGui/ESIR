// GRECO Vincent & GUILPAIN L�o

package messagerie;

import java.util.Date;

public class CommSMS extends AbstractCommMessage {

	//constructeur
	public CommSMS(Date debut, NumeroTelephone recepteur, NumeroTelephone emetteur){
		super(debut,recepteur,emetteur);
	}
}

