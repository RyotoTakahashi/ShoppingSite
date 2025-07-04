<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者メニュー</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminfront.css">
</head>
<body>
    <div class="admin-menu">
        <h1>管理者ページ</h1>
        <div class="menu-buttons">
            <form action="${pageContext.request.contextPath}/views/admin/products" method="get">
                <button type="submit">商品管理</button>
            </form>
            <form action="${pageContext.request.contextPath}/views/admin/users" method="get">
                <button type="submit">ユーザー管理</button>
            </form>
        </div>
    </div>
</body>
</html>
