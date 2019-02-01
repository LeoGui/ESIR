#!/usr/bin/env python

import socket

TCP_PORT = 5004
TCP_IP = '148.60.12.8'

#On cree une socket de niveau 3
s = socket.socket(socket.AF_PACKET,socket.SOCK_RAW,socket.htons(0x0003))


print s.recv(1024)



