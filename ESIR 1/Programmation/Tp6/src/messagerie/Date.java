// GRECO Vincent & GUILPAIN Léo

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
	
	//méthode
	
	//renvoie la date
	public int getDate(){return Date;}

}
