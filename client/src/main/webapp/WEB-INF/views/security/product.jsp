<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Товар</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
    <script type = "text/javascript" src = "js/product.js"> </script>
</head>
<body>
<div class="container">
    <a href="<c:url value="/security" />">Назад</a> <br/>
    <a href="<c:url value="/security/product/add"/>"> Добавить товар </a>

    <br><br>

    <c:if test="${!empty products}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><b>Список товаров</b></h3>
            </div>
            <div class="table-responsive">

                <table class="table table-bordered table-hover">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Admin</th>
                        <th>Created at</th>
                        <th>Updated at</th>
                        <th>View</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <td> ${product.productId} </td>
                            <td> ${product.name} </td>
                            <td><img src="${product.image.imageDirectory}" width="70" height="50"/></td>
                            <td> ${product.admin.login} </td>
                            <td> ${product.createdAt} </td>
                            <td> ${product.updatedAt} </td>
                            <td>
                                <button class="viewButton"
                                        data-id="${product.productId}">
                                    view
                                </button>
                            </td>
                            <td> <button class="editButton"
                                         data-id="${product.productId}">
                                 edit
                                </button>
                            </td>
                            <td> <button class="deleteButton"
                                         data-id="${product.productId}"
                                         data-csrf-name = "${_csrf.parameterName}"
                                         data-csrf-value = "${_csrf.token}"   >
                                    delete
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


