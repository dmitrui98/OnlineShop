<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Защищенная страничка</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
</head>
<body>
    <div class="container">
        <div class="row" style="margin-top:10px;">
            <div class="col-md-4 col-md-offset-4">
                <a class="btn btn-default btn-block" href="<c:url value = "/admin/category"/>">Редактировать категории
                    товаров</a>
                <a class="btn btn-default btn-block" href="<c:url value = "/admin/material"/>">Редактировать материалы
                    товаров</a>
                <a class="btn btn-default btn-block" href="<c:url value = "/admin/product"/>">Редактировать товары</a>
            </div>
        </div>

    </div>
</body>
</html>
