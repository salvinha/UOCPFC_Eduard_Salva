function test_node() {
	console.log('This is a test.');
}


function insert_db() {
	setTimeout(
		function() {
			console.log('Finished insert.'); 
			return 10;
		}, 2000
	);
}


var id = insert_db();
console.log('id:', id);

