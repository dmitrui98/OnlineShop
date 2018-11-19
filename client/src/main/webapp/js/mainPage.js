$("document").ready(function () {

    $("a.page-link").on("click", function () {
        if ($(this).parent().hasClass("disabled") || $(this).parent().hasClass("active"))
            return false;

        // нажатие на следующий, предыдущий
        var clickedOnNumber = true;
        if (!$(this).hasClass("number")) {
            if ($(this).parent().next("li").html() == undefined) {
                var prevElement = $(this).parent().prev("li").children();
                while (prevElement.hasClass("number")) {
                    prevElement = prevElement.parent().prev("li").children();
                }
                prevElement.parent().removeClass("disabled");

                var nextElement = $("li.page-item.active").next();
                if (!nextElement.next().children().hasClass("number"))
                    nextElement.next().addClass("disabled");
                $("li.page-item.active").removeClass("active");
                nextElement.addClass("active");
            };
            if ($(this).parent().prev("li").html() == undefined) {
                var nextElement = $(this).parent().next("li").children();
                while (nextElement.hasClass("number")) {
                    nextElement = nextElement.parent().next("li").children();
                }
                nextElement.parent().removeClass("disabled");

                var prevElement = $("li.page-item.active").prev();
                if (!prevElement.prev().children().hasClass("number"))
                    prevElement.prev().addClass("disabled");
                $("li.page-item.active").removeClass("active");
                prevElement.addClass("active");
            };
            clickedOnNumber = false;
        }

        if (clickedOnNumber) {
            // блокируем ссылку на следующую страницу
            var nextElement = $(this).parent().next("li").children();
            if (!nextElement.hasClass("number")) {
                nextElement.parent().addClass("disabled");
            } else {
                $(".page-item").removeClass("disabled");
            };

            // блокируем ссылку на предыдущую страницу
            var prevElement = $(this).parent().prev("li").children();
            if (!prevElement.hasClass("number")) {
                prevElement.parent().addClass("disabled");
            } else {
                while (prevElement.hasClass("number")) {
                    prevElement = prevElement.parent().prev("li").children();
                }
                prevElement.parent().removeClass("disabled");
            };

            $(".page-item").removeClass("active");
            $(this).parent().addClass("active");
        }

        var page = $("li.page-item.active").text().trim();
        var countElements = $("select.countElements").val();
        var data = {page : page, countElements: countElements};

        $.ajax({
            url: "/productList",
            data: data,
            method: "get",
            success: function (response) {
                $(".productList").html(response);
            },
            error: function (response) {
                console.log(response);
                alert("что-то пошло не так");
            }
        });
        return false;
    });

    $(".countElements").on("change", function () {
        var data = {countElements: $(this).val(),
            page: $("li.page-item.active").text().trim()};
        $.ajax({
            url: "/productList",
            data: data,
            method: "get",
            success: function (response) {
                $(".productList").html(response);

            },
            error: function () {
                alert("что-то пошло не так");
            }
        });
    });
});