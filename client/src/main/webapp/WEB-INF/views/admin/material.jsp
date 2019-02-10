<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Материал</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
    <script type = "text/javascript" src = "<c:url value="/js/material.js"/>"></script>
</head>
<body>
<div class="container">
    <a href="<c:url value="/admin" />">Назад</a> <br/>

    <a href="<c:url value="/admin/material/add" />"> Добавить материал </a>

    <br><br>

    <c:if test="${!empty materials}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><b>Список материалов</b></h3>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <tr>
                        <th>№</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Admin</th>
                        <th>Created at</th>
                        <th>Updated at</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        <th>Delete CASCADE</th>
                    </tr>
                    <%int i = 0; %>
                    <c:forEach items="${materials}" var="material">
                        <tr>
                            <td><%=++i%></td>
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
            </div>
        </div>
    </c:if>


</div>






</body>
</html>


