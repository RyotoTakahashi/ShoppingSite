<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>アカウント削除済み</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error-style.css">
</head>
<body>
  <a href="${pageContext.request.contextPath}/views/home" class="back-button">← ホームに戻る</a>

  <div class="error-container">
    <div class="error-box">
      <h1>You have been logged out.</h1>
      <p>ログアウトが正常に完了しました。</p>
      <p>またのご利用をお待ちしております。</p>
      <p style="margin-top: 2em;">
        <a href="${pageContext.request.contextPath}/views/guest/login" class="back-button" style="font-size:1em;">ログインフォームに戻る</a>
      </p>
    </div>
  </div>
</body>
</html>
