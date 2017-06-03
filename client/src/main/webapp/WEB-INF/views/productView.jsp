<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подробная информация</title>
    <jsp:include page="headers/userHeader.jsp" flush="true"/>
</head>
<body>
    <a href="<c:url value="/" />">Назад</a> <br/>
    <jsp:include page="productInfo.jsp" flush="true"/>
</body>
</html>
