var mysql = require('../mysql/node_modules/mysql');

function test_db(to_insert) {

	var connection = mysql.createConnection({
	  host     : 'localhost',
	  user     : 'edu',
	  password : 'edu',
	  database : 'jap', 
	});
	
	connection.connect();
	
	// var sql = "SELECT name as solution FROM test";
	var sql = "INSERT INTO test VALUES (null, '" + to_insert + "');"
	console.log(sql);
	connection.query(sql, function(err, rows, fields) {
	  if (err) throw err;
	
	  // console.log('The solution is: ', rows[0].solution);
	});
	
	connection.end();
}

/*
function test_db(to_insert) {
	var db = require("../mysql/node_modules/mysql-native").createTCPClient(); // localhost:3306 by default
	// db.auto_prepare = true;
	db.set("charset", "utf8_general_ci");
	
	function dump_rows(cmd)
	{
	   cmd.addListener('row', function(r) { console.dir(r); } );
	}
	
	db.auth("jap", "edu", "edu");
	dump_rows(db.query("select 1+1,2,3,'4',length('hello')"));
	dump_rows(db.execute("select 1+1,2,3,'4',length(?)", ["hello"]));
	db.execute("insert into test values (null, ?)", [to_insert]);
	db.close();
}
*/


exports.crear_concepte_paraula = function(
	text_catala, text_japones, so_catala, so_japones) {
	
	console.log('Creant la paraula ' + text_catala + ' ...');
	
	test_db(text_japones);
	return 1234;
}
