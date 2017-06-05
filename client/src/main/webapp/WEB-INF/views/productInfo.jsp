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
        <td> <img src="${product.image.imageDirectory}" width="70" height="50"/> </td>
    </tr>
</table> <br/>

<h3>Описание:</h3>
<textarea readonly="true" cols="50" rows="10"">
    ${product.description}
</textarea> <br/>

<h3>Состав:</h3>
<c:if test="${not empty product.productMaterials}">
    <table>
        <tr>
            <th width="100"><b>Материал</b></th>
            <th width="60"><b>Процент</b></th>
        </tr>
        <c:forEach items="${product.productMaterials}" var="productMaterial">
            <tr>
                <td> ${productMaterial.material} </td>
                <td> ${productMaterial.percentMaterial} </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty product.productMaterials}">
    <span>Состав не задан</span>
</c:if>