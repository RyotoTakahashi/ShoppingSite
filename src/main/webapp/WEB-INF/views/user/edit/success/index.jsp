<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>会員情報の更新完了</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit-success.css">
</head>
<body>
  <div class="overlay">
    <div class="glass-box">
      <div class="icon">✔</div>
      <h1>Your profile has been updated.</h1>
      <p>会員情報の更新が完了しました。</p>
      <p>最新の内容がマイページに反映されています。</p>
      <div class="actions">
        <a href="${pageContext.request.contextPath}/views/user/mypage.jsp" class="button">マイページへ</a>
        <a href="${pageContext.request.contextPath}/views/home" class="button secondary">ホームに戻る</a>
      </div>
    </div>
  </div>
</body>
</html>
