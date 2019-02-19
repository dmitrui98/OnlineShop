<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать товар</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
    <script type="text/javascript" src="<c:url value="/js/handlebars-v4.0.5.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/productEdit.js" />"></script>

    <style>
        .hide {
            display:none;
        }
    </style>
</head>
<body>
<div class="container">
    <a href="<c:url value="/admin/product" />">Назад</a> <br/>

    <%--TODO передать токен в теле запроса--%>
    <form:form action="/onlineShop/admin/product/add?${_csrf.parameterName}=${_csrf.token}" modelAttribute="product"
               method="POST" enctype="multipart/form-data">
        <h3> Редактирование товара </h3>

        <div>
            <spring:bind path="category">
                <form:errors path="category" class="error-message"/>
            </spring:bind>
            <spring:bind path="productMaterials">
                <form:errors path="productMaterials" class="error-message"/>
            </spring:bind>
        </div>

        <table>
            <tr>
                <td>
                    <form:label path="productId">
                        <spring:message text="ID:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="productId" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="productId"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="createdAt">
                        <spring:message text="CreatedTime:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="createdAt" readonly="true" size="16" disabled="true"/>
                    <form:hidden path="createdAt"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="updatedAt">
                        <spring:message text="UpdatedTime:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="updatedAt" readonly="true" size="16" disabled="true"/>
                    <form:hidden path="updatedAt"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Название:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                    <form:errors path="name" class="error-message"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="description">
                        <spring:message text="Описание:"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="description">
                        <form:textarea path="description" cols="40" rows="10"/> <br/>
                        <form:errors path="description" class="error-message"/>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="price">
                        <spring:message text="Цена:"/>
                    </form:label>
                </td>
                <td>
                    <spring:bind path="price">
                        <form:input type="number" path="price" step="any"/>
                        <form:errors path="price" class="error-message"/>
                    </spring:bind>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="image">
                        <spring:message text="Изображение:"/>
                    </form:label>
                </td>
                <td>
                    <%--TODO выберите файл. Обработать отмена--%>
                    <input id = "inputImage" type="file" name="image" accept="image/*,image/jpeg"/>
                    <img src="${product.image.imageDirectory}" width="70" height="50" id = "image"/>
                    <input type="hidden" name="imageDirectory" value="${product.image.imageDirectory}"/>
                    <input type="hidden" name="imageId" value="${product.image.imageId}"/>
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
                <td>
                    <select id="selectedMaterial">
                        <c:forEach items="${materials}" var="m">
                            <option value="${m.materialId}">${m.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="number" value="0" step="any" id = "percent"/>
                    <button id="materialAddButton"> Добавить материал </button> <br/>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="Редактировать"/>"/>
                </td>
            </tr>
        </table>

        <div>
            <h2>Состав</h2>
            <table id="composition"  style="width:100%">
                <c:forEach items="${product.productMaterials}" var="pm">
                    <tr>
                        <td>
                            ${pm.material.name}
                        </td>
                        <td>
                            <input type="hidden" name="materialId[]" value="${pm.material.materialId}">
                            <input type="number" step="any" name="percent[]" value="${pm.materialPercent}">
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
