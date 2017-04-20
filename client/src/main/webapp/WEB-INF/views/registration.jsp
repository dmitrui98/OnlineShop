<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=utf-8" %>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title> Регистрация </title>
		<link href = "css/style.css" rel="stylesheet" type ="text/css" />
		<link href = "css/bootstrap.css" rel="stylesheet" type ="text/css" />
		<link href = "css/common.css" rel="stylesheet" type ="text/css" />

		<%--<script type = "text/javascript" src = "js/jquery-3.2.0.min.js"> </script>--%>
		<script type = "text/javascript" src = "js/bootstrap.min.js"> </script>

		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

	<head>
	
	<body>
		<div class="container">
			<%--Регистрация--%>

			<%--<form action="/registration" method="post" modelAttribute="userForm" class="form-signin>--%>
				<%--<h2 class="form-signin-heading">Создайте свой аккаунт</h2>--%>
				<%--<div class = "block">--%>
					<%--Фамилия*:<br/>--%>
					<%--<input type="text" name="surname" class = ""> <br/>--%>
				<%--</div>--%>

				<%--<div class = "block">--%>
					<%--Имя*:<br/>--%>
					<%--<input type="text" name="fname"> <br/>--%>
				<%--</div>--%>

				<%--<div class = "block">--%>
					<%--Отчество:<br/>--%>
					<%--<input type="text" name="lname"> <br/>--%>
				<%--</div>--%>

				<%--<div class = "block">--%>
					<%--Email*:<br/>--%>
					<%--<input type="text" name="email"> <br/>--%>
				<%--</div>--%>

				<%--<div class = "block">--%>
					<%--Логин*:<br/>--%>
					<%--<input type="text" name="login"> <br/>--%>
				<%--</div>--%>

				<%--<div class = "block">--%>
					<%--Пароль*:<br/>--%>
					<%--<input type="password" name="password"> <br/>--%>
				<%--</div>--%>

				<%--<div class = "block">--%>
					<%--Телефон:<br/>--%>
					<%--<input type="text" name="telephon"> <br/>--%>
				<%--</div>--%>

				<%--<input type="submit" value="Зарегистрироваться"> <br/>--%>
			<%--</form>--%>
		<%--</div>--%>
		<div class="container">

			<form:form method="POST" modelAttribute="userForm" class="form-signin" action="/registration">

				<h2 class="form-signin-heading">Регистрация</h2>

				<spring:bind path="login">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="login" class="form-control" placeholder="Логин"
									autofocus="true" id = "login"/>
						<form:errors path="login"/>
					</div>
				</spring:bind>

				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" path="password" class="form-control" placeholder="Введите пароль"></form:input>
						<form:errors path="password"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="confirmPassword">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" path="confirmPassword" class="form-control"
									placeholder="Введите пароль еще раз"></form:input>
						<form:errors path="confirmPassword"></form:errors>
					</div>
				</spring:bind>

				<button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
			</form:form>

		</div>
	</body>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</html>