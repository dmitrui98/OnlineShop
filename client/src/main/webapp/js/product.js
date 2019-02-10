jQuery('document').ready(function() {
    jQuery(".viewButton").on("click", function () {
        var id = $(this).data("id");
        $.ajax({
            url: "/admin/product/view",
            data: {'id': id},
            method: "get",
            success: function (response) {
                $('body').html(response);
            },
            error: function () {
                alert("ошибка");
            }
        });
    })

    jQuery(".editButton").on("click", function () {
        var id = $(this).data("id");

        var data = {'id': id};
        $.ajax({
            url: "/admin/product/edit",
            data: data,
            method: "get",
            success: function (response) {
                $('body').html(response);
            },
            error: function () {
                alert("ошибка");
            }
        });
    })

    jQuery(".deleteButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;
        $.ajax({
            url: "/admin/product/delete",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response) {
                $('body').html(response);
            },
            error: function () {
                alert("ошибка");
            }
        });
    })
});