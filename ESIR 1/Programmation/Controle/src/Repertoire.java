
public interface Repertoire {

	public boolean estPresent(String nom);
	
	public String getNumero(String Nom);
	
	public void enregistrer(String nom, String numero);
	
	public void modifier(String nom, String numero);
	
	public void supprimer(String nom);
}
