<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ご注文ありがとうございました</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/purchase-complete.css">
</head>
<body>
  <div class="overlay">
    <div class="glass-box">
      <div class="icon">✔</div>
      <h1>Thank you for your purchase!</h1>
      <p>ご注文ありがとうございました。</p>
      <p>確認メールをお送りしましたので、ご確認ください。</p>
      <div class="actions">
        <a href="${pageContext.request.contextPath}/views/home" class="button">ホームに戻る</a>
        <a href="${pageContext.request.contextPath}/views/guest/orderhistory" class="button secondary">購入履歴を見る</a>
      </div>
    </div>
  </div>
</body>
</html>
