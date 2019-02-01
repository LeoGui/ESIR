var net = require ('net');
var prototypefind = require ('array.prototype.find')
var idclient = 0;
var tabclient = [];
var tableauclient = [];
var tabgroup = [];
var socket;
var num = 0;
var pseudo ='' ;

var server = net.createServer();
server.listen('8080','127.0.0.1');

server.on('connection',function(socket){
	var client = {socketclient : socket, id : idclient, pseudo : pseudo};
	tabclient.push(client);


	socket.on('data',function(data){
		var donnees = JSON.parse(data);
		//aide
		if(donnees.action == 'help'){
			var message = JSON.stringify('Aide : h/ \r\n' + 'Broadcast : b/message \r\n' + 'BroadcastGroup : bg/nomdugroupe/message \r\n' + 'Message Privé : s/nomdestinataire/message \r\n' + 'Liste des clients : lsc/ \r\n' + 'Liste des groupes : lsg/ \r\n' + 'Liste des clients dans un groupe : lscg/nomdugroupe \r\n' + 'Créer un groupe : cg/nomdugroupe \r\n' + 'Rejoindre un groupe : j/nomdugroupe \r\n' + 'Quitter un groupe : p/nomdugroupe \r\n' + 'Quitter le serveur : q/ \r\n');
			socket.write(message);
		}

		//attribuer un pseudo à un client
		if (donnees.action == 'addPseudo'){
			var ex;
			tabclient.forEach(function(client){
				if(donnees.nickname == client.pseudo){
					var message = JSON.stringify({action : 'again', message : 'erreur, le pseudo existe deja'});
					socket.write(message);
					ex = 1;
				}
			})
			if (ex != 1){
			idclient = idclient + 1;
			client.id = idclient;
			console.log('le nombre de client connecté est : ' + idclient)
			client.pseudo = donnees.nickname;
			var message = JSON.stringify('Salut ' + donnees.nickname + ', tu es connecté ! ');
			socket.write(message);
		}
			}



		// broadcast
		if (donnees.action == 'broadcast'){
			var envoyeur = client.pseudo;
			var message = JSON.stringify('Broadcast | ' + client.pseudo + ' > ' + donnees.message);
			console.log ('broadcast');
			tabclient.forEach(function (client){
				if(client.pseudo !=  envoyeur){
					client.socketclient.write(message);};
				});
			};

		// broadcastGroup
		if (donnees.action == 'broadcastGroup'){

			var existe = function(){
				var etat = false;
				var envoyeur = client.pseudo;
				tabgroup.forEach(function(groupe){
					if(groupe.name === donnees.nomGroupe){
						groupe.tableauclient.forEach(function(client){
							if (envoyeur === client.pseudo){
								etat = true;
							}
						})
					}
				});
				return etat;
			}

			var ex;
			envoyeur = client.pseudo;
			tabgroup.forEach(function(groupe){
				if(donnees.nomGroupe == groupe.name){
					if (existe() == true){
						groupe.tableauclient.forEach(function(client){
							if(client.pseudo !=  envoyeur){
								var message = JSON.stringify('BroadcastGroup | '+ donnees.nomGroupe + ' | ' + envoyeur + ' > ' + donnees.message);
								ex = 1;
								client.socketclient.write(message)};
						});
					};
				};
				if(ex != 1){
				var message = JSON.stringify('vous ne faites pas partis de ce groupe');
				socket.write(message)};
				});
			};


		// message privé
		if (donnees.action == 'private'){
			var ex;
			var envoyeur = donnees.source;
			var message = JSON.stringify('Private | ' + envoyeur + ' > ' + donnees.message);
			tabclient.forEach(function (client){
				if (client.pseudo == donnees.destination){
					ex = 1;
					client.socketclient.write(message);}
				});
				if (ex != 1){socket.write(JSON.stringify('Le client ' + donnees.destination + ' nest pas connecté !'));};
				};

		// liste des clients
		if (donnees.action == 'listeClient'){
			var listclient = []
			tabclient.forEach(function (client){
				listclient.push(client.pseudo);
			});
				var message = JSON.stringify('Voici la liste des clients connectés : '+ listclient )
				socket.write(message);
		};

		// liste des groupes
		if (donnees.action == 'listeGroupe'){
			var listgroup = []
			if (num != 0){
				tabgroup.forEach(function (groupe){
					listgroup.push(groupe.name);
				});
				var message = JSON.stringify('Voici la liste des groupes créés : '+ listgroup )
				socket.write(message);
			}
			else {
				var message = JSON.stringify('Aucun groupe a été créé');
				socket.write(message);}
		};

		// créer un groupe
		if (donnees.action == 'createGroup'){
			var ex;
			tabgroup.forEach(function(groupe){
				if (donnees.nomGroupe == groupe.name){
					var message = JSON.stringify('Le groupe ' + donnees.nomGroupe + ' existe déjà.');
					socket.write(message);
					ex = 1 ;}
			});
			if (ex!=1){
				num = num + 1 ;
				var groupe = {numgroupe : num ,name : donnees.nomGroupe, tableauclient : tableauclient};
				tabgroup.push(groupe);
				var message = JSON.stringify('Le groupe ' + donnees.nomGroupe + ' a été créé.');
				socket.write(message);};
		};

		// rejoindre un groupe
		if (donnees.action == 'join'){
			var ex;
			var envoyeur = donnees.source;
			tabgroup.forEach(function(groupe){
				if(donnees.nomGroupe == groupe.name){
					ex = 1;
					tabclient.forEach(function(client){
							groupe.tableauclient.push(client);
						
					});
						var message = JSON.stringify('Vous avez rejoins le groupe : ' + donnees.nomGroupe);
						socket.write(message);}
				});
				if (ex!=1){
					var message = JSON.stringify('Aucun groupe du nom ' + donnees.nomGroupe + ' existe.');
					socket.write(message);}
		};

		// quitter un groupe
		if (donnees.action == 'leave'){
			var i = 0;
			var ex;
				tabgroup.forEach(function (groupe){
					if(donnees.nomGroupe == groupe.name){
						tableauclient.forEach(function(client){
							if(donnees.source == client.pseudo){
								groupe.tableauclient.splice(i,1);}
						i = i + 1;
						});
					};
					ex = 1;
					var message = JSON.stringify('Vous avez quitté le groupe : ' + donnees.nomGroupe);
					socket.write(message);
				});
				if (ex!=1){
					var message = JSON.stringify('Aucun groupe du nom ' + donnees.nomGroupe + ' existe.');
					socket.write(message);}
				};

		// quitter proprement
		if(donnees.action == 'quit'){
			var i = 0;
			var j = 0;
				tabgroup.forEach(function(groupe){
					tableauclient.forEach(function(client){
						if(donnees.source == client.pseudo){
							groupe.tableauclient.splice(i,1);}
					i = i + 1;
					});
				});
				tabclient.forEach(function(client){
					if(donnees.source == client.pseudo){
						tabclient.splice(j,1);}
				j = j + 1;
				});
			var message = JSON.stringify('Vous vous êtes deconnecté');
			socket.write(message);
			socket.end();
		};

		// liste des clients dans le groupe
		if(donnees.action == 'listClientGroupe'){
			var listeClient = [];
			tabgroup.forEach(function(groupe){
				if(donnees.nomGroupe == groupe.name){
					groupe.tableauclient.forEach(function(client){
						listeClient.push(client.pseudo)});
				};
			});
			var message = JSON.stringify('Voici la liste des clients connectés à ce groupe : '+ listeClient );
			socket.write(message);
		};

	});

	socket.on('error',function(err){console.log("Détection de l'erreur :" + err);});

});
