<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    // ELで使うために request に格納
    jp.co.aforce.bean.UserBean user = (jp.co.aforce.bean.UserBean) session.getAttribute("user");
    String username = (String) session.getAttribute("username");
    request.setAttribute("user", user);
    request.setAttribute("username", username);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>会員登録確認</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/joinconfirm.css">
</head>
<body>
    <div class="container">
        <h2>以下の内容で登録しますか？</h2>

        <table class="confirm-table">
            <tr><th>ユーザー名</th><td><c:out value="${username}" /></td></tr>
            <tr><th>氏名</th><td><c:out value="${user.last_name}" /> <c:out value="${user.first_name}" /></td></tr>
            <tr><th>年齢</th><td><c:out value="${user.age}" /></td></tr>
            <tr><th>性別</th><td><c:out value="${user.isMale ? '男性' : '女性'}" /></td></tr>
            <tr><th>メールアドレス</th><td><c:out value="${user.email}" /></td></tr>
            <tr><th>電話番号</th><td><c:out value="${user.phone}" /></td></tr>
            <tr><th>パスワード</th><td><span class="disabled-text">（非表示）</span></td></tr>
        </table>

        <div class="button-group">
            <form action="${pageContext.request.contextPath}/views/guest/join/execute" method="post">
                <button type="submit" class="btn confirm">登録する</button>
            </form>
            <form action="${pageContext.request.contextPath}/views/guest/join" method="get">
                <button type="submit" class="btn back">修正する</button>
            </form>
        </div>
    </div>
</body>
</html>
