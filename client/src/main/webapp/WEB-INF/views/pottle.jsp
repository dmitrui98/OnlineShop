<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title> Корзина </title>
		<link href = "css/style.css" rel="stylesheet" type ="text/css" />
		<script type = "text/javascript" src = "/js/jquery-3.2.0.min.js"> </script>
	<head>
	
	<body>
	<a href="<c:url value="/" />">Главная страница</a>

	<jsp:include page="pottleProducts.jsp" flush="true" />


	</body>
	
	
</html>