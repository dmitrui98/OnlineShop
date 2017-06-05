<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Категория</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
    <script type = "text/javascript" src = "/js/category.js"> </script>
</head>
<body>
<div class="container">
    <a href="<c:url value="/security" />">Назад</a> <br/>
    <a href="<c:url value="/security/category/add" />"> Добавить категорию </a>

    <br><br>

    <c:if test="${!empty categories}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><b>Список категорий</b></h3>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Admin</th>
                        <th>Created at</th>
                        <th>Updated at</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        <th>Delete CASCADE</th>
                    </tr>
                    <c:forEach items="${categories}" var="category1">
                        <tr>
                            <td>${category1.categoryId}</td>
                            <td>${category1.name}</td>
                            <td>${category1.admin.login}</td>
                            <td>${category1.createdAt}</td>
                            <td>${category1.updatedAt}</td>

                            <td>
                                <button
                                        class="editButton"
                                        data-id="${category1.categoryId}">
                                    Редактировать
                                </button>
                            </td>

                            <td>
                                <button
                                        class="deleteButton"
                                        data-id="${category1.categoryId}"
                                        data-csrf-name="${_csrf.parameterName}"
                                        data-csrf-value="${_csrf.token}">
                                    Удалить
                                </button>
                            </td>

                            <td>
                                <button
                                        class="deleteCascadeButton"
                                        data-id="${category1.categoryId}"
                                        data-csrf-name="${_csrf.parameterName}"
                                        data-csrf-value="${_csrf.token}">
                                    Удалить каскадно
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </c:if>
</div>


</body>
</html>




