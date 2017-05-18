<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить материал</title>
</head>
<body>

<a href="<c:url value="/security/material" />">Назад</a>


<h1>Добавить материал</h1>

<form:form action="/security/material" modelAttribute="material" method="POST">
    <table>

        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Имя"/>
                </form:label>
            </td>
            <td>
                <form:input path="name" autofocus="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit"
                       value="<spring:message text="Добавить"/>"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
