<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить товар</title>

    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
    <script type = "text/javascript" src = "/js/handlebars-v4.0.5.js"> </script>
    <script type = "text/javascript" src = "/js/productAdd.js"> </script>

    <style>
        .hide {
            display:none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 10px;">
        <div class="col-md-6 col-md-offset-3">
            <h3> Добавление товара </h3>
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="col-md-3">
                        <label for="inputEmail3" class="control-label">Email</label>
                        <%--<form:label path="name">--%>
                            <%--<spring:message text="Название:"/>--%>
                        <%--</form:label>--%>
                    </div>

                    <div class="col-sm-9">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<a href="<c:url value="/security/product" />">Назад</a> <br/>

<form:form action="/security/product/add?${_csrf.parameterName}=${_csrf.token}" method="post"
           modelAttribute="product" enctype="multipart/form-data">
    <h3> Добавление товара </h3>

    <div>
        <spring:bind path="category">
            <form:errors path="category"/>
        </spring:bind>
        <spring:bind path="productMaterials">
            <form:errors path="productMaterials"/>
        </spring:bind>
    </div>

    <table>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Название:"/>
                </form:label>
            </td>
            <td>
                <spring:bind path="name">
                    <form:input type="text" path="name" class="form-control" placeholder="Название" autofocus="true"/> <br/>
                    <form:errors path="name"/>
                </spring:bind>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Описание:"/>
                </form:label>
            </td>
            <td>
                <form:textarea path="description" placeholder="Описание" cols="40" rows="10"/> <br/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="Цена:"/>
                </form:label>
            </td>
            <td>
                <form:input type="number" path="price" placeholder="Цена" step="any"/> <br/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="category">
                    <spring:message text="Категория:"/>
                </form:label>
            </td>
            <td>

                    <form:select path="category" items="${categories}"
                                 itemValue="categoryId" itemLabel="name"/> <br/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <%--TODO выберите файл. Обработать отмена--%>
                <%--TODO при получении ошибки нужно заново выбирать изображение--%>
                <input id="inputImage" type="file" name="image" accept="image/*,image/jpeg" />
                <img src="/images/default.jpg" width="70" height="50" id = "image"/>
            </td>
        </tr>

    </table>
    <table>
    <tr>
        <td>
            <select id="selectedMaterial">
                <c:forEach items="${materials}" var="m">
                    <option value="${m.materialId}">${m.name}</option>
                </c:forEach>
            </select>
            <input type="number" value="0" step="any" id = "percent"/>
        </td>
        <td>
            <button id="materialAddButton"> Добавить материал </button> <br/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <button type="submit">Добавить товар</button>
        </td>
    </tr>
    </table>
    <div>
        <h3>Состав</h3>
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
                        <button class="delete">Удалить</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <span id="emptyCompositionLabel"
                <c:if test="${not empty product.productMaterials}">
                    class="hide"
                </c:if> >
              Состав не задан </span>
    </div>
    <br/>

</form:form>

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

</body>
</html>
