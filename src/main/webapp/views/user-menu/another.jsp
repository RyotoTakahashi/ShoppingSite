<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>別ページ</title>
</head>
<body>
<h1>別ページ</h1>
<p>ここはログイン後にアクセスできる別のページです。</p>

<p>現在のログインユーザー: ${user.id}</p>

<form action="../user-menu" method="get">
  <button type="submit">ユーザーメニューに戻る</button>
</form>
</body>
</html>