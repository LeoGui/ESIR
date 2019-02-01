import os

def GetCanal():
	f = os.popen('iwlist wlan0 scan')
	ESSIDs = []
	Channels = []
	Qualities = []
	

	# On cree 1 tableau par canal avec comme valeur initiale 0
	a1=[0]
	a2=[0]
	a3=[0]
	a4=[0]
	a5=[0]
	a6=[0]
	a7=[0]
	a8=[0]
	a9=[0]
	a10=[0]
	a11=[0]
	a12=[0]
	a13 =[0]

	i =0

	for line in f:
		if line and line.strip():
			ch = line.replace(':',' ').replace('/',' ').replace('=',' ').split()

			if ch[0] in ['ESSID']: #si le premier terme est ESSID
				ESSIDs.append(ch[1]) #on ajoute le deuxieme terme dans le tableau
				
			if ch[0] in ['Quality']: #si le premier terme est Quality
				Qualities.append(ch[1]) #on ajoute le deuxieme terme dans le tableau

			if ch[0] in ['Channel']: #si le premier mot est Channel
				Channels.append(ch[1]) #on ajoute le deuxieme terme dans le tableau
	
	
	#On trouve la longueur du tableau Qualities
	longueur = len(Qualities);
	

	
	#On regarde la valeur de chaque canal, en fonction de la valeur, on ajoute la valeur de la qualite
	while i != longueur-1:
		if Channels[i] =='1': # si la valeur du canal vaut 1
			a1.append(Qualities[i]) # on ajoute la qualite correspondante dans le tableau correspondant au canal 1
		if Channels[i] =='2':
			a2.append(Qualities[i])
		if Channels[i] =='3':
			a3.append(Qualities[i])
		if Channels[i] =='4':
			a4.append(Qualities[i])
		if Channels[i] =='5':
			a5.append(Qualities[i])
		if Channels[i] =='6':
			a6.append(Qualities[i])
		if Channels[i] =='7':
			a7.append(Qualities[i])
		if Channels[i] =='8':
			a8.append(Qualities[i])
		if Channels[i] =='9':
			a9.append(Qualities[i])
		if Channels[i] =='10':
			a10.append(Qualities[i])
		if Channels[i] =='11':
			a11.append(Qualities[i])
		if Channels[i] =='12':
			a12.append(Qualities[i])
		if Channels[i] =='13':
			a13.append(Qualities[i])
		i = i+1
		
	# On cree un tableau final correspondant au meilleur qualite de chaque canal
	tabfinal = []	
	
	# On ajoute dans le tableau final, le max de chaque canal. La position dans le tableau correspond au numero du canal
	tabfinal.append(max(a1)) 
	tabfinal.append(max(a2))
	tabfinal.append(max(a3))
	tabfinal.append(max(a4))
	tabfinal.append(max(a5))
	tabfinal.append(max(a6))
	tabfinal.append(max(a7))
	tabfinal.append(max(a8))
	tabfinal.append(max(a9))
	tabfinal.append(max(a10))
	tabfinal.append(max(a11))
	tabfinal.append(max(a12))
	tabfinal.append(max(a13))
	
	
	
	longfinal = len(tabfinal)
	
	# On remplace chaque 0 dans le tableau par un grand nombre afin de pouvoir retourner la qualite minimale
	j = 0
	while j != longfinal:
		if tabfinal[j] == 0:
			tabfinal[j] = '9999'
		j = j+1
	
	
	minqualite = min(tabfinal)
	print "La moins bonne qualite est : ",minqualite

	# On parcourt le tableau afin de trouver l'indice correspondant au canal qui a la minqualite
	n = 0
	while n != longfinal-1:
		if tabfinal[n] == minqualite:
			print "Le meilleur canal est : ",n+1
			n = n+1 
		else:		
			n=n+1
	


GetCanal()
