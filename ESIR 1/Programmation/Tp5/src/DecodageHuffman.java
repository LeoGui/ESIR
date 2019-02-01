// GRECO Vincent et GUILPAIN L�o

import types.ABinHuffman;
import outilsHuffman.OutilsHuffman;

/**
 * R�alisation du d�codage d'un texte par la m�thode de Huffman
 */

public class DecodageHuffman
{
  public static void main (String[] args)
  {
    //------------------------------------------------------------------------
    // 0. Saisir le nom du fichier � d�coder (� FAIRE)
    //------------------------------------------------------------------------
    String nomFichier = "albatros.txt.code";

    //------------------------------------------------------------------------
    // 1. Lire et construire la table de fr�quences (DONN�)
    //------------------------------------------------------------------------
    int [] tableFrequences = OutilsHuffman.lireTableFrequences(nomFichier);

    //------------------------------------------------------------------------
    // 2. Construire l'arbre de Huffman (DONN�)
    //------------------------------------------------------------------------
    ABinHuffman arbreHuffman =
    OutilsHuffman.construireArbreHuffman(tableFrequences);

    //------------------------------------------------------------------------
    // 2.1 afficher l'arbre de Huffman (� FAIRE)
    //------------------------------------------------------------------------
    System.out.println("Arbre de Huffman associ� au texte " + nomFichier);
    afficherHuffman(arbreHuffman);

    //------------------------------------------------------------------------
    // 3. Lire le texte cod� (DONN�)
    //------------------------------------------------------------------------
    String texteCode = OutilsHuffman.lireTexteCode(nomFichier);

    //------------------------------------------------------------------------
    // 4. D�coder le texte (� FAIRE)
    //------------------------------------------------------------------------
    StringBuilder texteDecode = decoderTexte(texteCode, arbreHuffman);

    //------------------------------------------------------------------------
    // 5. Enregistrer le texte d�code (DONN�)
    //------------------------------------------------------------------------
    System.out.println("texte d�cod�:\n\n" + texteDecode);
    OutilsHuffman.enregistrerTexte(texteDecode, nomFichier + ".decode");
  }

  /**
   * 4. d�coder une cha�ne (non vide) encod�e par le codage de Huffman
   * @param texteCode    : cha�ne de "0/1" � d�coder
   * @param arbreHuffman : arbre de (d�)codage des caract�res
   */
  public static StringBuilder decoderTexte(String texteCode, ABinHuffman arbreHuffman)
  {
	  ABinHuffman complet = arbreHuffman;
	  ABinHuffman courant = complet;
	  StringBuilder phrase = new StringBuilder();
	 int i=0;
	 // for(int i=0;i<texteCode.length();i++){
	 while(i<texteCode.length()){
		 while (!courant.estFeuille()){
			 if(texteCode.charAt(i)=='1'){
				 courant = courant.filsDroit();
				 i++;
			 }
			 else {courant = courant.filsGauche();i++;}
		 }
		 
		 char lettre =courant.getValeur().premier();
		 courant = complet;
		 phrase.append(lettre);
	 }
	 
	  return phrase;
  }

  /**
   * 2.1 afficher un arbre de Huffman
   * @param a : arbre binaire de Huffman
   */
  public static void afficherHuffman(ABinHuffman a)
  {
	  String code = "";
	  afficherHuffman(a,code);
  }
  
  public static void afficherHuffman(ABinHuffman a, String code){
	  if (a.estFeuille()){ System.out.println(" < " + a.getValeur() + "> " + " : " + code);}
	  else{
		  afficherHuffman(a.filsGauche(), code+'0');
		  afficherHuffman(a.filsDroit(), code+'1');
	  }
}
	  
	  


 }
 
 // DecodageHuffman