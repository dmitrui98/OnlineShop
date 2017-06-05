<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="container">
    <c:if test="${!empty products}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><b>Список товаров</b></h3>
            </div>
            <div class="table-responsive">

                    <table class="table table-bordered table-hover">
                        <tr>
                            <th> <b>Название</b> </th>
                            <th> <b>Цена</b> </th>
                            <th> <b>Категория</b> </th>
                            <th> <b>Фото</b> </th>
                            <td> <b>Информация</b> </td>
                            <td> <b>В корзину</b> </td>
                        </tr>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td> ${product.name} </td>
                                <td> ${product.price} </td>
                                <td> ${product.category.name} </td>
                                <td><img src="${product.image.imageDirectory}" width="70" height="50"/></td>
                                <td>
                                    <form action="/product/info" method="get">
                                        <input type="hidden" name="id" value="${product.productId}">
                                        <button type="submit"> Подробнее </button>
                                    </form>
                                </td>
                                <td>
                                    <button class="addInPottleButton"
                                            data-id="${product.productId}"
                                            data-csrf-name="${_csrf.parameterName}"
                                            data-csrf-value="${_csrf.token}">
                                        Добавить в корзину
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
            </div>
        </div>
    </c:if>
</div>

