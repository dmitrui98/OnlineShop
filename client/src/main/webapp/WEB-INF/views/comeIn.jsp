<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="UTF-8"/>
		<title> Аутентификация </title>
		<link href = "css/bootstrap.css" rel="stylesheet" type ="text/css" />
		<link href = "css/style.css" rel="stylesheet" type ="text/css" />

		<script type = "text/javascript" src = "js/jquery-3.2.0.min.js"> </script>

		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<head>
	
	<body>
		<div class="container">
			<form action="/comeIn" method="post">
				<h2 class="form-heading">Аутентификация</h2>

				<div class="form-group ${error != null ? 'has-error' : ''}">
					<span>${message}</span>

					<input type="text" name="login" class="form-control" placeholder="Логин"
						   autofocus="true"/>

					<input type="password" name="password" class="form-control" placeholder="Пароль"/>

					<span>${error}</span>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

					<input class="btn btn-lg btn-primary btn-block" type="submit" value="Войти"/>
					<h4 class="text-center"><a href="/registration">Создать аккаунт</a></h4>
				</div>
			</form>


		</div>

		<c:if test="${not empty login}">
			<h6>Логин пользователя: ${login}</h6>
		</c:if>

		<c:if test="${not empty login2}">
			<h6>Логин2 пользователя: ${login2}</h6>
		</c:if>


	</body>
	
	
</html>