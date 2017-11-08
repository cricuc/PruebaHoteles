var mongoose = require('mongoose');
	Schema = mongoose.Schema;
	
var hotel = new Schema({
	name: String,
	stars: Number,
	urlimages: [{
		type: String
	}],
	price: String,
	description: String,
	address: String,
	country: String,
	city: String,
	state: {
		type: String,
		enum: ['full','available','not-available']
	}
});

module.exports = mongoose.model('hotel', hotel);