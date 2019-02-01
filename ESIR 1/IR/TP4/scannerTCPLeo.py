import socket

TCP_IP = input("adresse IP est : ")

liste = []
liste.append(21)
liste.append(22)
liste.append(80)
liste.append(139)
liste.append(443)
liste.append(661)
liste.append(8080)


i = 0

# On test si la connection se fait ou pas. Si elle se fait, le port est ouvert sinon il est ferme.
while i<7:
	sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # Creation de la socket

	try :
		sock.connect((TCP_IP,int(liste[i]))) 	
		print ("Le port ") + liste[i] + (" est ouvert")
		sock.close()	
		i=i+1
		
	except Exception  :
		print ("Le port ") + liste[i] + (" est ferme")
		sock.close()
		i = i+1	
