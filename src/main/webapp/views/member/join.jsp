<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>新規会員登録</h1>
	<form action="/ShoppingSite/views/member/check" method="post">
		<div class="id">
			<label for="id">ID:</label> <input type="text" name="id"
				value="${not empty id ? id : ''}"required>
		</div>
		<div class="password">
			<label for="password">パスワード:</label> <input type="password"
				name="password" value="${not empty password ? password : ''}" required>
		</div>
		<div class="firstname">
			<label for="firstname">姓:</label> <input type="text" name="firstname"
				value="${not empty firstname ? firstname : ''}" required>
		</div>
		<div class="lastname">
			<label for="lastname">名:</label> <input type="text" name="lastname"
				value="${not empty lastname ? lastname : ''}" required>
		</div><div class="address">
			<label for="address">住所:</label> <input type="text" name="address"
				value="${not empty address ? address : ''}" required>
		</div>
		<div class="mail">
			<label for="mail">メールアドレス:</label> <input type="email" name="mail"
				value="${not empty mail ? mail : ''}" required>
		</div>
		<input type="submit" value="確認">
		<input type="reset" value="リセット">
	</form>

</body>
</html>