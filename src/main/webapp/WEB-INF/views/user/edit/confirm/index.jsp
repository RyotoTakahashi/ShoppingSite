<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>会員情報の確認</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/confirm.css">
</head>
<body>
  <div class="confirm-container">
    <h1>以下の内容で更新しますか？</h1>
    <table>
      <tr><th>名</th><td>${firstname}</td></tr>
      <tr><th>姓</th><td>${lastname}</td></tr>
      <tr><th>電話番号</th><td>${phone}</td></tr>
    </table>
    <form action="${pageContext.request.contextPath}/views/user/edit/execute" method="post">
      <button type="submit">更新する</button>
    </form>
    <form action="${pageContext.request.contextPath}/views/user/edit" method="get">
      <button type="submit">戻って修正する</button>
    </form>
  </div>
</body>
</html>
