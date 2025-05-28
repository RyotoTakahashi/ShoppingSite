<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ログイン</h1>
	<form action="login" method="post">
		<div><label for="id">ID:</label>
			<input type="text" id="id" name="id" required><br>
		</div>
		<div>
			Password:<input type="password" id="password" name="password" required>
		</div>
		<button type="submit">ログイン</button>
		<button type="submit" action="/View/registration">新規会員登録</button>
	</form>
</body>
</html>