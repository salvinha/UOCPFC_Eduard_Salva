var mysql = require('../mysql/node_modules/mysql');

var LANG_CAT = 'cat';
var LANG_JAP = 'jap';

function get_connection() {
	return mysql.createConnection({
		host		: 'localhost', 
		user		: 'edu', 
		password	: 'edu', 
		database	: 'jap',
	});
}

function is_word_in_dictionary(the_word) {
	return true;
}


/**
 * Function to insert a word into the dictionary.
 */
exports.insert_word = function (to_insert) {
	var connection = get_connection();
	connection.connect();
	
	var sql = "INSERT INTO paraula (id, catala, japones, so_cat, so_jap) VALUES (null, ?, ?, null, null)";
	var inserted_id = function() {
		connection.query(
			sql, 
			[to_insert.text_cat, to_insert.text_jap],
			function(err, rows) {
				console.log("Inserted row ", rows.insertId);
				if (err) throw err;
				connection.end(function() {
					console.log("Connection ended (" + rows.insertId + ")");
					
					// Returning from here does nothing.
					return rows.insertId;
				});
			}
		);
	};
	
	console.log("Too soon!");
	return inserted_id();
}

