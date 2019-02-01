var net = require ('net');
var client = net.Socket();
var name = "vincent";
const readline = require('readline');

client.connect('8080','127.0.0.1',function(){
		console.log('connected');
		//client.name = 'vincent';
		//client.write(name);
		});

const rl = readline.createInterface({
			input : process.stdin,

		});

rl.on('line', function(input){
client.write(JSON.stringify(input));
});

client.on('data',function(data){
		console.log('received : ' + data.toString());
		});

client.on('error',function(err){console.log("Détection de l'erreur :" + err);
					end();});
