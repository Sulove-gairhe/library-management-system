<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle} - Inventory Product Manager</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<main class="page narrow">
    <header class="page-header">
        <div>
            <h1>${pageTitle}</h1>
            <p>Inventory Product Manager</p>
        </div>
        <a class="button secondary" href="<c:url value='/products' />">Back to Products</a>
    </header>

    <section class="content-panel">
        <c:url var="formAction" value="${actionUrl}" />
        <form:form method="post" modelAttribute="product" action="${formAction}" cssClass="product-form">
            <form:hidden path="id" />

            <div class="field">
                <label for="name">Product Name</label>
                <form:input id="name" path="name" />
                <form:errors path="name" cssClass="field-error" />
            </div>

            <div class="field">
                <label for="sku">SKU</label>
                <form:input id="sku" path="sku" />
                <form:errors path="sku" cssClass="field-error" />
            </div>

            <div class="field">
                <label for="category">Category</label>
                <form:input id="category" path="category" />
                <form:errors path="category" cssClass="field-error" />
            </div>

            <div class="field">
                <label for="stock">Stock</label>
                <form:input id="stock" path="stock" type="number" min="0" />
                <form:errors path="stock" cssClass="field-error" />
            </div>

            <div class="form-actions">
                <button class="button" type="submit">
                    <c:choose>
                        <c:when test="${editing}">Update Product</c:when>
                        <c:otherwise>Create Product</c:otherwise>
                    </c:choose>
                </button>
                <a class="button secondary" href="<c:url value='/products' />">Cancel</a>
            </div>
        </form:form>
    </section>
</main>
</body>
</html>
