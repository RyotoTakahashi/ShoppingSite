<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Login Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/errorpage.css">
</head>
<body>
    <a href="${pageContext.request.contextPath}/views/user/edit" class="back-button">← Back to edit</a>
    <div class="error-container">
        <div class="error-box">
            <h1>Sorry, something went wrong.</h1>
            <p>もう一度やり直してください.</p>
        </div>
    </div>
</body>
</html>
