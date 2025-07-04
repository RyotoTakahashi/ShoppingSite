package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jp.co.aforce.bean.OrderItemBean;

public class OrderItemsDAO extends DAO {

	public void createOrderItem(OrderItemBean item) throws Exception {
		String sql = "INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, item.getOrder_id());
			ps.setLong(2, item.getProduct_id());
			ps.setInt(3, item.getQuantity());
			ps.setBigDecimal(4, item.getUnit_price());
			ps.executeUpdate();
		}
	}

	public long createOrderItem(OrderItemBean item, Connection con) throws Exception {
		String sql = "INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, item.getOrder_id());
		ps.setLong(2, item.getProduct_id());
		ps.setInt(3, item.getQuantity());
		ps.setBigDecimal(4, item.getUnit_price());
		long a = ps.executeLargeUpdate(sql, Statement.RETURN_GENERATED_KEYS );
		return a;
	}

	public OrderItemBean getOrderItemById(long order_item_id) throws Exception {
		String sql = "SELECT * FROM order_items WHERE order_item_id = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, order_item_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSet(rs);
				}
			}
		}
		return null;
	}

	private OrderItemBean mapResultSet(ResultSet rs) throws Exception {
		OrderItemBean item = new OrderItemBean();
		item.setOrder_item_id(rs.getLong("order_item_id"));
		item.setOrder_id(rs.getLong("order_id"));
		item.setProduct_id(rs.getLong("product_id"));
		item.setQuantity(rs.getInt("quantity"));
		item.setUnit_price(rs.getBigDecimal("unit_price"));
		return item;
	}
}
