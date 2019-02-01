package tp5;


import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is a skeleton for realizing a very simple database user interface in java. 
 * The interface is an Applet, and it implements the interface ActionListener. 
 * If the user performs an action (for example he presses a button), the procedure actionPerformed 
 * is called. Depending on his actions, one can implement the database connection (disconnection), 
 * querying or insert. 
 * 
 * @author zmiklos
 *
 */
public class DatabaseUserInterface extends java.applet.Applet implements ActionListener {

 private TextField mStat, m1, m2, m3, m4, m5;
 private TextArea mRes;
 private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15;
 private static final long serialVersionUID = 1L;
 private Statement stmt;
 private Connection conn;

 
 
 /**
  * This procedure is called when the Applet is initialized.
  * 
  */
 public void init ()
 {    
	 /**
	  * Definition of text fields
	  */
     //m1 = new TextField(80);
     //m1.setText("What are you going to do when the light is:");
     //m1.setEditable(false);
     mStat = new TextField(150);
     mStat.setEditable(false);
     m1 = new TextField(150);
     m2 = new TextField(150);
     m3 = new TextField(150);
     m4 = new TextField(150);
     m5 = new TextField(150);
     mRes = new TextArea(10,150);
     mRes.setEditable(false);
    
     
     
     /**
      * First we define the buttons, then we add to the Applet, finally add and ActionListener 
      * (with a self-reference) to capture the user actions.  
      */
     b1 = new Button("CONNECT");
     b2 = new Button("DISCONNECT");
     b3 = new Button("QUERY");
     b4 = new Button("Lister les clients");
     b5 = new Button("Client possedant l'enclume 1");
     b6 = new Button("Table Client");
     b7 = new Button("Table Commande");
     b8 = new Button("Table Enclume");
     b9 = new Button("Table Facture");
     b10 = new Button("Table Produit");
     b11 = new Button("INSERT in Client");
     b12 = new Button("INSERT in Commande");
     b13 = new Button("INSERT in Facture");
     b14 = new Button("INSERT in Produit");
     b15 = new Button("INSERT in Enclume");
     add(b1) ;
     add(b2) ;
     add(b3) ;
     add(b4);
     add(b5);
     add(b6);
     add(b7);
     add(b8);
     add(b9);
     add(b10);
     add(b11);
     add(b12);
     add(b13);
     add(b14);
     add(b15);
     b1.addActionListener(this);
     b2.addActionListener(this);
     b3.addActionListener(this);
     b4.addActionListener(this);
     b5.addActionListener(this);
     b6.addActionListener(this);
     b7.addActionListener(this);
     b8.addActionListener(this);
     b9.addActionListener(this);
     b10.addActionListener(this);
     b11.addActionListener(this);
     b12.addActionListener(this);
     b13.addActionListener(this);
     b14.addActionListener(this);
     b15.addActionListener(this);
     add(mStat);
     add(new Label("Input fields: ", Label.CENTER));
     add(new Label("Query results: ", Label.CENTER));
     add(mRes);
     mRes.setText("Query results");
     
     setStatus("Waiting for user actions.");
 }
 
 
 /**
  * This procedure is called upon a user action.
  * 
  *  @param event The user event. 
  */
  public void actionPerformed(ActionEvent event)
 {

	  // Extract the relevant information from the action (i.e. which button is pressed?)
	  Object cause = event.getSource();

	  // Act depending on the user action
	  // Button CONNECT
	  if (cause == b1)
	  {
		  connectToDatabase();
	  }

	  // Button DISCONNECT
	  if (cause == b2)
	  {
		  disconnectFromDatabase();
	  }

	  //Button QUERY
	  if (cause == b3)
	  {
		  queryDatabase1();
	  }

	  if (cause == b4)
	  {
		  queryDatabase1();	      
	  }

	  if (cause == b5)
	  {
		  queryDatabase2();
	  }

	  //Selectionne la table Client
	  if (cause == b6){
		  add(m1);
		  add(m2);
		  add(m3);
		  add(m4);
		  add(m5);
		  m1.setText("Inserez l'IDclient :");  //According to the database schema
		  m2.setText("Inserez l'adresse : "); //According to the database schema
		  m3.setText("Inserer le Code Postal : ");//According to the database schema
		  m4.setText("Inserer la ville : ") ;
		  m5.setText("Inserer le nom : ");
	  }

	  //Sélectionne la table Commande
	  if (cause == b7){
		  add(m1);
		  add(m2);
		  add(m3);
		  add(m4);
		  m1.setText("Inserez l'IDcommande :");  
		  m2.setText("Inserez l'IDpanier : ");
		  m3.setText("Inserez Facture_idFacture : ");
		  m4.setText("Inserez Client_idClient : ");
	  }

	  //Sélectionne la table Enclume
	  if (cause == b8){
		  add(m1);
		  add(m2);
		  add(m3);
		  add(m4);
		  add(m5);
		  m1.setText("Inserez l'IDenclume :");  
		  m2.setText("Inserez la photo : ");
		  m3.setText("Inserez le poids : ");
		  m4.setText("Inserez la taille : ");
		  m5.setText("Inserez le prix : ");
	  }

	  //Sélectionne la table Facture
	  if (cause == b9){
		  add(m1);
		  add(m2);
		  add(m3);
		  m1.setText("Inserez l'IDfacture :");  
		  m2.setText("Inserez la Ristourne : ");
		  m3.setText("Inserez l'Idsource : ");
	  }

	  //Sélectionne la table Produit
	  if (cause == b10){
		  add(m1);
		  add(m2);
		  add(m3);
		  m1.setText("Inserez l'Enclume_idEnclume :");  
		  m2.setText("Inserez la Commande_idCommande : ");
		  m3.setText("Inserez la Qte : ");
	  }
     
    if (cause == b11)
    {
    	insertDatabaseClient();
    }

    if (cause == b12)
    {
    	insertDatabaseCommande();
    }

    if (cause == b13)
    {
    	insertDatabaseFacture();
    }

    if (cause == b14)
    {
    	insertDatabaseProduit();
    }

    if (cause == b15)
    {
    	insertDatabaseEnclume();
    }

 }
 

/**
 * Set the status text. 
 * 
 * @param text The text to set. 
 */
private void setStatus(String text){
	    mStat.setText("Status: " + text);
  }

/**
 * Procedure, where the database connection should be implemented. 
 */
private void connectToDatabase(){
	// Connection à la base de donnée

	// JDBC driver name and database URL
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://anteros.istic.univ-rennes1.fr:3306/base_17011240";

	//  Database credentials
	final String USER = "user_17011240";
	final String PASS = "leoguilpain11";

	try{   
		//STEP 2: Register JDBC driver
		Class.forName(JDBC_DRIVER);

		//STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		System.out.println("Connected database successfully...");
		setStatus("Connected database successfully...");
	}
	catch (Exception e){
		setStatus("Connexion failed");
		System.out.println("Connexion failed");
	}

}


/**
 * Procedure, where the database connection should be implemented. 
 */
private void disconnectFromDatabase(){
	try{
	setStatus("Disconnected from the database");
	System.out.println("Disconnected from the database");
	} catch(Exception e){
		System.err.println(e.getMessage());
		setStatus("Disconnection failed");
	}
}

/**
 * Execute a query and display the results. Implement the database querying and the 
 * display of the results here 
 * @throws SQLException 
 */
private void queryDatabase1(){
	try{
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT nom FROM Client";
		ResultSet rs = stmt.executeQuery(sql);
		mRes.setText("la liste des noms est :\n");
		//STEP 5: Extract data from result set
		while(rs.next()){
			//Retrieve by column name
			String nom = rs.getString("nom");
			System.out.println("nom : " + nom);
			mRes.append(nom + "\n");
			setStatus("Requete effectuee");
		}
		rs.close();
		stmt.close();
		conn.close();
	}
	catch(SQLException se){
		//Handle errors for JDBC
		se.printStackTrace();
	}	catch(Exception e){
		//Handle errors for Class.forName
		e.printStackTrace();
	}	finally{
		//finally block used to close resources
		try{
			if(stmt!=null)
				stmt.close();
		}	catch(SQLException se2){
		}// nothing we can do
		try{
			if(conn!=null)
				conn.close();
		}	catch(SQLException se){
			se.printStackTrace();
		}//end finally try
	}//end try
}


private void queryDatabase2(){
	try{
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT adresse,nom FROM Produit, Enclume, Commande, "
				+ "Client WHERE Enclume_idEnclume = 1 AND Produit.Enclume_idEnclume = Enclume.idEnclume "
				+ "AND Produit.Commande_idCommande = Commande.idCommande AND Commande.Client_idClient = Client.idClient";
		ResultSet rs = stmt.executeQuery(sql);

		//STEP 5: Extract data from result set
		while(rs.next()){
			//Retrieve by column name
			String nom = rs.getString("nom");
			String adresse = rs.getString("adresse");
			mRes.setText("Le client " + nom + " a achete l'enclume 1 et habite à " + adresse);
			setStatus("Requete effectuee");
		}
		rs.close();
		stmt.close();
		conn.close();
	}
	catch(SQLException se){
		//Handle errors for JDBC
		se.printStackTrace();
	}	catch(Exception e){
		//Handle errors for Class.forName
		e.printStackTrace();
	}	finally{
		//finally block used to close resources
		try{
			if(stmt!=null)
				stmt.close();
		}	catch(SQLException se2){
		}// nothing we can do
		try{
			if(conn!=null)
				conn.close();
		}	catch(SQLException se){
			se.printStackTrace();
		}//end finally try
	}//end try
}



private void insertDatabaseClient(){
	try{
		stmt = conn.createStatement();
		int idClient = Integer.parseInt(m1.getText());
		String adresse = m2.getText();
		int CP = Integer.parseInt(m3.getText());
		String Ville = m4.getText();
		String Nom = m5.getText();
		String sql = "INSERT INTO Client " + 
						"VALUES( " + idClient + ", \"" + adresse + "\", " + CP + ", \""+ Ville + "\", \"" + Nom + "\")";
		System.out.println(sql);
		stmt.executeUpdate(sql);
		setStatus("Inserting --( " + idClient + ", " + adresse + ", " + CP + ", " + Ville + ", " + Nom + ")-- to the database");
		} catch(Exception e){
			System.err.println(e.getMessage());
			setStatus("Insertion failed");
		}
}

private void insertDatabaseCommande(){
	try{
		stmt = conn.createStatement();
		int idCommande = Integer.parseInt(m1.getText());
		int idPanier = Integer.parseInt(m2.getText());
		int Facture_idFacture = Integer.parseInt(m3.getText());
		int Client_idClient = Integer.parseInt(m4.getText());
		
		String sql = "INSERT INTO Commande " + 
						"VALUES( " + idCommande + ", " + idPanier + ", " + Facture_idFacture + ", " + Client_idClient + ")";
		System.out.println(sql);
		stmt.executeUpdate(sql);
		setStatus("Inserting --( " + idCommande + ", " + idPanier + ", " + Facture_idFacture + ", " + Client_idClient + ")-- to the database");
		} catch(Exception e){
			System.err.println(e.getMessage());
			setStatus("Insertion failed");
		}
}

private void insertDatabaseFacture(){
	try{
		stmt = conn.createStatement();
		int idFacture = Integer.parseInt(m1.getText());
		int Ristourne = Integer.parseInt(m2.getText());
		int idSource = Integer.parseInt(m3.getText());
		String sql = "INSERT INTO Facture " + 
						"VALUES( " + idFacture + ", " + Ristourne + ", " + idSource + ")";
		System.out.println(sql);
		stmt.executeUpdate(sql);
		setStatus("Inserting --( " + idFacture + ", " + Ristourne + ", " + idSource + ")-- to the database");
		} catch(Exception e){
			System.err.println(e.getMessage());
			setStatus("Insertion failed");
		}
}

private void insertDatabaseEnclume(){
	try{
		stmt = conn.createStatement();
		int idEnclume = Integer.parseInt(m1.getText());
		String Photos = m2.getText();
		int Poids = Integer.parseInt(m3.getText());
		int Taille = Integer.parseInt(m4.getText());
		int Prix = Integer.parseInt(m5.getText());
		String sql = "INSERT INTO Enclume " + 
				"VALUES( " + idEnclume + ", \"" + Photos + "\", " + Poids + ", " + Taille + ", " + Prix + ")";
		System.out.println(sql);
		stmt.executeUpdate(sql);
		setStatus("Inserting --( " + idEnclume + ", " + Photos + ", " + Poids + ", " + Taille + ", " + Prix + ")-- to the database");
	} catch(Exception e){
		System.err.println(e.getMessage());
		setStatus("Insertion failed");
	}
}

private void insertDatabaseProduit(){
	try{
		stmt = conn.createStatement();
		int Enclume_idEnclume = Integer.parseInt(m1.getText());
		int Commande_idCommande = Integer.parseInt(m2.getText());
		int Qte = Integer.parseInt(m3.getText());
		String sql = "INSERT INTO Produit " + 
				"VALUES( " + Enclume_idEnclume + ", " + Commande_idCommande + ", " + Qte + ")";
		System.out.println(sql);
		stmt.executeUpdate(sql);
		setStatus("Inserting --( " + Enclume_idEnclume + ", " + Commande_idCommande + ", " + Qte + ")-- to the database");
	} catch(Exception e){
		System.err.println(e.getMessage());
		setStatus("Insertion failed");
	}
}
}