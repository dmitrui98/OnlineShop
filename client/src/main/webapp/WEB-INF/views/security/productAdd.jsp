<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить товар</title>

    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
    <script type = "text/javascript" src = "js/handlebars-v4.0.5.js"> </script>
    <script type = "text/javascript" src = "js/productAdd.js"> </script>

    <style>
        .hide {
            display:none;
        }
    </style>
</head>
<body>
<div class="container">
    <a href="<c:url value="/security/product" />">Назад</a> <br/>

    <form:form action="/security/product/add?${_csrf.parameterName}=${_csrf.token}" method="post"
               modelAttribute="product" enctype="multipart/form-data" class="form-horizontal">
        <div class="text-center">
            <h4> Добавление товара </h4>
        </div>

        <div>
            <spring:bind path="category">
                <form:errors path="category" class="error-message"/>
            </spring:bind>
            <spring:bind path="productMaterials">
                <form:errors path="productMaterials" class="error-message"/>
            </spring:bind>
        </div>

        <div class="form-group text-left">
            <form:label path="name" class="col-sm-2 control-label">
                <spring:message text="Название:"/>
            </form:label>

            <div class="col-sm-10">
                <spring:bind path="name">
                    <form:input type="text" path="name" class="form-control" placeholder="Название" autofocus="true"/>
                    <form:errors path="name" class="error-message"/>
                </spring:bind>
            </div>
        </div>

        <div class="form-group">
            <form:label path="description" class="col-sm-2 control-label">
                <spring:message text="Описание:"/>
            </form:label>
            <div class="col-sm-10">
                <spring:bind path="description">
                    <form:textarea path="description" class="form-control" placeholder="Описание" rows="5"/>
                    <form:errors path="description" class="error-message"/>
                </spring:bind>
            </div>
        </div>

        <div class="form-group">

            <form:label path="price" class="col-sm-2 control-label">
                <spring:message text="Цена:"/>
            </form:label>
            <div class="col-sm-10">
                <spring:bind path="price">
                    <form:input type="number" path="price" class="form-control" placeholder="Цена" step="any"/>
                    <form:errors path="price" class="error-message"/>
                </spring:bind>
            </div>
        </div>

        <div class="form-group">
            <form:label path="category" class="col-sm-2 control-label">
                <spring:message text="Категория:"/>
            </form:label>

            <div class="col-sm-10">
            <form:select path="category" items="${categories}" class="form-control"
                         itemValue="categoryId" itemLabel="name"/>
            </div>
        </div>

        <div class="form-group">
            <label for="inputImage" class="col-sm-2 control-label">Изображение:</label>
            <%--TODO выберите файл. Обработать отмена--%>
            <%--TODO при получении ошибки нужно заново выбирать изображение--%>
            <div class="col-sm-10">
                <input id="inputImage" type="file" name="image" accept="image/*,image/jpeg"
                        class="form-control" />
                <img src="/images/default.jpg" id = "image" width="150px" height="150px"
                     class="img-responsive"/>
            </div>
        </div>

        <div class="text-center">
            <h4>Состав</h4>
        </div>
        <div class="form-group">
            <div class="text-right">
                <label  for="selectedMaterial" class="col-sm-2">Материал:</label>
            </div>

            <div class="col-sm-2">
                <select id="selectedMaterial" class="form-control">
                    <c:forEach items="${materials}" var="m">
                        <option value="${m.materialId}">${m.name}</option>
                    </c:forEach>
                </select>
            </div>
            <label for="percent" class="col-sm-1">Процент:</label>

            <div class="col-sm-2">
                <input type="number" value="0" step="any" id = "percent" class="form-control"/>
            </div>

            <button id="materialAddButton" class="btn btn-default col-sm-2"> Добавить материал </button>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default col-sm-12">Добавить товар</button>
        </div>


        <br/><br/>

        <table id="composition" style="width:100%">
            <c:forEach items="${product.productMaterials}" var="pm">
                <tr>
                    <td>
                            ${pm.material.name}
                    </td>
                    <td>
                        <input type="hidden" name="materialId[]" value="${pm.material.materialId}">
                        <input type="number" step="any" name="percent[]" value="${pm.percentMaterial}">
                    </td>
                    <td>
                        <button class="delete btn btn-default">Удалить</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <span id="emptyCompositionLabel"
                <c:if test="${not empty product.productMaterials}">
                    class="hide"
                </c:if> >
              Состав не задан </span>
    </form:form>
</div>
</body>
</html>

<script id="table-row" type="text/x-handlebars-template">
    <tr>
        <td>
            {{name}}
        </td>
        <td>
            <input type="hidden" name="materialId[]" value="{{id}}">
            <input type="number" step="any" name="percent[]" value="{{percent}}">
        </td>
        <td>
            <button class="delete">Удалить</button>
        </td>
    </tr>
</script>
