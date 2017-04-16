<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="UTF-8"/>
		<title> Аутентификация </title>
		<link href = "css/style.css" rel="stylesheet" type ="text/css" />
	<head>
	
	<body>
		Аутентификация

		<form action="/comeIn" method="post">
			Логин:
			<input type="text" name="login">
			Пароль:
			<input type="password" name="password">
			<input type="submit" value="Войти">
		</form>

		<c:if test="${not empty login}">
			<h1>Логин пользователя: ${login}</h1>
		</c:if>

		<c:if test="${not empty login2}">
			<h1>Логин2 пользователя: ${login2}</h1>
		</c:if>


	</body>
	
	
</html>