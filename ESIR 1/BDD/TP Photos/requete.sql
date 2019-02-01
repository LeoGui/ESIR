	
INSERT INTO client
VALUES(1,'Theo&Co','66 rue des Monts','0658743689','Faye Danjou');

INSERT INTO client
VALUES(2,'Vincent Corporation','35 avenue du professeur Charles Foulon','0648742360','Rennes');

INSERT INTO facture
VALUES(3,2,5,100,0,'14/02/17');

INSERT INTO facture
VALUES(6,1,4,200,0,'14/02/17');

INSERT INTO produit
VALUES(4,'salade','150','3');
	
INSERT INTO produit
VALUES(5,'radis','40','2');



.print 'Dans quelle ville habite le client Vincent Corporation ? :'
SELECT ville 
FROM client 
WHERE nom_c = 'Vincent Corporation';

.print 'Quand Theo&Co ont-ils acheté des salades ? : '
SELECT date_f
FROM facture Fa,produit P,client Cl
WHERE nom_p = 'salade' AND nom_c = 'Theo&Co' AND Fa.num_p = P.num_p AND Fa.num_c = Cl.num_c;


