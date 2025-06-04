<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>会員情報編集画面</h1>
<form action="/ShoppingSite/views/user-menu/fix" method="post">
		<div class="firstname">
			<label for="firstname">姓:</label> <input type="text" name="firstname"
				value="${user.firstName }" required>
		</div>
		<div class="lastname">
			<label for="lastname">名:</label> <input type="text" name="lastname"
				value="${user.lastName }" required>
		</div><div class="address">
			<label for="address">住所:</label> <input type="text" name="address"
				value="${user.address }" required>
		</div>
		<div class="mail">
			<label for="mail">メールアドレス:</label> <input type="email" name="mail"
				value="${user.mailAddress }" required>
		</div>
		<input type="submit" value="確認">
		<input type="reset" value="リセット">
</form>
</body>
</html>