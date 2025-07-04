<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>商品管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-products.css">
</head>
<body>
  <h1>商品管理ページ</h1>

  <!-- 新規登録フォーム -->
  <section>
    <h2>新規商品登録</h2>
    <form method="post" action="${pageContext.request.contextPath}/views/admin/products" enctype="multipart/form-data">
      <input type="hidden" name="action" value="create">
      <input type="text" name="name" placeholder="商品名" required>
      <input type="number" name="price" placeholder="価格" required>
      <input type="text" name="description" placeholder="説明">
      <input type="number" name="quantity" placeholder="在庫数" required>
      <input type="file" name="image" accept="image/jpeg" />
      <button type="submit">登録</button>
    </form>
  </section>

  <hr>

  <!-- 商品一覧 -->
  <section>
    <h2>商品一覧</h2>
    <c:forEach var="product" items="${products}">
      <div class="product-row">
        <strong>${product.name}</strong>
        ￥${product.price} / 在庫: ${product.stock_quantity}
        <form method="get" action="${pageContext.request.contextPath}/views/admin/products" style="display:inline;">
          <input type="hidden" name="action" value="edit">
          <input type="hidden" name="id" value="${product.product_id}">
          <button type="submit">編集</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/views/admin/products" style="display:inline;">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="id" value="${product.product_id}">
          <button type="submit">削除</button>
        </form>
      </div>
    </c:forEach>
  </section>

  <!-- 編集フォーム（対象選択時のみ表示） -->
  <c:if test="${not empty edit_product}">
    <hr>
    <section>
      <h2>商品情報の編集</h2>
      <form method="post" action="${pageContext.request.contextPath}/views/admin/products" enctype="multipart/form-data">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${edit_product.product_id}">
        <input type="text" name="name" value="${edit_product.name}" required>
        <input type="number" name="price" value="${edit_product.price}" required>
        <input type="text" name="description" value="${edit_product.description}">
        <input type="number" name="quantity" value="${edit_product.stock_quantity}" required>
        
        <button type="submit">更新</button>
      </form>
    </section>
  </c:if>
  <a href="/ShoppingSite/views/admin">戻る</a>
  <a href="/ShoppingSite/views/user/logout/execute">ログアウト</a>
</body>
</html>
