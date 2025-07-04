package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.CartBean;

public class CartDAO extends DAO {

	public CartBean getCartItem(Long userId, String guestId, long productId) throws Exception {
		String sql = "SELECT c.*, p.name AS product_name, p.price " +
		             "FROM cart_items c " +
		             "JOIN products p ON c.product_id = p.product_id " +
		             "WHERE c.guest_id = ? AND c.product_id = ? AND ";
		sql += (userId != null) ? "c.user_id = ?" : "c.user_id IS NULL";

		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, guestId);
			ps.setLong(2, productId);
			if (userId != null) {
				ps.setLong(3, userId);
			}
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapCartBean(rs);
				}
			}
		}
		return null;
	}

	public void insertCartItem(CartBean item) throws Exception {
		String sql = "INSERT INTO cart_items (user_id, guest_id, product_id, quantity) VALUES (?, ?, ?, ?)";
		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			if (item.getUserId() != null) {
				ps.setLong(1, item.getUserId());
			} else {
				ps.setNull(1, Types.BIGINT);
			}
			ps.setString(2, item.getGuestId());
			ps.setLong(3, item.getProductId());
			ps.setInt(4, item.getQuantity());
			ps.executeUpdate();
		}
	}

	public void updateCartItem(CartBean item) throws Exception {
		String sql = "UPDATE cart_items SET quantity = ?, updated_at = NOW() WHERE cart_item_id = ?";
		try (Connection con = getConnection();
		     PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, item.getQuantity());
			pstmt.setLong(2, item.getCartItemId());
			pstmt.executeUpdate();
		}
	}

	public List<CartBean> getCartItems(Long userId, String guestId) throws Exception {
		String sql = "SELECT c.*, p.name AS product_name, p.price " +
		             "FROM cart_items c " +
		             "JOIN products p ON c.product_id = p.product_id " +
		             "WHERE c.guest_id = ? AND ";
		sql += (userId != null) ? "c.user_id = ?" : "c.user_id IS NULL";

		List<CartBean> list = new ArrayList<>();
		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, guestId);
			if (userId != null) {
				ps.setLong(2, userId);
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapCartBean(rs));
				}
			}
		}
		return list;
	}

	public void deleteCartItem(long cartItemId) throws Exception {
		String sql = "DELETE FROM cart_items WHERE cart_item_id = ?";
		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, cartItemId);
			ps.executeUpdate();
		}
	}

	public void deleteCartByGuest(String guest_id) throws Exception {
		String sql = "DELETE FROM cart_items WHERE guest_id = ?";
		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, guest_id);
			ps.executeUpdate();
		}
	}

	public void updateQuantity(long cartItemId, int newQuantity) throws Exception {
		String sql = "UPDATE cart_items SET quantity = ? WHERE cart_item_id = ?";
		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, newQuantity);
			ps.setLong(2, cartItemId);
			ps.executeUpdate();
		}
	}

	private CartBean mapCartBean(ResultSet rs) throws Exception {
		CartBean item = new CartBean();
		item.setCartItemId(rs.getLong("cart_item_id"));
		item.setUserId(rs.getObject("user_id", Long.class));
		item.setGuestId(rs.getString("guest_id"));
		item.setProductId(rs.getLong("product_id"));
		item.setQuantity(rs.getInt("quantity"));
		item.setProductName(rs.getString("product_name"));
		item.setPrice(rs.getInt("price"));
		return item;
	}
}
