<!DOCTYPE html>
<%@ page import="java.util.Iterator" %>
<%@ page import="by.dmitrui98.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Товар</title>
    <jsp:include page="headers/adminHeader.jsp" flush="true"/>
    <script type = "text/javascript" src = "/js/product.js"> </script>
</head>
<body>
    <a href="<c:url value="/security" />">Назад</a> <br/>
    <a href="<c:url value="/security/product/add"/>"> Добавить товар </a>
    <%
        Iterator<Product> productIterator = ((List<Product>) request.getAttribute("products")).iterator();
        if (productIterator != null && productIterator.hasNext()) {
    %>
        <table border="0" cellpadding="0" width="100%">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Image</td>
                <td>Admin</td>
                <td>Created at</td>
                <td>Updated at</td>
                <td>View</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>

    <%
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
    %>
        <tr>
            <td> <%=product.getProductId()%> </td>
            <td> <%=product.getName()%> </td>
            <td><img src="<%= product.getImage().getImageDirectory() %>" width="70" height="50"/></td>
            <td> <%=product.getAdmin().getLogin()%> </td>
            <td> <%=product.getCreatedAt()%> </td>
            <td> <%=product.getUpdatedAt()%> </td>
            <td>
                <button class="viewButton"
                        data-id="<%=product.getProductId()%>">
                    view
                </button>
            </td>
            <td> <button class="editButton"
                         data-id="<%=product.getProductId()%>">
                 edit
                </button>
            </td>
            <td> <button class="deleteButton"
                         data-id="<%=product.getProductId()%>"
                         data-csrf-name = "${_csrf.parameterName}"
                         data-csrf-value = "${_csrf.token}"   >
                    delete
                </button>
            </td>
        </tr>
        <% } %>
        </table>
    <% } %>

</body>
</html>


