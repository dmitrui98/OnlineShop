jQuery('document').ready(function() {
    jQuery(".deleteButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url: "/security/category/delete",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response, textStatus, xhr) {
                if (textStatus.toString() === ("notmodified")) {
                    alert("Какой-то продукт ссылается на данную категорию, " +
                        "чтобы удалить категорию вместе с продуктами," +
                        "нажмите кнопку \"удалить каскадно\"");
                }
                else
                    $("body").html(response);
            },
            error: function (response) {
                alert("ошибочка вышла");
            }
        });
    });

    jQuery(".deleteCascadeButton").on("click", function () {
        var result = confirm("Вы действительно хотите удалить все товары, связанные с данной категорией?");

        if (result) {
            var id = $(this).data("id");
            var csrfValue = $(this).data("csrf-value");
            var csrfName = $(this).data("csrf-name");

            var data = {'id': id};
            data[csrfName] = csrfValue;

            jQuery.ajax({
                url: "/security/category/deleteCascade",
                headers: {'X-Csrf-Token': csrfValue},
                data: data,
                method: "post",
                success: function (response, textStatus, xhr) {
                    $("body").html(response);
                },
                error: function (response) {
                    alert("ошибка");
                }
            });
        }
    });

    jQuery(".editButton").on("click", function () {
        var id = $(this).data("id");
        var data = {'id': id};

        jQuery.ajax({
            url: "/security/category/edit",
            data: data,
            method: "get",
            success: function (response, textStatus, xhr) {
                $('body').html(response);
            },
            error: function (response) {
                alert("что-то пошло не так");
            }
        });
    });
});

