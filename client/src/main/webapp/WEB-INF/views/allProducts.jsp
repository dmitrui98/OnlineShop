<%@ page session="true" import="java.util.*, by.dmitrui98.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (products != null && (products.size() > 0)) {
%>
<table border="0" cellpadding="0" width="100%">
    <tr>
        <th> <b>Название</b> </th>
        <th> <b>Цена</b> </th>
        <th> <b>Категория</b> </th>
        <th> <b>Фото</b> </th>
        <td> <b>Информация</b> </td>
        <td> <b>В корзину</b> </td>
    </tr>
    <%
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
    %>
    <tr>
        <td><b><%= product.getName() %></b></td>
        <td><b><%= product.getPrice() %></b></td>
        <td><b><%= product.getCategory().getName() %></b></td>
        <td><img src="<%= product.getImage().getImageDirectory() %>" width="70" height="50"/></td>
        <td>
            <form action="/product/info" method="get">
                <input type="hidden" name="id" value="<%=product.getProductId()%>">
                <button type="submit"> Подробнее </button>
            </form>
        </td>
        <td>
            <button class="addInPottleButton"
                    data-id="<%= product.getProductId() %>"
                    data-csrf-name="${_csrf.parameterName}"
                    data-csrf-value="${_csrf.token}">
                Добавить в корзину
            </button>
        </td>
    </tr>
    <% } %>
</table>
<% } %>

<script>
    jQuery(".addInPottleButton").on("click", function() {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id':id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url:"/pottleController/put",
            headers:{'X-Csrf-Token':csrfValue},
            data:data,
            method:"post",
            success:function (response, textStatus, xhr) {

                if (response.length > 0)
                    location.href = "/comeIn";

            },
            error:function (response) {
                alert("что-то пошло не так");
            }
        });
    });
</script>