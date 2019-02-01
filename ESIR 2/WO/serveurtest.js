var net = require ('net');
var i = 0;
var j = 0;
var n = 0;
var idclient = 0;
var tabclient = [];
var tabgroup = [];
var socket;
//cd Documents/ESIR/ESIR 2/WO
var server = net.createServer();
server.listen('8080','127.0.0.1');

server.on('connection',function(socket){
	idclient = idclient + 1;
	console.log('le nombre de client connecté est : ' + idclient)
	var client = {socketclient : socket, id : idclient};
	tabclient.push(client);

	// var group = {name : }

	socket.on('data',function(data){
		var donnees = JSON.parse(data);
		var final = donnees.toString();
		tab = final.split('/');


		// broadcast
		if ( tab[0] == "b"){
			var envoyeur = client.id;
			var message = JSON.stringify({'id envoyeur' : envoyeur, action : 'broadcast',msg :tab[1,tab.length-1]});
			//console.log(message);
			console.log ('broadcast');
			tabclient.forEach(function (client){
				if(client.id !=  envoyeur){
					client.socketclient.write(message);
				};
			});
		};

		// message privé
		if ( tab[0] == "s"){
			var envoyeur = client.id;
			var message = JSON.stringify({'id envoyeur' : envoyeur ,action : 'private',msg :tab[2,tab.length-1]});
			tabclient.forEach(function (client){
				if (client.id == tab[1]){
					client.socketclient.write(message);
				};
			});
		};

		// liste des clients
		if ( tab[0] == 'lsc'){
			tabclient.forEach(function (client){
				var message = JSON.stringify({action : 'listeClient', id : client.id});
				socket.write(message);
			});
		};

		// créer un groupe
		if ( tab[0] == 'cg'){
				var groupe = {name : tab[1], client : tabclient };
				tabgroup.forEach(function (groupe){
					if (tab[1] != groupe.name )
						{tabgroup.push(groupe);}  //on ajoute dans le tableau de groupe le nouveau groupe
					else {socket.write('le groupe existe deja');}
				});
		};

		// rejoindre un groupe
		if ( tab[0] == 'j'){
			tabgroup.forEach(function (groupe){
				if(tab[1] == groupe.name){
					groupe.client.push(client);
				console.log(groupe.name.client.id)}
				else{socket.write('aucun  groupe de ce nom nexiste');}
			});
		};

		// quitter un groupe
		if ( tab[0] == 'p'){
			tabclient.forEach(function (client){
				var message = JSON.stringify({action : 'leave', id : client.id});
				socket.write(message);
			});
		};

		// envoyer au groupe
		if ( tab[0] == 'bg'){
			var envoyeur = client.id;
			var message = JSON.stringify({'id envoyeur' : envoyeur ,action : 'broadcastGroup', msg :tab[2,tab.length-1]});
			tabgroup.forEach(function(group){
				if(tab[1] == groupe.name){
					tabclient.forEach(function (client){
						if(client.id !=  envoyeur){
							client.socketclient.write(message);
						}
					});
				}
			});
		};

		// liste des clients dans le groupe
		if ( tab[0] == 'lg'){
			tabgroup.forEach(function (groupe){
				if(tab[1] == groupe.name){
	//		tabclient.forEach(function (client){
				var message = JSON.stringify({action : 'listeClientGroup', personneconnecte : groupe.name.client.id});
				socket.write(message);
			};
		});

}


		// quitter
		if ( tab[0] == 'q'){
			tabclient.forEach(function (client){
				var message = JSON.stringify({action : 'quit', id : client.id});
				socket.write(message);
			});
		};

	});

	socket.on('error',function(err){console.log("Détection de l'erreur :" + err);});
});
