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
		icmp = struct.unpack('!BBHLL' , en_tete_icmp)
		Type = icmp[0]
		code = icmp[1]
		checksum = icmp[2:3]
		unused = icmp[4:7]
		internet_header = icmp[8:11]
		print ('Type : ' + str(Type) + ' code : ' + str(code) + ' checksum : ' + str(checksum) + ' unused : ' + str(unused) + ' internet header : ' + str(internet_header))


	elif protocol == 6: #TCP
		en_tete_tcp = donneesbrutesrecues[39:63]
		tcp = struct.unpack('!LLHHHL' , en_tete_tcp)
		sequence_number = tcp[0:3]
		acknowledgment_number = tcp[4:7]
		window = tcp[8:9]
		checksum = tcp[10:11]
		urgent_pointer = tcp[12:13]
		data = tcp[14:17]
		print (' sequence number : ' + str(sequence_number) + ' acknowledgment number : ' + str(acknowledgment_number) + ' window : ' + str(window) + 'checksum : ' + str(checksum) + ' urgent pointer : ' + str(urgent_pointer) + 'data : ' + str(data))

	elif  protocol == 17: #UDP
		en_tete_udp = donneesbrutesrecues[39:63]
		udp = struct.unpack('!LLBBH' , en_tete_udp)
		source_address = udp[0:3]
		dest_address = udp[4:7]
		zero = udp[8]
		Protocol = udp[9]
		UDP_length = udp[10:11]
		print ('Source Address : ' + str(source_address) + ' destination adress : ' + str(dest_address) + ' zero : ' + str(zero) + ' Protocole : ' + str(protocol) + ' UDP length : ' + str(UDP_length))


