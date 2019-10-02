

function doGet(){

	$.ajax({
	    type: "GET",
	    headers: {"X-My-Custom-Header": "some value"},
	    url: "http://localhost:3000/endpoint"
	}).done(function (data) {
	    console.log(data);
	});



}