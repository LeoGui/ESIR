// GRECO Vincent et GUILPAIN L�o

import types.ABinHuffman;
import types.ListeABH;
import types.*;

import java.util.ListIterator;

import outilsHuffman.OutilsHuffman;

/**
 * R�alisation du codage d'un texte par la méthode de Huffman
 */

public class CodageHuffman
{
  public static void main (String[] args)
  {
    //------------------------------------------------------------------------
    // 0. Saisir le nom du fichier à coder (À FAIRE)
    //------------------------------------------------------------------------
    String nomFichier = "exemple.txt";

    //------------------------------------------------------------------------
    // 1. Lire le texte (DONNÉ)
    //------------------------------------------------------------------------
    char [] texte = OutilsHuffman.lireFichier(nomFichier);

    //------------------------------------------------------------------------
    // 2. Calculer la table des fréquences des caractères (À FAIRE)
    //------------------------------------------------------------------------
    int [] tableFrequences = calculerFrequences(texte);

    //------------------------------------------------------------------------
    // 3. Enregistrer la table de fréquences dans le fichier de sortie (DONNÉ)
    //------------------------------------------------------------------------
    OutilsHuffman.enregistrerTableFrequences(tableFrequences, nomFichier + ".code");

    //------------------------------------------------------------------------
    // 4. Construire l'arbre de codage de Huffman (DONNÉ - À FAIRE)
    //------------------------------------------------------------------------
    ABinHuffman arbreCodageHuffman = construireArbreHuffman(tableFrequences);

    //------------------------------------------------------------------------
    // Afficher l'arbre de codage de Huffman (DÉJÀ FAIT)
    //------------------------------------------------------------------------
    System.out.println("Arbre de Huffman associé au texte " + nomFichier);
    DecodageHuffman.afficherHuffman(arbreCodageHuffman);

    //------------------------------------------------------------------------
    // 5. Construire la table de codage associée (À FAIRE)
    //------------------------------------------------------------------------
    String [] tablecodage = construireTableCodage(arbreCodageHuffman);

    //------------------------------------------------------------------------
    // 5.1. afficher la table de codage (À FAIRE)
    //------------------------------------------------------------------------
    System.out.println("Table de codage associée au texte " + nomFichier);
    afficherTableCodage(tablecodage);

    //------------------------------------------------------------------------
    // 6. coder le texte avec l'arbre de Huffman (À FAIRE)
    //------------------------------------------------------------------------
    StringBuilder texteCode = coderTexte(texte, tablecodage);

    //------------------------------------------------------------------------
    // 7. enregistrer le texte codé (DONNÉ)
    //------------------------------------------------------------------------
    OutilsHuffman.enregistrerTexteCode(texteCode, nomFichier + ".code");

    //------------------------------------------------------------------------
    // xx. calculer et afficher les stats (À FAIRE)
    //------------------------------------------------------------------------
    // calculer la taille du fichier non codé
    // calculer la taille du fichier codé

  }

  /**
   * 2. calculer la fréquence d'apparition de chaque caractère
   * @param  tcar tableau des caractères du texte
   * @return tableau de fréquence des caractères, indicé par les caractères
   */
  public static int [] calculerFrequences(char [] tcar)
  { 
	  int [] tab = new int[256];
	  for (int i=0; i < tcar.length ; i++){
	  int valeur = (int) tcar[i];
	  tab[valeur]=tab[valeur]+1;
	  System.out.println(valeur + " -> "+ tcar[i] + " - fr�quence : "+ tab[valeur]);
}
	  return tab;
  }
  
  /**
   * 4. construire un arbre de codage de Huffman par sélection et combinaison
   * des éléments minimaux
   * @param tableFrequences table des fréquences des caractères
   * @return arbre de codage de Huffman
   */
  
  public static ABinHuffman construireArbreHuffman(int [] tableFrequences)
  {
	  //creation de la liste a partir de la table 
	 ListeABH liste = faireListeAbinHuffman(tableFrequences);
	 
	  //creation des ABinHuffman
	  ABinHuffman arbreH = new ABinHuffman();
	  ABinHuffman arbreG = new ABinHuffman();
	  ABinHuffman arbreD = new ABinHuffman();
	  
	  //si la liste comprend un seul ABinHuffman
	  while (liste.size() > 1 ) 
	  { 
		  arbreG=liste.getFirst(); //ajout du 1er terme (int)dans ABG
		  liste.removeFirst(); //supression du 1er terme
		  arbreD=liste.getFirst(); //ajout du 1er terme dans ABD
		  liste.removeFirst(); //suppression du 1er terme
		  Couple<Character,Integer> C1 = new Couple(".",(arbreG.getValeur().deuxieme()+arbreD.getValeur().deuxieme()) ); //contruction des racines de ABG et ABD
		  arbreH = OutilsHuffman.consArbre(C1, arbreG,arbreD); //construction de l'arbre
		  ajoutertrier(liste, arbreH); //appel de la fonction ajoutertrier
	  }
	  return arbreH;
	  
  }

  /**
   * 4.1 Faire une liste triée dont chaque élément est un arbreBinaire<br>
   * comprenant un unique sommet dont l'étiquette est un couple
   * <caractère, fréquence>, trié par fréquence croissante
   * @param tableFrequences : table des fréquences des caractères
   * @return		      la liste triée
   */
  
  private static ListeABH faireListeAbinHuffman(int [] tableFrequences)
  {
	  //Cr�ation de la liste d'arbre de Huffman
	  ListeABH liste = new ListeABH ();
	  
	  //Rentrer les valeurs
	  for (int i= 0; i < tableFrequences.length ; i++){
		  if (tableFrequences[i] != 0){
			  
			//Cr�ation d'un arbre de Huffman
			  ABinHuffman courant = new ABinHuffman();
			  Couple<Character, Integer> C1 = new Couple<Character,Integer>((char)i, tableFrequences[i]);
			  courant.setValeur(C1);
			  
			  //Placer l'ABin � la bonne place
			  ajoutertrier(liste,courant);
		  }
	  }
	  return liste;
  }
  
  
  //Fonction qui permet de placer l'arbre � la bonne place dans la liste
  
  private static void ajoutertrier(ListeABH listetriee, ABinHuffman courant) 
  {
	  
	// cr�ation de l'iterateur 
	  ListIterator<ABinHuffman> it = listetriee.listIterator();
		  
	  while (it.hasNext())
	  {
			if (it.next().getValeur().deuxieme() > courant.getValeur().deuxieme())
			{
				it.previous();
				it.add(courant);
				return;
			}
	  }
	  it.add(courant);
	  
  }

  
  /**
   * 5. construire la table de codage correspondant � l'arbre de Huffman
   * @param abinHuff : arbre de Huffman
   * @return table de (d�)codage indic� par les caract�res
   */
  public static String [] construireTableCodage(ABinHuffman abinHuff)
  {
	  //creation de la table de codage
	  String [] tableCodage = new String [256];
	  construireTableCodage(abinHuff, "",tableCodage);
	  return tableCodage;
  }
  
  public static void construireTableCodage (ABinHuffman a, String code, String [] tableCodage){
	  if (a.estFeuille()){ tableCodage[(int)a.getValeur().premier()]=code;}
	  else {
		  construireTableCodage(a.filsGauche(), code+'0', tableCodage);
		  construireTableCodage(a.filsDroit(), code+'1', tableCodage);
	  }
  }

  /**
   * 5.1. Afficher la table de codage associ�e au texte
   * @param tablecodage : table de codage associ�e au texte
   */
  public static void afficherTableCodage(String [] tablecodage)
  {
	  int i = 0;
	  while (i< tablecodage.length){
		  System.out.println("l'�l�ment d'indice  "+ (char)i + "  est   " + tablecodage[i]);
		  i++;
	  }
  }

  /**
   * 6. Coder un texte � l'aide de la table de codage
   * @param texte � coder
   * @param tablecodage : table de codage associée au texte
   * @return texte codé
   */
  public static StringBuilder coderTexte(char [] texte, String [] tablecodage)
  {
	  //cr�ation du texte cod�
	  StringBuilder code = new StringBuilder();
	  
	  //cr�ation de l'iterateur du tableau du texte
	  int i = 0;
	  
	  //codage du texte
	  while (i < texte.length){
		  code.append(tablecodage[(int)texte[i]]);
		  i++;
	  }
	  
	  return code;
  }
}// CodageHuffman












