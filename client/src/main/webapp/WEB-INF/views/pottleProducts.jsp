<%@ page session="true" import="java.util.*, by.dmitrui98.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script>
    <%@include file="/js/pottleProducts.js"%>
</script>
<%
    List<Product> products = (List<Product>) session.getAttribute("pottleProducts");
    if (products != null && (products.size() > 0)) {
%>
<table border="0" cellpadding="0" width="100%" id = 'content'>
    <tr>
        <td><b>Название</b></td>
        <td><b>Цена</b></td>
        <td><b>Описание</b></td>
        <td><b>Количество</b></td>
        <td></td>
    </tr>
    <%
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
    %>
    <tr>
        <td><b><%= product.getName() %></b></td>
        <td><b><%= product.getPrice() %></b></td>
        <td><b><%= product.getDescription() %></b></td>
        <td><b><%= product.getCountPottleProducts() %></b></td>
        <td>
            <%--<form name="addForm"--%>
                  <%--action="/pottleController/delete"--%>
                  <%--method="POST">--%>
                <%--<input type="submit" value="Удалить все товары из корзины">--%>
                <%--<input type="hidden" name= "index" value='<%= index %>'>--%>
                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--</form>--%>

                <button class="deleteFromPottleButton"
                        data-id="<%= product.getProductId() %>"
                        data-csrf-name="${_csrf.parameterName}"
                        data-csrf-value="${_csrf.token}">
                    Удалить один товар из корзины
                </button>

                <button class="deleteFromPottleAllButton"
                        data-id="<%= product.getProductId() %>"
                        data-csrf-name="${_csrf.parameterName}"
                        data-csrf-value="${_csrf.token}">
                    Удалить все товары из корзины
                </button>
        </td>
    </tr>
    <% } %>
</table>
<% } else {%>
<h3>Корзина пуста. Чтобы добавить товары в корзину, перейдите на главную страницу</h3>
<% } %>


