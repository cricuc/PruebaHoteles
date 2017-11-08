var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var app = express();

mongoose.connect('mongodb://localhost/almundo', function(err, res){
	if(err) console.log('ERROR: Conectando a la BD: ' + err);
	else console.log('Conexión a la BD realizada');
});

app.use(bodyParser.json());
app.use(methodOverride());

app.get('/', function(req, res) {
	res.send('WS Hoteles almundo');
});

require('./routesHotels')(app);

app.listen(5000);
console.log('Servidor Express escuchando en el puerto 5000');