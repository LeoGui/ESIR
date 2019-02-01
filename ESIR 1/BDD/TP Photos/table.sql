CREATE TABLE client(
	num_c INT PRIMARY KEY,
	nom_c TEXT,
	adresse TEXT,
	telephone TEXT,
	ville TEXT);
	
CREATE TABLE facture(
	num_f INT PRIMARY KEY,
	num_c INT,
	num_p INT,
	qte INT,
	ristourne INT
	date_f TEXT);
	
CREATE TABLE produit(
	num_p INT PRIMARY KEY,
	nom_p TEXT,
	prix INT,
	TVA INT);
	
	

