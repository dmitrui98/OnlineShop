<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta charset="UTF-8"/>
	<title> Интернет-магазин товаров </title>
    <jsp:include page="headers/userHeader.jsp" flush="true"/>
    <script type="text/javascript" src="<c:url value="/js/mainPage.js" />"></script>
<head>
	
<body>
	<div class="container">
		<div id = "header" style="height: 80px;" >
			<div id = "currency">
				currency
			</div>

			<div id = "siteName">
				Интернет-магазин товаров
			</div>

			<div id = "buttons">
				<c:choose>
                    <c:when test="${empty pageContext.request.userPrincipal}">
						<button id = "comeIn"> войти </button>
						<button id = "registration"> регистрация </button>
					</c:when>
					<c:otherwise>
						<span style="font-size : 18px;">
							Привет, ${pageContext.request.userPrincipal.name} <br/>
							<a href="logout">Logout</a>
						</span>
					</c:otherwise>
				</c:choose>

				<button id = "pottle"> корзина </button>

			</div>

			<div id = "language">
				language
			</div>
		</div>

		<div class="productList">
			<jsp:include page="productList.jsp" flush="true" />
		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					Количество элементов на странице:
					<select class="form-control countElements">
						<option>5</option>
						<option>10</option>
						<option>15</option>
						<option>20</option>
						<option>25</option>
						<option>30</option>
					</select>
				</div>
				<div class = "col-sm-8">
					<nav aria-label="...">
						<ul class="pagination">
							<li class="page-item disabled">
								<a class="page-link" href="#">Предыдущая</a>
							</li>
							<li class="page-item number active">
								<a class="page-link number" href="#">1</a>
							</li>
							<%
								Integer countPages =(Integer) request.getAttribute("countPages");
								Integer maxPages = (Integer) request.getAttribute("maxPages");
								for (int i = 2; i <=countPages && i <= maxPages; i++) {
							%>
							<li class="page-item number">
								<a class="page-link number" href="#"><%=i%> </a>
							</li>
							<%}%>
							<li class="page-item disabled">
								<a class="page-link" href="#">Следующая</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
<body>
<html>