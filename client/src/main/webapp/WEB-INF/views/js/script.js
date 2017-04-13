jQuery('document').ready(function() {
	jQuery("#comeIn").on("click", function() {
		location.href = '../comeIn.jsp';
	});
	
	jQuery("#registration").on("click", function() {
		location.href = '../registration.jsp';
	});
	
	jQuery("#pottle").on("click", function() {
		location.href = '../pottle.jsp';
	});
	
	jQuery("#searchAll").on("click", function() {
		alert("поиск по всем каталогам");
	});
	
	jQuery("#extendedSearch").on("click", function() {
		location.href = '../extendedSearch.jsp';
	});
});