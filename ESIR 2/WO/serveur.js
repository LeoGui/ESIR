var net = require ('net');
var i = 0;
// var sockserver = net.Socket()

var server = net.createServer(function(socket){
	// socket.write('vous êtes connectés !');
	var tabpersonne = [];
	tabpersonne.push(socket.name);
	console.log ('Les clients connectés sont :' + tabpersonne)

var message = socket.on('data',function(data){
	var entree = data.toString();
	tab = entree.split('/');
	var message = JSON.stringify({name : socket.name, type : tab[0],msg :tab[1,tab.length-1]});
	console.log(message.toString());
	// donnees = entree.split('/');
	if ( tab[0] == "b"){
		console.log ('broadcast');
			while (i < tabpersonne.length -1 ){
			socket.pipe('le client '+ socket.name + 'vous envoie ' + tabpersonne[i]);
	 		i = i+1;
			};
};

		switch (data) {
		    case (tab[0] == "b"):
						console.log ('broadcast')
						while (i < list.size() -1 ){
		        socket.pipe('le client '+ socket.name + 'vous envoie ' + list(i));
					 i = i+1;
				 };
		        break;
		    case 1:
		        day = "Monday";
		        break;
		    case 2:
		        day = "Tuesday";
		        break;
		    case 3:
		        day = "Wednesday";
		        break;
		    case 4:
		        day = "Thursday";
		        break;
		    case 5:
		        day = "Friday";
		        break;
		    case 6:
		        day = "Saturday";
		console.log('received : ' + data);}
	});
	socket.on('error',function(err){console.log("Détection de l'erreur :" + err);});
});
server.listen('8080','127.0.0.1');



//	socket.pipe(socket);});
// server.listen('8080','148.60.143.113'); //J'écoute sur mon adresse ip pendant que le client envoie sur mon adresse
