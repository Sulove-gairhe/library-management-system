<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Inventory Product Manager</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<c:if test="${not empty toastMessage}">
    <div class="toast toast-${toastType}" role="status">${toastMessage}</div>
</c:if>
<main class="page">
    <header class="page-header">
        <div>
            <h1>Inventory Product Manager</h1>
            <p>Advanced Java CRUD Web Application</p>
        </div>
        <a class="button" href="<c:url value='/products/new' />">Add Product</a>
    </header>

    <section class="content-panel">
        <c:choose>
            <c:when test="${empty products}">
                <p class="empty-state">No products have been added yet.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>SKU</th>
                        <th>Category</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.sku}</td>
                            <td>${product.category}</td>
                            <td>${product.stock}</td>
                            <td class="actions">
                                <a class="action-link edit-link" href="<c:url value='/products/${product.id}/edit' />">Edit</a>
                                <form method="post" action="<c:url value='/products/${product.id}/delete' />">
                                    <button class="action-link delete-link" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </section>
</main>
</body>
</html>
