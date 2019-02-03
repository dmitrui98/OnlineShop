jQuery('document').ready(function() {
    jQuery(".addInPottleButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url: "pottle/put",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response, textStatus, xhr) {

                if (response.length > 0) {
                    location.href = "/comeIn";
                } else{
                    alert("Товар добавлен в корзину");
                }

            },
            error: function (response) {
                alert("что-то пошло не так");
            }
        });
    });


});