package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jp.co.aforce.bean.OrderBean;

public class OrdersDAO extends DAO {

    public void createOrder(OrderBean order) throws Exception {
        String sql = "INSERT INTO orders (user_id, shipping_address_id, total_amount, order_token, payment_method, order_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setObject(1, order.getUser_id(), java.sql.Types.BIGINT);
            ps.setLong(2, order.getShipping_address_id());
            ps.setInt(3, order.getTotal_amount());
            ps.setString(4, order.getOrder_token());
            ps.setString(5, order.getPayment_method());
            ps.setString(6, order.getOrder_number());
            ps.executeUpdate();
        }
    }
    public void createOrder(OrderBean order, Connection con) throws Exception {
        String sql = "INSERT INTO orders (user_id, shipping_address_id, total_amount, order_token, payment_method, order_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

             PreparedStatement ps = con.prepareStatement(sql);
        	ps.setObject(1, order.getUser_id(), java.sql.Types.BIGINT);
            ps.setLong(2, order.getShipping_address_id());
            ps.setInt(3, order.getTotal_amount());
            ps.setString(4, order.getOrder_token());
            ps.setString(5, order.getPayment_method());
            ps.setString(6, order.getOrder_number());
            ps.executeUpdate();
        
    }
    
    public long createOrders(OrderBean order, Connection con) throws Exception {
        String sql = "INSERT INTO orders (user_id, shipping_address_id, total_amount, order_token, payment_method, order_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

             PreparedStatement ps = con.prepareStatement(sql);
        	ps.setObject(1, order.getUser_id(), java.sql.Types.BIGINT);
            ps.setLong(2, order.getShipping_address_id());
            ps.setInt(3, order.getTotal_amount());
            ps.setString(4, order.getOrder_token());
            ps.setString(5, order.getPayment_method());
            ps.setString(6, order.getOrder_number());
            long a = ps.executeLargeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            return a;
        
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

    public OrderBean getOrderByToken(String token) throws Exception {
        String sql = "SELECT * FROM orders WHERE order_token = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, token);
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
    public OrderBean getOrderByGuestID(String guest_id)throws Exception{
    	 String sql = "SELECT * FROM orders WHERE user_id = ?";
         try (Connection con = getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setString(1, guest_id);
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return mapResultSet(rs);
                 }
             }
         }
         return null;
    }

    public void updateOrder(OrderBean order) throws Exception {
        String sql = "UPDATE orders SET total_amount = ?, order_token = ?, payment_method = ?, updated_at = CURRENT_TIMESTAMP WHERE order_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, order.getTotal_amount());
            ps.setString(2, order.getOrder_token());
            ps.setString(3, order.getPayment_method());
            ps.setLong(4, order.getOrder_id());
            ps.executeUpdate();
        }
    }

    private OrderBean mapResultSet(ResultSet rs) throws Exception {
        OrderBean order = new OrderBean();
        order.setOrder_id(rs.getLong("order_id"));
        order.setUser_id(rs.getLong("user_id"));
        order.setShipping_address_id(rs.getLong("shipping_address_id"));
        order.setTotal_amount(rs.getInt("total_amount"));
        order.setOrder_token(rs.getString("order_token"));
        order.setPayment_method(rs.getString("payment_method"));
        order.setCreated_at(rs.getTimestamp("created_at"));
        order.setUpdated_at(rs.getTimestamp("updated_at"));
        return order;
    }
}
