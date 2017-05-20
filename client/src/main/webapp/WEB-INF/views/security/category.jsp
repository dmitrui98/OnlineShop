<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 29.04.2017
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Категория</title>
    <script type = "text/javascript" src = "/js/jquery-3.2.0.min.js"> </script>
    <script type = "text/javascript" src = "/js/category.js"> </script>
</head>
<body>
<a href="<c:url value="/security" />">Назад</a> <br/>

<a href="<c:url value="/security/category/add" />"> Добавить категорию </a>

<c:if test="${!empty categories}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Admin</th>
            <th width="120">Created at</th>
            <th width="120">Updated at</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
            <th width="120">Delete CASCADE</th>
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
</c:if>



</body>
</html>




