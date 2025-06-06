<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>登録する内容は以下でよろしいですか？</h1>
	<p>
		名前（姓）:${firstname }<br>
		名前(名):${lastname }<br> 住所:${address }<br> メールアドレス:${mail }
	</p>

	<form action="/ShoppingSite/views/member/fixUser" method="post">
		<input type="hidden" name="firstname" value="${firstname}" required>
		<input type="hidden" name="lastname" value="${lastname }" required>
		<input type="hidden" name="address" value="${address}" required>
		<input type="hidden" name="mail" value="${mail }" required>
		<button type="submit" value="record" name="act">確認</button>
		<button type="submit" value="fix" name="act">修正</button>
	</form>
</body>
</html>