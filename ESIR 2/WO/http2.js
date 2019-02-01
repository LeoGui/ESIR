var http = require("http");
var url = require("utl");
var path = require("path");
var fs = require ('fs');

var serveur = http.createServer(function(request,response){
	var pathname = url.parse(request.url).pathname; //parse : recupere url  && pathname : recupère le chemin pour acceder au serveur
	var method = request.method; //methode http
	var filename = path.join(process.cwd(), unescape(pathname)); // (path.join)pointeur sur le process utilisé  && (unescape)enlever les caractères invisibles ++ donne le fichier qui est demandé
	console.log("Request for " + pathname + "received.");
	console.log("Request method is " + method);
	console.log("Request url is " + request.url);
	var stats;

	console.log(filename);

	// test si le fichier existe
	try{
		stats = fs.lstatSync(filename); //
	}
	catch(e){
		response.writeHead(404,{'Content-Type': 'text/plain'});
		response.write('404 not found\n');
		response.end();
	}

	// Si le fichier existe
	if (stats.isFile()){
		response.writeHead(200, {'Content-Type': 'javascript'};
		var fileStream == fs.createReadStream(filename);
		fileStream(response);
	}

	console.log("Request for " + pathname + "received.");
	console.log("Request method is " + method);});

serveur.listen('8080');
