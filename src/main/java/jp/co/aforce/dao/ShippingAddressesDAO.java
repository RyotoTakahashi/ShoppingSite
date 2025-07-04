package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.ShippingAddressBean;

public class ShippingAddressesDAO extends DAO {

    // 住所登録（user_idあり/なし対応）
    public long createShippingAddress(ShippingAddressBean address) throws Exception {
        String sql = "INSERT INTO shipping_addresses (user_id, postal_code, street, building, is_default) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (address.getUser_id() == null) {
                ps.setNull(1, java.sql.Types.BIGINT);
            } else {
                ps.setLong(1, address.getUser_id());
            }
            ps.setString(2, address.getPostal_code());
            ps.setString(3, address.getAddress());
            ps.setString(4, address.getBuilding());
            ps.setBoolean(5, address.isIs_default());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        throw new Exception("配送先の登録に失敗しました。");
    }

    // user_id からすべての住所を取得
    public List<ShippingAddressBean> getShippingAddressesByUserId(long userId) throws Exception {
        List<ShippingAddressBean> list = new ArrayList<>();
        String sql = "SELECT * FROM shipping_addresses WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }
        }
        return list;
    }

    // デフォルト住所を取得
    public ShippingAddressBean getDefaultAddressByUserId(long userId) throws Exception {
        String sql = "SELECT * FROM shipping_addresses WHERE user_id = ? AND is_default = TRUE LIMIT 1";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    // address_idで取得
    public ShippingAddressBean getShippingAddressById(long addressId) throws Exception {
        String sql = "SELECT * FROM shipping_addresses WHERE address_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, addressId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    // デフォルト住所の切り替え
    public void updateDefaultAddress(long userId, long addressId) throws Exception {
        String unsetSql = "UPDATE shipping_addresses SET is_default = FALSE WHERE user_id = ?";
        String setSql = "UPDATE shipping_addresses SET is_default = TRUE WHERE address_id = ?";
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement(unsetSql);
                 PreparedStatement ps2 = con.prepareStatement(setSql)) {

                ps1.setLong(1, userId);
                ps1.executeUpdate();

                ps2.setLong(1, addressId);
                ps2.executeUpdate();

                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    // 住所削除
    public void deleteShippingAddress(long addressId) throws Exception {
        String sql = "DELETE FROM shipping_addresses WHERE address_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, addressId);
            ps.executeUpdate();
        }
    }

    // ResultSet → Bean
    private ShippingAddressBean mapResultSet(ResultSet rs) throws Exception {
        ShippingAddressBean address = new ShippingAddressBean();
        address.setAddress_id(rs.getLong("address_id"));
        long uid = rs.getLong("user_id");
        address.setUser_id(rs.wasNull() ? null : uid);
        address.setPostal_code(rs.getString("postal_code"));
        address.setAddress(rs.getString("street"));
        address.setBuilding(rs.getString("building"));
        address.setIs_default(rs.getBoolean("is_default"));
        address.setCreated_at(rs.getTimestamp("created_at"));
        return address;
    }
}
