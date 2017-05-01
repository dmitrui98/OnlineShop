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
</head>
<body>

<c:url var="url" value="/security/category"/>

<p id = "myId">
    text
</p>

<c:if test="${!empty categories}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${categories}" var="category1">
            <tr>
                <td>${category1.categoryId}</td>
                <td>${category1.name}</td>

                <td>
                    <form name="addForm"
                          action="${url}/edit/${category1.categoryId}"
                          method="GET">
                        <input type="submit" value="Редактировать"/>
                    </form>
                </td>

                <td>
                    <%--<form id="deleteForm"--%>
                          <%--action="${url}"--%>
                          <%--method="DELETE">--%>
                        <%--<input type="submit" value="Удалить">--%>
                        <%--<input type="hidden" name= "id" value="${category1.categoryId}"/>--%>
                    <%--</form>--%>
                    <button
                            class="deleteButton"
                            data-id="${category1.categoryId}"
                            data-csrf-name="${_csrf.parameterName}"
                            data-csrf-value="${_csrf.token}"
                    > Удалить </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Добавить категорию</h1>

<form:form action="${url}" modelAttribute="category" method="POST">
    <table>
        <c:if test="${!empty category.name}">
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
        </c:if>
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
                <c:if test="${!empty category.name}">
                    <input type="submit"
                           value="<spring:message text="Редактировать"/>"/>
                </c:if>
                <c:if test="${empty category.name}">
                    <input type="submit"
                           value="<spring:message text="Добавить"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>

<script>
    jQuery(".deleteButton").on("click", function() {
        var index = $(this).data("index");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'index':index};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url:"/security/category",
            headers:{'X-Csrf-Token':csrfValue},
            data:data,
            method:"delete",
            success:function (response, textStatus, xhr) {

                alert($("#myId").html);
            },
            error:function (response) {
                alert("что-то пошло не так");
            }
        });
    });
</script>
