<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оформление заказа</title>
    <jsp:include page="headers/userHeader.jsp" flush="true"/>
</head>
<body>
    <div class="container">
        <h3>Оформление заказа</h3>

        <form action="pottle/issueOrder" method="POST">

            <input type="text" name="country" placeholder="Страна"
                        autofocus="true"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button type="submit">Оформить</button>
        </form>
    </div>


</body>
</html>
