<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать материал</title>
</head>
<body>

<a href="<c:url value="/security/material" />">Назад</a>

<h1>Редактировать материал</h1>

<form:form action="/security/material" modelAttribute="material" method="POST">
    <table>
        <tr>
            <td>
                <form:label path="materialId">
                    <spring:message text="ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="materialId" readonly="true" size="8" disabled="true"/>
                <form:hidden path="materialId"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="createdAt">
                    <spring:message text="createdTime"/>
                </form:label>
            </td>
            <td>
                <form:input path="createdAt" readonly="true" size="16" disabled="true"/>
                <form:hidden path="createdAt"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="updatedAt">
                    <spring:message text="updatedAt"/>
                </form:label>
            </td>
            <td>
                <form:input path="updatedAt" readonly="true" size="16" disabled="true"/>
                <form:hidden path="updatedAt"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Имя"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit"
                       value="<spring:message text="Редактировать"/>"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
