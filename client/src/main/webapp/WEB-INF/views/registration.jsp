<%@ page contentType="text/html;charset=utf-8" %>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title> Регистрация </title>
		<link href = "css/style.css" rel="stylesheet" type ="text/css" />
	<head>
	
	<body>
		Регистрация

		<form action="/account/registration" method="post">
			<div class = "block">
				Фамилия*:<br/>
				<input type="text" name="surname" class = ""> <br/>
			</div>

			<div class = "block">
				Имя*:<br/>
				<input type="text" name="fname"> <br/>
			</div>

			<div class = "block">
				Отчество:<br/>
				<input type="text" name="lname"> <br/>
			</div>

			<div class = "block">
				Email*:<br/>
				<input type="text" name="email"> <br/>
			</div>

			<div class = "block">
				Логин*:<br/>
				<input type="text" name="login"> <br/>
			</div>

			<div class = "block">
				Пароль*:<br/>
				<input type="password" name="password"> <br/>
			</div>

			<div class = "block">
				Телефон:<br/>
				<input type="text" name="telephon"> <br/>
			</div>

			<input type="submit" value="Зарегистрироваться"> <br/>
		</form>
	</body>
	
	
</html>