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
    <h4>Users:</h4>
    ${users}

</body>
</html>
