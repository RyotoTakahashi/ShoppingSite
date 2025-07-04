<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Login Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/errorpage.css">
</head>
<body>
    <a href="${pageContext.request.contextPath}/views/guest/login" class="back-button">← Back to Login</a>
    <div class="error-container">
        <div class="error-box">
            <h1>Sorry, your session has expired.</h1>
            <p>Please log in again to continue.</p>
        </div>
    </div>
</body>
</html>
