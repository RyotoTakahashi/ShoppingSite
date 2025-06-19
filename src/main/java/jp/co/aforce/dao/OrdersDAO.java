package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.OrderBean;

public class OrdersDAO extends DAO {

    public void createOrder(OrderBean order) throws Exception {
        String sql = "INSERT INTO orders (user_id, shipping_address_id, order_status, total_amount, order_token, payment_method) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, order.getUser_id());
            ps.setLong(2, order.getShipping_address_id());
            ps.setString(3, order.getOrder_status());
            ps.setBigDecimal(4, order.getTotal_amount());
            ps.setString(5, order.getOrder_token());
            ps.setString(6, order.getPayment_method());
            ps.executeUpdate();
        }
    }

    public OrderBean getOrderById(long order_id) throws Exception {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, order_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    public OrderBean getOrderByUserId(long user_id) throws Exception {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, user_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    public void updateOrder(OrderBean order) throws Exception {
        String sql = "UPDATE orders SET order_status = ?, total_amount = ?, order_token = ?, payment_method = ?, updated_at = CURRENT_TIMESTAMP WHERE order_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, order.getOrder_status());
            ps.setBigDecimal(2, order.getTotal_amount());
            ps.setString(3, order.getOrder_token());
            ps.setString(4, order.getPayment_method());
            ps.setLong(5, order.getOrder_id());
            ps.executeUpdate();
        }
    }

    private OrderBean mapResultSet(ResultSet rs) throws Exception {
        OrderBean order = new OrderBean();
        order.setOrder_id(rs.getLong("order_id"));
        order.setUser_id(rs.getLong("user_id"));
        order.setShipping_address_id(rs.getLong("shipping_address_id"));
        order.setOrder_status(rs.getString("order_status"));
        order.setTotal_amount(rs.getBigDecimal("total_amount"));
        order.setOrder_token(rs.getString("order_token"));
        order.setPayment_method(rs.getString("payment_method"));
        order.setCreated_at(rs.getTimestamp("created_at"));
        order.setUpdated_at(rs.getTimestamp("updated_at"));
        return order;
    }
}
