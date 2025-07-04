<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者認証</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminverify.css">
</head>
<body>
<div class="verify-container">
    <div class="verify-box">
        <h2>管理者認証</h2>
        <p>セキュリティコードを入力してください</p>
        <form action="${pageContext.request.contextPath}/views/user/verify/execute" method="post">
            <input type="text" name="token" maxlength="8" pattern="\d*" required autocomplete="off" />
            <button type="submit">認証する</button>
        </form>
    </div>
</div>
</body>
</html>
