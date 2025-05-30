<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user-menu/index.jsp</title>
</head>
<body>
<h1>ようこそ${user.id }!</h1>
<form action="another.jsp" method="get">
  <button type="submit">修正</button>
</form>
<form action="" method="get">
  <button type="submit">削除</button>
</form>
<form action="../logout" method="get">
  <button type="submit">ログアウト</button>
</form>
</body>
</html>