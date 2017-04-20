<%@ page contentType="text/html;charset=utf-8" %>
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
    <form action="/comeInAdmin" method="post">
        <h2 class="form-heading">Аутентификация администратора</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>

            <input type="text" name="login" class="form-control" placeholder="Логин"
                   autofocus="true"/>

            <input type="password" name="password" class="form-control" placeholder="Пароль"/>

            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Войти"/>
        </div>
    </form>


</div>


</body>


</html>
