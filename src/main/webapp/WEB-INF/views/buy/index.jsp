<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/material.css">
</head>
<body>
<div class="container">
  <h2>カート内容の確認</h2>

  <table class="material-table">
    <thead>
      <tr>
        <th>商品名</th><th>単価</th><th>数量</th><th>小計</th>
      </tr>
    </thead>
    <tbody>
      <c:set var="total" value="0"/>
      <c:forEach var="item" items="${cartItems}">
        <tr>
          <td>${item.productName}</td>
          <td>¥${item.price}</td>
          <td>${item.quantity}</td>
          <td>¥${item.price * item.quantity}</td>
          <c:set var="total" value="${total + (item.price * item.quantity)}"/>
        </tr>
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="3" style="text-align: right;">合計：</td>
        <td>¥${total}</td>
      </tr>
    </tfoot>
  </table>

  <div class="button-group">
    <a href="${pageContext.request.contextPath}/views/home" class="md-button outlined">戻る</a>
    <form action="${pageContext.request.contextPath}/views/buy/confirm" method="post">
      <button type="submit" class="md-button filled">購入情報入力</button>
    </form>
  </div>
</div>

</body>
</html>