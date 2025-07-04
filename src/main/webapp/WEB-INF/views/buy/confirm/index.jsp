<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
  <title>購入確認</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<div class="container">
  <h2>ご注文内容の確認</h2>

  <!-- カート内容表示 -->
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
          <td>${item.productID.}</td>
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

  <form action="${pageContext.request.contextPath}/views/buy/execute" method="post">

    <!-- 配送先選択 -->
    <h3>配送先住所</h3>
    <c:if test="${not empty addressList}">
      <p>登録済み住所から選択：</p>
      <c:forEach var="address" items="${addressList}">
        <label>
          <input type="radio" name="addressId" value="${address.address_id}" ${address.is_default ? "checked" : ""}>
          ${address.postal_code} ${address.address} ${address.building}
        </label><br>
      </c:forEach>
    </c:if>

    <p><strong>または新しい住所を入力：</strong></p>
    <input type="text" name="postal" placeholder="郵便番号"><br>
    <input type="text" name="address" placeholder="住所"><br>
    <input type="text" name="building" placeholder="建物名・部屋番号"><br>
    <label><input type="checkbox" name="saveNewAddress" value="true"> この住所を登録する（ログインユーザーのみ）</label>

    <!-- 支払い方法 -->
    <h3>支払い方法</h3>
    <select name="paymentMethod" required>
      <option value="card">クレジットカード</option>
      <option value="cash">代金引換</option>
    </select>

    <!-- メールアドレス（ゲスト用） -->
    <c:if test="${empty userdata}">
      <h3>メールアドレス</h3>
      <input type="email" name="email" placeholder="メールアドレス" required>
    </c:if>
    <c:if test="${not empty userdata}">
      <input type="hidden" name="email" value="${userdata.email}">
    </c:if>

    <br><br>
    <button type="submit" class="md-button filled">購入確定</button>
    <a href="${pageContext.request.contextPath}/views/buy" class="md-button outlined">カートへ戻る</a>
  </form>
</div>

</body>
</html>
