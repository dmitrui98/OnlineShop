<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <tr>
        <th width="100">Название</th>
        <th width="100">Цена</th>
        <th width="100">Категория</th>
        <th width="100">Фото</th>
    </tr>
    <tr>
        <td> ${product.name} </td>
        <td> ${product.price} </td>
        <td> ${product.category.name} </td>
        <td> <img src="${product.image.imageDirectory}"/> </td>
    </tr>
</table> <br/>

<h3>Описание:</h3>
<textarea readonly="true" cols="50" rows="10" class="descriptionClass">
    ${product.description}
</textarea> <br/>

<h3>Состав:</h3>
<table>
    <tr>
        <th width="100"><b>Материал</b></th>
        <th width="60"><b>Процент</b></th>
    </tr>
    <c:forEach items="${product.productMaterias}" var="productMateria">
        <tr>
            <td> ${productMateria.materia} </td>
            <td> ${productMateria.persantMateria} </td>
        </tr>
    </c:forEach>
</table>