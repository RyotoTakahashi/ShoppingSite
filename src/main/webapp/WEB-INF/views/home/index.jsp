<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <title>ホーム</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>

<header>
    <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" class="logo">
    <nav>
        <a href="${pageContext.request.contextPath}/views/guest/login">ログイン</a>
        <a href="${pageContext.request.contextPath}/views/buy">カート</a>
    </nav>
</header>

<section class="search-bar">
    <form method="get" action="home">
        <input type="text" name="keyword" value="${keyword}" placeholder="商品名で検索">
        <select name="sort">
            <option value="new" ${sort=="new" ? "selected" : ""}>新着順</option>
            <option value="price_asc" ${sort=="price_asc" ? "selected" : ""}>価格の安い順</option>
            <option value="price_desc" ${sort=="price_desc" ? "selected" : ""}>価格の高い順</option>
        </select>
        <button type="submit">検索</button>
    </form>
</section>

<section class="products">
    <c:forEach var="p" items="${products}">
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/img/${p.product_id}.jpg" alt="${p.name}">
            <h3>${p.name}</h3>
            <p>￥${p.price}</p>
            <p>${p.description}</p>
            <form method="post" action="${pageContext.request.contextPath}/cart/add">
                <input type="hidden" name="product_id" value="${p.product_id}">
                <label>数量：
                    <input type="number" name="quantity" value="1" min="1" max="99">
                </label>
                <button type="submit">カートに追加</button>
            </form>
        </div>
    </c:forEach>
</section>

<section class="pagination">
    <c:if test="${hasPrev}">
        <a href="home?keyword=${keyword}&sort=${sort}&page=${page - 1}">前へ</a>
    </c:if>
    <c:if test="${hasNext}">
        <a href="home?keyword=${keyword}&sort=${sort}&page=${page + 1}">次へ</a>
    </c:if>
</section>

</body>
</html>