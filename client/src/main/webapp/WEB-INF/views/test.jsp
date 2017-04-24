<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 23.04.2017
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <script type = "text/javascript" src = "js/jquery-3.2.0.min.js"> </script>
    <script type = "text/javascript" src="js/script.js"> </script>
</head>
<body>
    HELLO WORLD!<br/>
    <%
        Cookie[] cookies = request.getCookies();
        Cookie cookieResult = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("1")) {
                cookieResult = cookie;
                break;
            }
        }
    %>
    myCookie: <%= cookieResult.getValue() %>
    <br/>

    <c:if test="${not empty pageContext.request.userPrincipal.name}">
    <h1>Привет, ${pageContext.request.userPrincipal.name} </h1>
    </c:if>

    <h4>Users:</h4>
    ${users}

    <div id = "content">
        Список товаров AJAX:<br/>
    </div>

</body>
</html>
