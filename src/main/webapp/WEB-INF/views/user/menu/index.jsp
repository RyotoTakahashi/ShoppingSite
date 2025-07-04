<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ユーザーメニュー</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_menu.css">
</head>
<body>
    <div class="menu-container">
        <h1>ユーザーメニュー</h1>
        <div class="menu-buttons">
            <form action="${pageContext.request.contextPath}/views/user/edit" method="get">
                <button type="submit">情報を編集</button>
            </form>
            <form action="${pageContext.request.contextPath}/views/user/delete" method="get">
                <button type="submit">アカウント削除</button>
            </form>
            <form action="${pageContext.request.contextPath}/views/order/history" method="get">
                <button type="submit">注文履歴を見る</button>
            </form>
            <form action="${pageContext.request.contextPath}/views/home" method="get">
                <button type="submit">ホーム画面へ戻る</button>
            </form>
        </div>
    </div>
</body>
</html>
