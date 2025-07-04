<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ユーザー管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-users.css">
</head>
<body>
  <h1>ユーザー管理ページ</h1>

  <!-- ユーザー一覧 -->
  <section>
    <h2>ユーザー一覧</h2>
    <c:forEach var="user" items="${users}">
      <div class="user-row">
        <div>
          <strong>${user.last_name} ${user.first_name}</strong><br>
          電話: ${user.phone} / メール: ${user.email}<br>
          管理者: <c:choose>
                    <c:when test="${user.isAdmin}">✔</c:when>
                    <c:otherwise>✖</c:otherwise>
                  </c:choose>
        </div>
        <div class="user-actions">
          <form method="get" action="${pageContext.request.contextPath}/views/admin/users">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" value="${user.user_id}">
            <button type="submit">編集</button>
          </form>
          <form method="post" action="${pageContext.request.contextPath}/views/admin/users">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="${user.user_id}">
            <button type="submit">削除</button>
          </form>
        </div>
      </div>
    </c:forEach>
  </section>

  <!-- 編集フォーム -->
  <c:if test="${not empty edit_user}">
    <hr>
    <section>
      <h2>ユーザー情報の編集</h2>
      <form method="post" action="${pageContext.request.contextPath}/views/admin/users">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${edit_user.user_id}">
        <p>名前: ${edit_user.last_name} ${edit_user.first_name}</p>
        <p>電話: ${edit_user.phone}</p>
        <p>メール: ${edit_user.email}</p>
        <label for="admin">管理者フラグ:</label>
        <select name="admin" id="admin">
          <option value="admin" ${edit_user.isAdmin ? "selected" : ""}>管理者</option>
          <option value="user"  ${!edit_user.isAdmin ? "selected" : ""}>一般ユーザー</option>
        </select>
        <button type="submit">更新</button>
      </form>
    </section>
  </c:if>
    <a href="${pageContext.request.contextPath}/views/admin">戻る</a>
  <a href="${pageContext.request.contextPath}/views/user/logout/execute">ログアウト</a>
</body>
</html>
