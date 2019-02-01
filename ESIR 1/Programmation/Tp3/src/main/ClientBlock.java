package main;

import java.util.Scanner;
import java.util.*;

import tableau.Block;
import tableau.Tableau2x;
import types.Tableau;

public class ClientBlock {
	
	public static void main (String[]args)
	
    { Scanner entree = new Scanner (System.in);
	/*System.out.print ("Saisissez un entier positif   :  ");
	int nb = entree.nextInt();*/
	
	// tableau de tous les nombres premiers < 37
    Block <Integer >lesPremiers = new Block<Integer>(1000);
	lesPremiers.push_back(2);
	lesPremiers.push_back(3);
	lesPremiers.push_back(5);
	lesPremiers.push_back(7);
	lesPremiers.push_back(11);
	lesPremiers.push_back(13);
	lesPremiers.push_back(17);
	lesPremiers.push_back(19);
	lesPremiers.push_back(23);
	lesPremiers.push_back(29);
	lesPremiers.push_back(31);
	
	/*System.out.println (" Le nombre est t-il premier ?   " + estPremier (nb, lesPremiers));*/
	int nb=2;
//	System.out.print ("Saisissez la valeur de nb  :  ");
//	int maxaleatoire = entree.nextInt();
	estPremier (nb, lesPremiers);
	
	//Fonction qui calcule les nombres premiers
	System.out.print ("Saisissez la valeur de N  :  ");
	int valmax = entree.nextInt();
	assert valmax>=2: "N doit �tre superieur ou �gal � 2"; 
	Block <Integer > tabC = new Block<Integer>(10);
	int dernier = CalculerNombresPremiers(valmax,tabC);
	System.out.println ("Le dernier entier calcul� est  :  " + dernier);
	
	// Fonction qui cr�e et initialise un tableau de nombres al�atoires
	Block <Integer > tableaual = new Block<Integer>(dernier);
	tableaual = remplirHasard(dernier);
    afficher(tableaual);
    
    // Fonction qui elimine d'un tableau les �l�ments d�j�pr�sent dans un autre
    System.out.println ("il y a   " + eliminerPresents(tableaual,tabC) + "  �l�ment(s) qui ont �t� supprim�s");
    afficher(tableaual);
    
    entree.close();
    }

	
	// D�termine si n est un nombre premier
    static boolean estPremier(int n, Block<Integer> nombresPremiers)
    { 
    	assert n >= 2;
    	int i = 0;
    	while (i < nombresPremiers.size())
    	{ 
    		if(n % nombresPremiers.get(i) == 0 && n!=nombresPremiers.get(i))      //le nombre n est divisible par l'�l�ment i du tableau
    		{return false;}
    		else { i = i + 1;}
    	}
    	return true;
    }
	
    

   
	 static int CalculerNombresPremiers(int N,Block<Integer>tabC){
		for (int nb =2 ; nb<N ; nb++)
		{ 
			if (estPremier(nb,tabC)){
				tabC.push_back(nb);
			}
		    if (tabC.full()) {return nb;}
		}
		return N;
	 }
	 
	 // Affiche les �l�ments du tableau
	 static void afficher(Block<Integer>tabvide){
		 int i = 0;
		 while(i <tabvide.size())
		 { 
			 System.out.println(" En position " + i + " le tableau vaut : " + tabvide.get(i));
		 i++;
		  }
 
	 }
	 
	 // Remplis de fa�on al�atoire un tableau initialement vide
	static Block<Integer> remplirHasard(int nb){
		Block<Integer>tab = new Block<Integer>(nb);
		int i = 0;
		while (i != nb){
			Random r = new Random();
			int nbAleatoire = r.nextInt(nb);  //int valeur = r.nextInt(valeurMax - valeurMin) 
			tab.push_back(nbAleatoire);
			i = i + 1;
		}
		return tab;
	}
	
	
	// Fonction qui d�termine si l'entier nb se trouve dans le tableau t
	static boolean contains (int nb,Block <Integer> t){
		int i = 0;
		while (i < t.size()){
			if (nb != t.get(i)){
				i++;
			}
			else {return true;}
			}
		return false;
		}
	

	// Fonction qui elimine d'un tableau les �l�ments d�j�pr�sent dans un autre
	static int eliminerPresents(Block<Integer>t1,Block<Integer>t2){
		int i=0;
		int cpt=0;
		while (i != t1.size()){
			if (!contains(t1.get(i),t2)){
				i++;
			}
			else { t1.set(i,  t1.get(t1.size()-1));
			t1.pop_back();
			cpt++;
			}
	    }
		return cpt;
	}
}