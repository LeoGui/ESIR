// GRECO Vincent & GUILPAIN Léo

package messagerie;

import java.util.Date;

public class CommMessageVocal extends AbstractCommMessage {

	//constructeur
	public CommMessageVocal(Date debut, NumeroTelephone recepteur, NumeroTelephone emetteur){
		super(debut,recepteur,emetteur);
	}
}
