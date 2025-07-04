<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>会員情報の編集</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
</head>
<body>
  <div class="form-container">
    <h1>会員情報の編集</h1>
    <form action="${pageContext.request.contextPath}/views/user/edit/confirm" method="post">
      <label for="firstname">名</label>
      <input type="text" name="firstname" id="firstname" required
             value="${sessionScope.firstname}">

      <label for="lastname">姓</label>
      <input type="text" name="lastname" id="lastname" required
             value="${sessionScope.lastname}">

      <label for="phone">電話番号</label>
      <input type="tel" name="phone" id="phone" required
             value="${sessionScope.phone}">

      <button type="submit">確認する</button>
    </form>
    <p><a href="${pageContext.request.contextPath}/views/user/meny">マイページへ戻る</a></p>
  </div>
</body>
</html>
