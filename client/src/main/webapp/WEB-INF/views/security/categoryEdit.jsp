<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать категорию</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
</head>
<body>

<a href="<c:url value="/security/category" />">Назад</a>

<h3>Редактировать категорию</h3>

<form:form action="/security/category" modelAttribute="category" method="POST">
    <table>
        <tr>
            <td>
                <form:label path="categoryId">
                    <spring:message text="ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="categoryId" readonly="true" size="8" disabled="true"/>
                <form:hidden path="categoryId"/>
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
                       value="<spring:message text="Редактировать"/>"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
