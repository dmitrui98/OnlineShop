<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать материал</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
</head>
<body>
<div class="container">
    <a href="<c:url value="/admin/material" />">Назад</a>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title text-center"><b>Редактирование материала</b></h3>
        </div>

        <form:form action="/onlineShop/admin/material" modelAttribute="material" method="POST"  class="form-horizontal">

            <div class="form-group">
                <form:label path="materialId" class="col-sm-2">
                    <spring:message text="ID"/>
                </form:label>

                <form:input path="materialId" readonly="true" disabled="true" class="col-sm-4"/>
                <form:hidden path="materialId"/>
            </div>

            <div class="form-group">
                <form:label path="createdAt" class="col-sm-2">
                    <spring:message text="Время создания"/>
                </form:label>

                <form:input path="createdAt" readonly="true" disabled="true" class="col-sm-4"/>
                <form:hidden path="createdAt"/>
            </div>

            <div class="form-group">
                <form:label path="updatedAt" class="col-sm-2">
                    <spring:message text="Время обновления"/>
                </form:label>

                <form:input path="updatedAt" readonly="true" size="16" disabled="true" class="col-sm-4"/>
                <form:hidden path="updatedAt"/>
            </div>

            <div class="form-group">
                <form:label path="name" class="col-sm-2">
                    <spring:message text="Наименование"/>
                </form:label>

                <spring:bind path="name">
                    <form:input path="name" autofocus="true" class="col-sm-4"/>
                    <div>
                        <form:errors path="name" class="error-message"/>
                    </div>
                </spring:bind>
            </div>

            <div class="form-group">
                <div class="col-sm-2">
                <input type="submit"
                       value="<spring:message text="Редактировать"/>"/>
                </div>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
