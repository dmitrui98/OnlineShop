<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить категорию</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
</head>
<body>

<div class="container">
    <a href="<c:url value="/security/category" />">Назад</a>


    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title text-center"><b>Добавление категории</b></h3>
        </div>

        <form:form action="/security/category" modelAttribute="category" method="POST" class="form-horizontal">

            <div class="form-group">
                <form:label path="name" class="col-sm-2 control-label">
                    <spring:message text="Наименование:"/>
                </form:label>


                <div class="col-sm-9">
                    <spring:bind path="name">
                        <form:input path="name" autofocus="true" class="form-control"/>
                        <div>
                            <form:errors path="name" class="error-message"/>
                        </div>
                    </spring:bind>
                </div>
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-default col-sm-6 col-sm-offset-3"
                    value="<spring:message text="Добавить"/>"/>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
