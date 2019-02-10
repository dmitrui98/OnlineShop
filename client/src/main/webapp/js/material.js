jQuery('document').ready(function() {
    jQuery(".deleteButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url: "material/delete",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response, textStatus, xhr) {
                if (textStatus.toString() === "notmodified") {
                    alert("Какой-то товар имеет в своем составе данный материал, " +
                        "чтобы удалить материал вместе с товарами," +
                        "нажмите кнопку \"удалить каскадно\"");
                } else
                    $("body").html(response);
            },
            error: function (response, textStatus) {
                alert("ошибка: " + textStatus);
            }
        });
    });

    jQuery(".deleteCascadeButton").on("click", function () {
        var result = confirm("Вы действительно хотите удалить все товары, связанные с данным материалом?");
        if (result) {
            var id = $(this).data("id");
            var csrfValue = $(this).data("csrf-value");
            var csrfName = $(this).data("csrf-name");

            var data = {'id': id};
            data[csrfName] = csrfValue;

            jQuery.ajax({
                url: "material/deleteCascade",
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
            url: "material/edit",
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
