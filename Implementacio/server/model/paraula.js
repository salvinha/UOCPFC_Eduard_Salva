
exports.crear_concepte_paraula = function(
	text_catala, text_japones, so_catala, so_japones) {
	
	var id = require('../lib/db.js').insert_word({
		text_cat: 'lalala', 
		text_jap: 'kikiki', 
		so_cat: '', 
		so_jap: ''
	});
	
	return id;
}