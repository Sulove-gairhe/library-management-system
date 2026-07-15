<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle} - Library Book Manager</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<main class="page narrow">
    <header class="page-header">
        <div>
            <h1>${pageTitle}</h1>
            <p>Library Book Manager</p>
        </div>
        <a class="button secondary" href="<c:url value='/books' />">Back to Books</a>
    </header>

    <section class="content-panel">
        <c:url var="formAction" value="${actionUrl}" />
        <form:form method="post" modelAttribute="book" action="${formAction}" cssClass="book-form">
            <form:hidden path="id" />

            <div class="field">
                <label for="title">Title</label>
                <form:input id="title" path="title" />
                <form:errors path="title" cssClass="field-error" />
            </div>

            <div class="field">
                <label for="author">Author</label>
                <form:input id="author" path="author" />
                <form:errors path="author" cssClass="field-error" />
            </div>

            <div class="field">
                <label for="category">Category</label>
                <form:input id="category" path="category" />
                <form:errors path="category" cssClass="field-error" />
            </div>

            <div class="field">
                <label for="quantity">Quantity</label>
                <form:input id="quantity" path="quantity" type="number" min="0" />
                <form:errors path="quantity" cssClass="field-error" />
            </div>

            <div class="form-actions">
                <button class="button" type="submit">
                    <c:choose>
                        <c:when test="${editing}">Update Book</c:when>
                        <c:otherwise>Create Book</c:otherwise>
                    </c:choose>
                </button>
                <a class="button secondary" href="<c:url value='/books' />">Cancel</a>
            </div>
        </form:form>
    </section>
</main>
</body>
</html>
