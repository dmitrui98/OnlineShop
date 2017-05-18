<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 05.05.2017
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать продукт</title>
    <script type = "text/javascript" src = "/js/handlebars-v4.0.5.js"> </script>
    <script type = "text/javascript" src = "/js/jquery-3.2.0.min.js"> </script>

    <style>
        .container {
            width:600px;
            margin: 50px auto;
        }

        .hide {
            display:none;
        }

    </style>

</head>
<body>
<a href="<c:url value="/security/product" />">Назад</a> <br/>
<form:form action="/security/product/add?${_csrf.parameterName}=${_csrf.token}" modelAttribute="product" method="POST" enctype="multipart/form-data">
    <table>
        <tr>
            <td>
                <form:label path="productId">
                    <spring:message text="ID"/>
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
                    <spring:message text="createdTime"/>
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
                    <spring:message text="updatedAt"/>
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
                    <spring:message text="Название"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Описание"/>
                </form:label>
            </td>
            <td>
                <form:textarea path="description" cols="40" rows="10"/> <br/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="Цена"/>
                </form:label>
            </td>
            <td>
                <form:input type="number" path="price" step="any"/>
            </td>
        </tr>

        <input type="hidden" name="imageDirectory" value="${product.image.imageDirectory}"/>
        <input type="hidden" name="imageId" value="${product.image.imageId}"/>

        <tr>
            <td>
                Изображение:
            </td>
            <td>
                <input type="file" name="image" accept="image/*,image/jpeg"/> <br/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="category">
                    <spring:message text="Категория"/>
                </form:label>
            </td>
            <td>
                <form:select path="category" items="${categories}" id = "categories"
                             itemValue="categoryId" itemLabel="name"/> <br/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit"
                       value="<spring:message text="Редактировать"/>"/>
            </td>
        </tr>
    </table>



    <select id="selectedMaterial">
        <c:forEach items="${materials}" var="m">
            <option value="${m.materialId}">${m.name}</option>
        </c:forEach>
    </select>

    <input type="number" value="0" step="any" id = "persent"/>
    <button id="materialAddButton"> Добавить материал </button> <br/>

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
                        <input type="number" step="any" name="persent[]" value="${pm.persentMaterial}">
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
            </c:if>
        >Состав не задан</span>
    </div>
</form:form>
</body>
</html>

<script>

    $(document).ready(function() {


        $('#materialAddButton').on('click', function(e) {
            e.preventDefault();
            $('#composition').removeClass('hide');
            $('#emptyCompositionLabel').addClass('hide');

            var id = $('#selectedMaterial').val();
            var name = $('#selectedMaterial').find('option:selected').text();
            var persent = $('#persent').val();

            var source = $("#table-row").html();
            var template = Handlebars.compile(source);

            var context = {
                id: id,
                name: name,
                persent: persent
            };

            var html = template(context);
            $('#composition').append(html);
        });


        $(document).on('click', '.delete', function(e) {
            e.preventDefault();
            $(this).closest('tr').remove();

            if ($('#composition').find('tr').length == 0) {
                $('#composition').addClass('hide');
                $('#emptyCompositionLabel').removeClass('hide');
            }
        });


    });

</script>


<script id="table-row" type="text/x-handlebars-template">
    <tr>
        <td>
            {{name}}
        </td>
        <td>
            <input type="hidden" name="materialId[]" value="{{id}}">
            <input type="number" step="any" name="persent[]" value="{{persent}}">
        </td>
        <td>
            <button class="delete">Удалить</button>
        </td>
    </tr>
</script>
