<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta charset="UTF-8"/>
	<title> Интернет-магазин товаров </title>
    <jsp:include page="headers/userHeader.jsp" flush="true"/>
    <script type = "text/javascript" src="/js/allProducts.js"> </script>
<head>
	
<body>
	<div class="container">
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

		<jsp:include page="productList.jsp" flush="true" />
	</div>
<body>
<html>