
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login-in.jsp</title>
</head>
<body>
<h1>ログイン</h1>
<form action ="../login" method ="post">
<p>ID:<input type="text" name="id"><br>
Password:<input type="password" name="password"></p>
<button type = "submit" name="action" value="login">ログイン</button>
<button type = "submit" name="action" value="registtration" >新規会員登録</button>
</form>
</body>
</html>