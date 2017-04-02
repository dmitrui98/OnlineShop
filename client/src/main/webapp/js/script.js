jQuery('document').ready(function() {
	jQuery("#comeIn").on("click", function() {
		location.href = 'comeIn.html';
	});
	
	jQuery("#registration").on("click", function() {
		location.href = 'registration.html';
	});
	
	jQuery("#pottle").on("click", function() {
		location.href = 'pottle.html';
	});
	
	jQuery("#searchAll").on("click", function() {
		alert("поиск по всем каталогам");
	});
	
	jQuery("#extendedSearch").on("click", function() {
		location.href = 'extendedSearch.html';
	});
});