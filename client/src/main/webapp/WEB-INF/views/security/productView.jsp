<%@ page import="by.dmitrui98.entity.Product" %>
<%@ page import="by.dmitrui98.entity.ProductMateria" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 05.05.2017
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация о товаре</title>
</head>
<body>
<a href="<c:url value="/security/product" />">Назад</a> <br/>
    <tr>
        <tb>name</tb>
        <tb>image</tb>
        <tb>materias</tb>
    </tr>

    <% Product product = (Product) request.getAttribute("product");
        Set<ProductMateria> productMaterias = product.getProductMaterias();
    %>
    <tr>
        <td> ${product.name} </td>
        <td> <img src="${product.image.imageDirectory}"/></td>
        <td> ${product.image.imageDirectory}" </td>
    <%
        for(ProductMateria pm : productMaterias) {
    %>

            <td> <%=pm.getMateria().getName()%></td>
    <% } %>
    </tr>
</body>
</html>
