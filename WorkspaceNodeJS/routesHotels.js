module.exports = function(app) {
	var Hotel = require('./hotel');
	
	//GET
	findAllHotels = function(req, res){
		Hotel.find(function(err, hoteles){
			if(!err) res.send(hoteles);
			else console.log('ERROR: '+err);
		});
	};
	
	//GET
	findByID = function(req, res){
		Hotel.findById(req.params.id, function(err, hotel){
			if(!err) res.send(hotel);
			else console.log('ERROR: '+err);
		});
	};
	
	//POST
	addHotel = function(req, res){
		console.log('POST');
		console.log(req.body);
		
		var hotel = new Hotel({
			name: req.body.name,
			stars: req.body.stars,
			urlimages: req.body.urlimages,
			price : req.body.price,
			description : req.body.description,
			address : req.body.address,
			country : req.body.country,
			city : req.body.city,
			state : req.body.state
		});
		
		hotel.save(function(err){
			if(!err) console.log('Hotel guardado');
			else console.log('ERROR: '+err);
		});
		
		res.send(hotel);
		
	}
	
	//PUT
	updateHotel = function(req, res){
		Hotel.findById(req.params.id, function(err, hotel){
			
			hotel.name = req.body.name;
			hotel.stars = req.body.stars;
			hotel.urlimages = req.body.urlimages;
			hotel.price = req.body.price;
			hotel.description = req.body.description;
			hotel.address = req.body.address;
			hotel.country = req.body.country;
			hotel.city = req.body.city;
			hotel.state = req.body.state;
			
			hotel.save(function(err){
				if(!err) console.log('Hotel actualizado');
				else console.log('ERROR: '+err);
			});
			
		});		
	}
	
	//DELETE
	deleteHotel = function(req, res){ 
		Hotel.findById(req.params.id, function(err, hotel){
			hotel.remove(function(err){
				if(!err) console.log('Hotel eliminado');
				else console.log('ERROR: '+err);
			});
		});
	}
	
	//API Routes
	app.get('/hotels', findAllHotels);
	app.get('/hotels/:id', findByID);
	app.post('/hotels', addHotel);
	app.put('/hotels/:id', updateHotel);
	app.delete('/hotels/:id', deleteHotel);
	
}