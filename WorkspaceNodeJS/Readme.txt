API REST

Consultar todos los hoteles:
http://localhost:5000/hotels - GET

Consultar un hotel
http://localhost:5000/hotels/{id} - GET

Crear un hotel
http://localhost:5000/hotels/ - POST
Enviar por el body un JSON como el siguiente:
{
	"name": "Decameron Isleño",
	"stars": 5,
	"urlimages": [
		"https://www.decameron.com/images/destinos/colombia/isleno/isleno-032.jpg",
		"https://media-cdn.tripadvisor.com/media/photo-s/10/c9/10/52/decameron-isleno.jpg",
		"https://media-cdn.tripadvisor.com/media/photo-s/10/c9/0e/62/decameron-isleno.jpg"
	],
	"price": "900",
	"description": "El Hotel Royal Decameron Isleño cuenta con una piscina al aire libre y alberga un spa y centro de bienestar en San Andrés, a pocos metros de la playa. En sus instalaciones hay conexión Wi-Fi.",
	"address": "Decameron Isleño",
	"country": "Colombia",
	"city": "San Andrés Islas",
	"state": "full"
}

Actualizar un hotel
http://localhost:5000/hotels/{id} - PUT
Enviar por el body un JSON como el siguiente:
{
	"name": "Decameron Isleño",
	"stars": 5,
	"urlimages": [
		"https://www.decameron.com/images/destinos/colombia/isleno/isleno-032.jpg",
		"https://media-cdn.tripadvisor.com/media/photo-s/10/c9/10/52/decameron-isleno.jpg",
		"https://media-cdn.tripadvisor.com/media/photo-s/10/c9/0e/62/decameron-isleno.jpg"
	],
	"price": "900",
	"description": "El Hotel Royal Decameron Isleño cuenta con una piscina al aire libre y alberga un spa y centro de bienestar en San Andrés, a pocos metros de la playa. En sus instalaciones hay conexión Wi-Fi.",
	"address": "Decameron Isleño",
	"country": "Colombia",
	"city": "San Andrés Islas",
	"state": "full"
}

Eliminar un hotel
http://localhost:5000/hotels/{id} - DELETE