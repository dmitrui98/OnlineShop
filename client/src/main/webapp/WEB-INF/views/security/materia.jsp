<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Материал</title>
    <script type = "text/javascript" src = "/js/jquery-3.2.0.min.js"> </script>
</head>
<body>

<a href="<c:url value="/security/materia/add" />"> Добавить материал </a>

<c:if test="${!empty materias}">
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
        <c:forEach items="${materias}" var="materia">
            <tr>
                <td>${materia.materiaId}</td>
                <td>${materia.name}</td>
                <td>${materia.admin.login}</td>
                <td>${materia.createdAt}</td>
                <td>${materia.updatedAt}</td>

                <td>
                    <button
                            class="editButton"
                            data-id="${materia.materiaId}">
                        Редактировать
                    </button>
                </td>

                <td>
                    <button
                            class="deleteButton"
                            data-id="${materia.materiaId}"
                            data-csrf-name="${_csrf.parameterName}"
                            data-csrf-value="${_csrf.token}">
                        Удалить
                    </button>
                </td>

                <td>
                    <button
                            class="deleteCascadeButton"
                            data-id="${materia.materiaId}"
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

<script>
    jQuery(".deleteButton").on("click", function() {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id':id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url:"/security/materia/delete",
            headers:{'X-Csrf-Token':csrfValue},
            data:data,
            method:"post",
            success:function (response, textStatus, xhr) {
                $("body").html(response);
            },
            error:function (response) {
                alert("что-то пошло не так");
            }
        });
    });

    jQuery(".editButton").on("click", function() {
        var id = $(this).data("id");
        var data = {'id':id};

        jQuery.ajax({
            url:"/security/materia/edit",
            data:data,
            method:"get",
            success:function (response, textStatus, xhr) {
                $('body').html(response);
            },
            error:function (response) {
                alert("что-то пошло не так");
            }
        });
    });
</script>
