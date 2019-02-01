#!/usr/bin/env python
import struct
import socket

#Tous les paquets 0x0003
rawSocket=socket.socket(socket.AF_PACKET,socket.SOCK_RAW,socket.htons(0x0003))

while True :
	donneesbrutesrecues = rawSocket.recvfrom(65565)
	#conversion de chaine vers un tuple
	donneesbrutesrecues = donneesbrutesrecues[0]
	#L'en-tete IP commence au quinzieme octets, apres l'en-tete ethernet, et fait 20 octets (sans les options) 
	en_tete_ip = donneesbrutesrecues[14:34]
	#on lit l'en-tete IP et on le structure. B : entier sur 1 octet, H : entier sur 2 octets, s : chaine de caracteres (pour l'adresse IP)
	Eip = struct.unpack('!BBHHHBBH4s4s' , en_tete_ip)
	version_ihl = Eip[0]
	#Decalage de 4 bits car le champs version est sur 4 bit et qu'on lit 1 octet
	version = version_ihl >> 4
	#Annulation des 4 premiers bits pour n'avoir que les 4 bits du champs header length
	ihl = version_ihl & 0xF
	#Nombre de mot de 4 octets en octets
	longueur_en_tete_IP = ihl * 4
	ttl = Eip[5]
	protocol = Eip[6]
	s_addr = socket.inet_ntoa(Eip[8])
	d_addr = socket.inet_ntoa(Eip[9])
	print ("Version : " + str(version) + " Longueur en-tete IP : " + str(ihl) + " TTL : " + str(ttl) + " Protocole : " + str(protocol) + " Adresse IP Source : " + str(s_addr) + " Adresse IP Destination : " + str(d_addr))


	if protocol == 1: #ICMP
		en_tete_icmp = donneesbrutesrecues[35:46]
		icmp = struct.unpack('!BBHHHBBH4s4s' , en_tete_icmp)
		Type = donneesbrutesrecues[35]
		code = donneesbrutesrecues[36]
		checksum = donneesbrutesrecues[37:38]
		unused = donneesbrutesrecues[39:42]
		internet_header = donneesbrutesrecues[43:46]
		print ('Type : ' + str(Type) + ' code : ' + str(code) + ' checksum : ' + str(checksum) + ' unused : ' + str(unused) + ' internet header : ' + str(internet_header))


	elif protocol == 6: #TCP

		source_port = donneesbrutesrecues
		dest_port = donneesbrutesrecues
		sequence_number = donneesbrutesrecues[39:42]
		acknowledgment_number = donneesbrutesrecues[43:46]
		window = donneesbrutesrecues[49:50]
		checksum = donneesbrutesrecues[51:52]
		urgent_pointer = donneesbrutesrecues[53:54]
		data = donneesbrutesrecues[60:63]
		print (' sequence number : ' + str(sequence_number) + ' acknowledgment number : ' + str(acknowledgment_number) + ' window : ' + str(window) + 'checksum : ' + str(checksum) + ' urgent pointer : ' + str(urgent_pointer) + 'data : ' + str(data))

	elif  protocol == 17: #UDP
		source_address = donneesbrutesrecues[35:38]
		dest_address = donneesbrutesrecues[39:42]
		zero = donneesbrutesrecues[43]
		Protocol = donneesbrutesrecues[44]
		UDP_length = donneesbrutesrecues[45:46]
		print ('Source Address : ' + str(source_address) + ' destination adress : ' + str(dest_address) + ' zero : ' + str(zero) + ' Protocole : ' + str(protocol) + ' UDP length : ' + str(UDP_length))


