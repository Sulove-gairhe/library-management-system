<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error - Inventory Product Manager</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<main class="page narrow">
    <header class="page-header">
        <div>
            <h1>Inventory Product Manager</h1>
            <p>Advanced Java CRUD Web Application</p>
        </div>
    </header>
    <section class="content-panel">
        <h2>Something went wrong</h2>
        <p class="field-error">${message}</p>
        <a class="button" href="<c:url value='/products' />">Back to Products</a>
    </section>
</main>
</body>
</html>
