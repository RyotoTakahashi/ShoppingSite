<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/joinguest.css">
</head>
<body>
	<div class="container">
		<h2>新規会員登録</h2>
		<a href="${pageContext.request.contextPath}/views/home" class="button">ホームに戻る</a>
		<c:if test="${param.error == 'empty'}">
			<div class="error">ユーザー名とパスワードは必須です。</div>
		</c:if>
		<c:if test="${not empty sessionScope.join_error}">
			<div class="error">${sessionScope.join_error}</div>
			<c:remove var="join_error" scope="session" />
		</c:if>

		<form method="post"
			action="${pageContext.request.contextPath}/views/guest/join/confirm">
			<div class="form-group">
				<label>ユーザー名</label><input type="text" name="username" required>
			</div>
			<div class="form-group">
				<label>パスワード</label><input type="password" name="password" required>
			</div>
			<div class="form-group">
				<label>姓</label><input type="text" name="lastname">
			</div>
			<div class="form-group">
				<label>名</label><input type="text" name="firstname">
			</div>
			<div class="form-group">
				<label>年齢</label><input type="number" name="age" min="0">
			</div>
			<div class="form-group gender">
				<label class="gender">性別</label>
				<div class="radio-options">
					<label><input type="radio" name="gender" value="male"
						checked> 男性</label> <label><input type="radio"
						name="gender" value="female"> 女性</label>
				</div>
			</div>
			<div class="form-group">
				<label>メールアドレス</label><input type="email" name="email">
			</div>
			<div class="form-group">
				<label>電話番号</label><input type="tel" name="phone">
			</div>

			<button type="submit" class="btn button-style">確認</button>
		</form>

		<div class="login-link">
			<a href="${pageContext.request.contextPath}/views/guest/login"
				class="btn button-style">ログイン</a>
		</div>
	</div>
</body>
</html>
