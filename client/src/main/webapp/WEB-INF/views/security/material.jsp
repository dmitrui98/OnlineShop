<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Материал</title>
    <script type = "text/javascript" src = "/js/jquery-3.2.0.min.js"> </script>
    <script type = "text/javascript" src = "/js/material.js"></script>
</head>
<body>
<a href="<c:url value="/security" />">Назад</a> <br/>

<a href="<c:url value="/security/material/add" />"> Добавить материал </a>

<c:if test="${!empty materials}">
    <table>
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
        <c:forEach items="${materials}" var="material">
            <tr>
                <td>${material.materialId}</td>
                <td>${material.name}</td>
                <td>${material.admin.login}</td>
                <td>${material.createdAt}</td>
                <td>${material.updatedAt}</td>

                <td>
                    <button
                            class="editButton"
                            data-id="${material.materialId}">
                        Редактировать
                    </button>
                </td>

                <td>
                    <button
                            class="deleteButton"
                            data-id="${material.materialId}"
                            data-csrf-name="${_csrf.parameterName}"
                            data-csrf-value="${_csrf.token}">
                        Удалить
                    </button>
                </td>

                <td>
                    <button
                            class="deleteCascadeButton"
                            data-id="${material.materialId}"
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


