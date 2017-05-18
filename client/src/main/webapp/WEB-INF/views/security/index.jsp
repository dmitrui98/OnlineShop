<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 16.04.2017
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Защищенная страничка</title>
</head>
<body>
    <a href = "<c:url value = "/security/category"/>">Редактировать категории товаров</a><br/>
    <a href = "<c:url value = "/security/material"/>">Редактировать материалы товаров</a><br/>
    <a href = "<c:url value = "/security/product"/>">Редактировать товары</a><br/>
</body>
</html>
