<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error - Library Book Manager</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<main class="page narrow">
    <section class="content-panel">
        <h1>Something went wrong</h1>
        <p class="field-error">${message}</p>
        <a class="button" href="<c:url value='/books' />">Back to Books</a>
    </section>
</main>
</body>
</html>
