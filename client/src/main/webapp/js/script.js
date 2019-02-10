jQuery('document').ready(function() {
	jQuery("#comeIn").on("click", function() {
		location.href = "comeIn";
	});

	jQuery("#registration").on("click", function() {
		location.href = "registration";
	});

	jQuery("#pottle").on("click", function() {
		location.href = "pottle";
	});

    // jQuery.ajax({
    //     url: 'products',
    //     type: 'GET',
    //     success: function (data) {
    //         data.forEach(function(element) {
    //             var p = $('<p></p>').text('Товар: ' + element.name + ' ' +
    //                 ' цена: ' + element.price + ' ' +
    //                 ' описание: ' + element.description);
    //
    //             $("#content").append(p);
    //
    //             console.log(element);
    //         });
    //     }
    // });
});