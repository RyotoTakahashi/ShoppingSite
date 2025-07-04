<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ログイン</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/guestlogin.css">
  <script src="${pageContext.request.contextPath}/js/jsrp-browser.js"></script>
</head>
<body data-context="${pageContext.request.contextPath}">
  <div class="container">
  <a href="${pageContext.request.contextPath}/views/home" class="button">ホームに戻る</a>
    <h2>ログイン</h2>
    <c:if test="${not empty param.error}">
      <div class="error">ユーザー名またはパスワードが正しくありません。</div>
    </c:if>
    <form id="loginForm" action="${pageContext.request.contextPath}/views/guest/login/execute" method="post" >
      <div class="form-group">
        <label for="username">ユーザー名</label>
        <input type="text" id="username" name="username" required>
      </div>
      <div class="form-group">
        <label for="password">パスワード</label>
        <input type="password" id="password" name="password" required>
      </div>
      <button type="submit" id="loginBtn" class="btn">ログイン</button>
    </form>
    <div class="register-link">
      <a href="${pageContext.request.contextPath}/views/guest/join" class="btn">新規会員登録</a>
    </div>
  </div>
</body>
</html>
