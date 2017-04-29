<%@ page session="true" import="java.util.*, by.dmitrui98.entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (products != null && (products.size() > 0)) {
%>
<table border="0" cellpadding="0" width="100%">
    <tr>
        <td><b>Название</b></td>
        <td><b>Цена</b></td>
        <td><b>Описание</b></td>
        <td></td>
    </tr>
    <%
        Iterator<Product> iterator = products.iterator();
        long index = -1;
        while (iterator.hasNext()){
            Product product = iterator.next();
            index++;
    %>
    <tr>
        <td><b><%= product.getName() %></b></td>
        <td><b><%= product.getPrice() %></b></td>
        <td><b><%= product.getDescription() %></b></td>
        <td>
            <form name="addForm"
                  action="/productController/put"
                  method="POST">
                <input type="submit" value="Добавить в корзину">
                <input type="hidden" name= "index" value='<%= index %>'>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<% } %>