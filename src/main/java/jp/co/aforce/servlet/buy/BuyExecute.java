package jp.co.aforce.servlet.buy;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import jp.co.aforce.api.OrderMailSend;
import jp.co.aforce.bean.CartBean;
import jp.co.aforce.bean.OrderBean;
import jp.co.aforce.bean.OrderItemBean;
import jp.co.aforce.bean.ProductBean;
import jp.co.aforce.bean.ShippingAddressBean;
import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.CartDAO;
import jp.co.aforce.dao.OrderItemsDAO;
import jp.co.aforce.dao.OrdersDAO;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.dao.ShippingAddressesDAO;
import jp.co.aforce.servlet.cart.CartUtils;

@WebServlet("/views/buy/execute")
public class BuyExecute extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean users = (UserBean) session.getAttribute("userdata");
		String guest_id = CartUtils.getOrCreateGuestId(request, response);

		// パラメータ取得
		String addressIdStr = request.getParameter("addressId");
		String postal = request.getParameter("postal");
		String address = request.getParameter("address");
		String building = request.getParameter("building");
		String payment = request.getParameter("paymentMethod");
		String email = request.getParameter("email");
		String saveNew = request.getParameter("saveNewAddress");

		Connection con = null;

		try {
			@SuppressWarnings("unchecked")
			List<CartBean> cartItems = (List<CartBean>) session.getAttribute("cartItems");

			if (cartItems == null || cartItems.isEmpty()) {
				throw new Exception("カートが空です。");
			}

			// 配送先住所IDの決定
			long addressId;
			ShippingAddressesDAO shipdao = new ShippingAddressesDAO();

			if (StringUtils.isNotBlank(addressIdStr)) {
				// 既存住所使用
				addressId = Long.parseLong(addressIdStr);
			} else {
				// 新規入力
				if (StringUtils.isAnyBlank(postal, address, building)) {
					response.sendRedirect(request.getContextPath() + "/views/buy/error");
					return;
				}
				ShippingAddressBean newAddress = new ShippingAddressBean(postal, address, building);
				if (users != null && "true".equals(saveNew)) {
					newAddress.setUser_id(users.getUser_id());
				}
				addressId = shipdao.createShippingAddress(newAddress);
			}

			ProductsDAO productsDAO = new ProductsDAO();
			OrdersDAO ordersDAO = new OrdersDAO();
			OrderItemsDAO itemsDAO = new OrderItemsDAO();

			con = ordersDAO.getConnection();
			con.setAutoCommit(false);

			int total_price = 0;

			// 在庫チェック
			for (CartBean item : cartItems) {
				ProductBean product = productsDAO.getProductById(item.getProductId(), con);
				if (product.getStock_quantity() < item.getQuantity()) {
					throw new Exception("在庫不足: 商品ID " + item.getProductId());
				}
			}

			// 在庫減算・金額計算
			for (CartBean item : cartItems) {
				ProductBean product = productsDAO.getProductById(item.getProductId(), con);
				int item_price = product.getPrice();
				total_price += item_price * item.getQuantity();
				productsDAO.decreaseStock(item.getProductId(), item.getQuantity(), con);
			}

			// 注文作成
			OrderBean order = new OrderBean();
			order.setShipping_address_id(addressId);
			order.setTotal_amount(total_price);
			order.setPayment_method(payment);
			order.setOrder_token(generateHashedToken(guest_id, 4));
			order.setOrder_number(generateOrderNumber());
			if (users != null) {
				order.setUser_id(users.getUser_id());
			}

			long orderId = ordersDAO.createOrders(order, con);

			// 注文商品登録
			for (CartBean item : cartItems) {
				ProductBean product = productsDAO.getProductById(item.getProductId(), con);
				OrderItemBean orderItem = new OrderItemBean();
				orderItem.setOrder_id(orderId);
				orderItem.setProduct_id(product.getProduct_id());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setUnit_price(BigDecimal.valueOf(product.getPrice()));
				itemsDAO.createOrderItem(orderItem, con);
			}

			con.commit();

			// メール送信（コミット後）
			OrderMailSend mailSend = new OrderMailSend();
			mailSend.sendOrderConfirmationMail(email, order.getOrder_number(), order.getOrder_token(), payment, total_price);

			// セッション後処理
			session.setAttribute("ordernum", order.getOrder_number());
			session.removeAttribute("cartItems");
			CartDAO cartDAO = new CartDAO();
			cartDAO.deleteCartByGuest(guest_id);

			response.sendRedirect(request.getContextPath() + "/views/buy/success");

		} catch (Exception e) {
			e.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			request.setAttribute("error", "購入処理中にエラーが発生しました");
			request.getRequestDispatcher("/views/buy/error.jsp").forward(request, response);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private String generateRandomString(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		}
		return sb.toString();
	}

	private String generateHashedToken(String guestId, int securitylength) throws Exception {
		String combined = guestId + generateRandomString(securitylength);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(combined.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			hexString.append(String.format("%02x", b));
		}
		return hexString.substring(0, 32);
	}

	private String generateOrderNumber() {
		String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String randomPart = generateRandomString(6).toUpperCase();
		return "ORDER-" + datePart + "-" + randomPart;
	}
}
