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
            <h1>Oops! Login didn’t work.</h1>
            <p>Please check your credentials and try again.</p>
        </div>
    </div>
</body>
</html>
