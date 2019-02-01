import os

def GetScan():
	f = os.popen('iwlist wlan0 scan')
	ESSIDs = []
	Channels = []
	Qualities = []
	Counter = 0
	for line in f:
		if line and line.strip():
			ch = line.replace(':',' ').replace('/',' ').replace('=',' ').split()

			if ch[0] in ['ESSID']: #si le premier mot est ESSID
				ESSIDs.append(ch[1]) #on ajoute le deuxieme dans le tableau
				
			if ch[0] in ['Quality']: #si le premier mot est Quality
				Qualities.append(ch[1]) #on ajoute le deuxieme dans le tableau

			if ch[0] in ['Channel']: #si le premier mot est Channel
				Channels.append(ch[1]) #on ajoute le deuxieme dans le tableau
	maxqualite = max(Qualities)
	i=0
	longueur = len(Qualities);
	while i != longueur-1:
		if Qualities[i] == maxqualite:
			print "Le meilleur reseau est : ",ESSIDs[i] 
			print "La meilleure qualite est : " , maxqualite # affiche la plus grande qualite			
			return i
		else:
			i = i+1

	
	#print ESSIDs


GetScan()
