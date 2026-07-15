<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Book Manager</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<main class="page">
    <header class="page-header">
        <div>
            <h1>Library Book Manager</h1>
            <p>Advanced Java CRUD Project</p>
        </div>
        <a class="button" href="<c:url value='/books/new' />">Add Book</a>
    </header>

    <section class="content-panel">
        <c:choose>
            <c:when test="${empty books}">
                <p class="empty-state">No books have been added yet.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.category}</td>
                            <td>${book.quantity}</td>
                            <td class="actions">
                                <a href="<c:url value='/books/${book.id}/edit' />">Edit</a>
                                <form method="post" action="<c:url value='/books/${book.id}/delete' />">
                                    <button class="link-button" type="submit">Delete</button>
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
