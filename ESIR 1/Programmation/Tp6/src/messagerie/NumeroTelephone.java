// GRECO Vincent & GUILPAIN L�o

package messagerie;

public class NumeroTelephone {
	//attributs
	protected String numero;
	private int nombre;
	private int i=0;

	//constructeur
	public NumeroTelephone(){
		nombre = i;
		i++;	
		numero = ("+33") + nombre;
	}

	//renvoie le num�ro de t�l�phone
	public String getNum(){return numero;}
}