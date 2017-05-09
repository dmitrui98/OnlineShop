<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Добавить продукт</title>

    <script type = "text/javascript" src = "/js/jquery-3.2.0.min.js"> </script>
    <script type = "text/javascript" src = "/js/handlebars-v4.0.5.js"> </script>

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

<form:form action="/security/product/add?${_csrf.parameterName}=${_csrf.token}" method="post"
           modelAttribute="product" enctype="multipart/form-data">
    <h2> Добавление товара </h2>

    <form:input type="text" path="name" placeholder="Название" autofocus="true"/> <br/>

    <form:textarea path="description" placeholder="Описание" cols="40" rows="10"/> <br/>

    Цена: <br/>
    <form:input type="number" path="price" placeholder="Цена" step="any"/> <br/>

    Категория: <br/>
    <form:select path="category" items="${categories}"
                 itemValue="categoryId" itemLabel="name"/> <br/>

    <input type="file" name="image" accept="image/*,image/jpeg"/> <br/>

    <select id="selectedMateria">
        <c:forEach items="${materias}" var="m">
            <option value="${m.materiaId}">${m.name}</option>
        </c:forEach>
    </select>

    <input type="number" value="0" step="any" id = "persant"/>
    <button id="materiaAddButton"> Добавить материал </button> <br/>

    <button type="submit">Добавить товар</button> <br/>

    <div>
        <h2>Состав</h2>
        <table id="composition" class="hide"  style="width:100%"></table>
        <span id="emptyCompositionLabel">Состав не задан</span>
    </div>
    <br>

</form:form>

<script>

    $(document).ready(function() {
        $('#materiaAddButton').on('click', function(e) {
            e.preventDefault();

            $('#composition').removeClass('hide');
            $('#emptyCompositionLabel').addClass('hide');

            var id = $('#selectedMateria').val();
            var name = $('#selectedMateria').find('option:selected').text();
            var persant = $('#persant').val();

            var source = $("#table-row").html();
            var template = Handlebars.compile(source);

            var context = {
                id: id,
                name: name,
                persant: persant
            };

            console.log(context);

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
            <input type="hidden" name="materiaId[]" value="{{id}}">
            <input type="number" step="any" name="persant[]" value="{{persant}}">
        </td>
        <td>
            <button class="delete">Удалить</button>
        </td>
    </tr>
</script>

</body>
</html>
