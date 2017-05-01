<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<fmt:requestEncoding value="UTF-8" />--%>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title> Интернет-магазин одежды </title>
        <link href = "css/bootstrap.css" rel="stylesheet" type ="text/css" />
        <link href = "css/style.css" rel="stylesheet" type ="text/css" />

		<script type = "text/javascript" src = "/js/jquery-3.2.0.min.js"> </script>
		<script type = "text/javascript" src="/js/script.js"> </script>
	<head>
	
	<body>
        <div id = "header" style="height: 80px;" >
			<div id = "currency">
				currency
			</div>
			
			<div id = "siteName">
				Интернет-магазин одежды
			</div>
			
			<div id = "buttons">

				<c:choose>
					<c:when test="<%=request.getUserPrincipal() == null%>">
						<button id = "comeIn"> войти </button>
						<button id = "registration"> регистрация </button>
					</c:when>
					<c:otherwise>
						<span style="font-size : 18px;">
							<%= "Привет, " + request.getUserPrincipal().getName().toString()%> <br/>
							<a href = "/logout">Logout</a>
						</span>
					</c:otherwise>
				</c:choose>

				<button id = "pottle"> корзина </button>


			</div>

            <div id = "language">
                language
            </div>
		</div>

		<jsp:include page="products.jsp" flush="true" />

	<body>
<html>