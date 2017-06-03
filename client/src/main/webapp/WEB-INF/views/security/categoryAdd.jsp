<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить категорию</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
</head>
<body>

<a href="<c:url value="/security/category" />">Назад</a>


<h3>Добавить категорию</h3>

<form:form action="/security/category" modelAttribute="category" method="POST" class="form-horizontal">
    <table>

        <tr>
            <td valign="top">
                <form:label path="name">
                    <spring:message text="Имя"/>
                </form:label>
            </td>
            <td>
                <spring:bind path="name">
                    <form:input path="name" autofocus="true"/>
                    <div>
                        <form:errors path="name" class="error-message"/>
                    </div>

                </spring:bind>
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
