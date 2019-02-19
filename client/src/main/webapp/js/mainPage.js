$(document).ready(function () {

    $(document).on("click", "a.page-link", function () {
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
            $(".page-item").removeClass("active");
            $(this).parent().addClass("active");

            // блокируем ссылку на следующую страницу
            var nextElement = $(".page-item.number.active").next("li");
            if (!nextElement.hasClass("number")) {
                nextElement.addClass("disabled");
            } else {
                $(".page-item").removeClass("disabled");
            };

            // блокируем ссылку на предыдущую страницу
            var prevElement = $(".page-item.number.active").prev("li");
            if (!prevElement.hasClass("number")) {
                prevElement.addClass("disabled");
            } else {
                while (prevElement.hasClass("number")) {
                    prevElement = prevElement.prev("li");
                }
                prevElement.removeClass("disabled");
            };
        }

        var page = $("li.page-item.active").text().trim();
        var countElements = $("select.countElements").val();
        var data = {page : page, countElements: countElements};

        $.ajax({
            url: "/onlineShop/productList",
            data: data,
            method: "get",
            success: function (response) {
                $(".productList").html(response);

            },
            error: function (response) {
                console.log(response);
                alert("что-то пошло не так productList");
            }
        });
        repaintPagination();
        return false;
    });

    $(".countElements").on("change", function () {
        var countElements = $(this).val();
        var data = {countElements: countElements};
        $.ajax({
            url: "/onlineShop/countPages",
            data: data,
            method: "get",
            success: function(response) {
                var countPages = response.countPages;
                var maxPages = response.maxPages;
                $(".pagination li.page-item.number").next().remove();
                $(".pagination li.page-item.number").remove();
                $(".pagination").append("<li class='page-item number active'> <a class='page-link number' href='#'>1</a> </li>");
                $("li.page-item.number.active").prev().addClass("disabled");
                for (var i = 2; i <= countPages && i <=maxPages; i++) {
                    $(".pagination").append("<li class='page-item number'> <a class='page-link number' href='#'>" + i + "</a> </li>");
                }
                $(".pagination").append("<li class='page-item'> <a class='page-link' href='#'>Следующая</a> </li>");

                var data = {countElements: countElements,
                    page: $("li.page-item.active").text().trim()};
                $.ajax({
                    url: "/onlineShop/productList",
                    data: data,
                    method: "get",
                    success: function (response) {
                        $(".productList").html(response);
                    },
                    error: function () {
                        alert("что-то пошло не так");
                    }
                });
            },
            error: function () {
                alert("что-то пошло не так");
            }
        });
    });
});

function repaintPagination() {
    var countElements = $("select.countElements").val();
    var data = {countElements: countElements};
    $.ajax({
        url: "/onlineShop/countPages",
        data: data,
        method: "get",
        success: function (response) {
            var countPages = response.countPages;
            var maxPages = response.maxPages;
            var nextElement = $(".page-item.number.active").next("li");
            if (!nextElement.hasClass("number")) {
                var currentPage = $(".page-item.number.active").text().trim();
                if (currentPage < countPages) {
                    var element = $(".page-item.number.active");
                    element.next("li").removeClass("disabled");
                    element.removeClass("active");
                    element.prev("li").addClass("active");
                    do {
                        var page = Number(element.text().trim()) + 1;
                        element.children().text(page);
                        element = element.prev("li")
                    } while(element.hasClass("number"));
                }
            }
            var prevElement = $(".page-item.number.active").prev("li");
            if (!prevElement.hasClass("number")) {
                var currentPage = $(".page-item.number.active").text().trim();
                if (currentPage > 1) {
                    var element = $(".page-item.number.active");
                    element.prev("li").removeClass("disabled");
                    element.removeClass("active");
                    element.next("li").addClass("active");
                    do {
                        var page = Number(element.text().trim()) - 1;
                        element.children().text(page);
                        element = element.next("li")
                    } while(element.hasClass("number"));
                }
            }
        },
        error: function (response) {
            console.log(response);
            alert("что-то пошло не так countPages");
        }
    });
}