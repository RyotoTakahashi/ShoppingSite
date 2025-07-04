<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.*, jp.co.aforce.OrderBean" %>
<html>
<head>
    <title>注文履歴</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order_history.css">
</head>
<body>
  <div class="history-container">
    <h2>注文履歴</h2>
    <c:if test="${empty orders}">
        <p>注文履歴は見つかりませんでした。</p>
    </c:if>
    <c:forEach var="order" items="${order}">
        <div class="order-card">
            <p>注文番号: ${order.order_id}</p>
            <p>合計金額: ¥${order.getTotal_amount}</p>
        </div>
    </c:forEach>
    </div>
</body>
</html>