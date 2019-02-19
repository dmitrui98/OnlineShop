<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8"/>
	<title> Аутентификация </title>
	<jsp:include page="headers/userHeader.jsp" flush="true"/>
<head>

<body>
	<div class="container">
        <form action="" method="post">
			<h2 class="form-heading">Аутентификация</h2>

			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span>

				<input type="text" name="login" class="form-control" placeholder="Логин"
					   autofocus="true"/>

				<input type="password" name="password" class="form-control" placeholder="Пароль"/>

				<span>${error}</span>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

				<input class="btn btn-lg btn-primary btn-block" type="submit" value="Войти"/>
                <h4 class="text-center"><a href="<c:url value="/registration"/>">Создать аккаунт</a></h4>
                <h4 class="text-center"><a href="<c:url value="/"/>">Главная страница</a></h4>
			</div>
		</form>


	</div>
</body>
</html>