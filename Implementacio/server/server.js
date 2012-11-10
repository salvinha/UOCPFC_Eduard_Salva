var http = require('http');
var paraula = require('./model/paraula.js');
var port = 3000;

var create_koncept = function(text_catala, text_japones, so_catala, so_japones) {
	return paraula.crear_concepte_paraula(text_catala, text_japones, so_catala, so_japones);
};

var edit_koncept = function(id, text_catala, text_japones, audio_catala, audio_japones) {
	console.log("Editing...");
};

var get_koncept = function(id) {
	console.log('Requesting ' + id);
};

http.createServer(function(req, res) {
	var url = req.url;
	if (url == '/favicon.ico') return;
	
	var fullRequest = require('url').parse(url, true);
	
	var pathname = fullRequest.pathname.substring(1); 
	var params = fullRequest.query;
	
	switch (pathname) {
	case 'crear_concepte_paraula':
		console.log('cat = ' + params.text_catala);
		
		var id = create_koncept(
			params.text_catala, 
			params.text_japones, 
			params.so_catala, 
			params.so_japones);
		
		
		res.writeHead(200, {'Content-Type': 'text/plain'});
		res.end(id + "");
		
		break;
	default:
		console.log('Wrong query (' + pathname + ')');
	}	
}).listen(port);
