var net = require ('net');
var client = net.Socket();
var prompt = require('prompt-sync')();
var message;
const readline = require('readline');
console.log('h/ pour être aidé');
console.log('Entrez votre nom :');
var pseudo = prompt();

client.connect('8080','127.0.0.1',function(){

  var ajouter = JSON.stringify({nickname : pseudo, action : 'addPseudo'});
  client.write(ajouter);
});

const rl = readline.createInterface({
  input : process.stdin,
});


rl.on('line', function(input){
  tab = input.split('/');

  //aide
  if(tab[0] == 'h'){
    var message = JSON.stringify({action :'help'});
  }

  //upload de fichier
  if ( tab[0] == "u"){
    var message = JSON.stringify({action : 'upload',message :tab[1,tab.length-1]});
  };

  // broadcast
  if ( tab[0] == "b"){
    var message = JSON.stringify({action : 'broadcast',message :tab[1,tab.length-1]});
  };

  // broadcastGroup
  if (tab[0] == "bg"){
    var message = JSON.stringify({nomGroupe : tab[1],action : 'broadcastGroup', message :tab[2,tab.length-1]});
  };

  // message privé
  if (tab[0] == "s"){
    var message = JSON.stringify({source : pseudo,action : 'private',destination : tab[1], message :tab[2,tab.length-1]});
  };

  // liste des clients
  if (tab[0] == 'lsc'){
    var message = JSON.stringify({action : 'listeClient'});
  };

  // liste des groupes
  if (tab[0] == 'lsg'){
    var message = JSON.stringify({action : 'listeGroupe'});
  };

  // créer un groupe
  if (tab[0] == 'cg'){
    var message = JSON.stringify({action : 'createGroup', nomGroupe : tab[1]});
  };

  // rejoindre un groupe
  if ( tab[0] == 'j'){
    var message = JSON.stringify({source : pseudo, action : 'join', nomGroupe : tab[1]});
  };

  // liste des clients dans le groupe
  if ( tab[0] == 'lscg'){
    var message = JSON.stringify({action : 'listClientGroupe', nomGroupe : tab[1]});
  };

  // quitter un groupe
  if ( tab[0] == 'p'){
    var message = JSON.stringify({source :pseudo, action : 'leave', nomGroupe : tab[1]});
  };

  // quitter proprement
  if (tab[0] == 'q'){
    var message = JSON.stringify({source :pseudo, action : 'quit'});
  };

client.write(message);
});


client.on('data',function(data){
  var donnees = JSON.parse(data);
	if(donnees.action == 'again'){
		console.log(donnees.message);
		console.log('Entrez votre nom :');
		pseudo = prompt();
		var ajouter = JSON.stringify({nickname : pseudo, action : 'addPseudo'});
		client.write(ajouter);
	}
  else{console.log(donnees);}
});

client.on('error',function(err){console.log("Détection de l'erreur :" + err);});
