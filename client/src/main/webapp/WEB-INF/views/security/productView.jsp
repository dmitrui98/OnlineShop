<!DOCTYPE html>
<%@ page import="by.dmitrui98.entity.Product" %>
<%@ page import="by.dmitrui98.entity.ProductMaterial" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация о товаре</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
</head>
<body>
<a href="<c:url value="/security/product" />">Назад</a> <br/>
    ID: ${product.productId} <br/>
    CreatedAt: ${product.createdAt} <br/>
    UpdatedAt: ${product.updatedAt} <br/>
    CreatedBy: ${product.admin.login} <br/>
    <jsp:include page="../productInfo.jsp" flush="true"/>
</body>
</html>
