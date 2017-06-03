<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta charset="UTF-8"/>
	<title> Корзина </title>
	<jsp:include page="headers/userHeader.jsp" flush="true"/>
<head>
	
<body>
	<a href="<c:url value="/" />">Главная страница</a>
	<jsp:include page="pottleProducts.jsp" flush= "true" />
</body>
	
</html>