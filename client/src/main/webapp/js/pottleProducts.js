jQuery('document').ready(function() {
    jQuery(".deleteFromPottleAllButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url: "/pottle/deleteAll",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response, textStatus, xhr) {

                $('#content').html(response);
            },
            error: function (response) {
                alert("что-то пошло не так");
            }
        });
    });

    jQuery(".deleteFromPottleButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url: "/pottle/delete",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response, textStatus, xhr) {

                $('#content').html(response);
            },
            error: function (response) {
                alert("что-то пошло не так");
            }
        });
    });
});