// GRECO Vincent & GUILPAIN L�o

package messagerie;

import java.util.Random;

public class Date {
	
	//attributs
	private int Date;
	
	//constructeur
	public Date(){
		Random x = new Random();
		Date = x.nextInt(31);
	}
	
	//m�thode
	
	//renvoie la date
	public int getDate(){return Date;}

}
