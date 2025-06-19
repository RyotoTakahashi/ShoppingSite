package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.ShippingAddressBean;

public class ShippingAddressesDAO extends DAO {

    public void createShippingAddress(ShippingAddressBean address) throws Exception {
        String sql = "INSERT INTO shipping_addresses (user_id, postal_code, prefecture, city, street, building, is_default) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, address.getUser_id());
            ps.setString(2, address.getPostal_code());
            ps.setString(3, address.getPrefecture());
            ps.setString(4, address.getCity());
            ps.setString(5, address.getStreet());
            ps.setString(6, address.getBuilding());
            ps.setBoolean(7, address.isIs_default());
            ps.executeUpdate();
        }
    }

    public ShippingAddressBean getShippingAddressById(long address_id) throws Exception {
        String sql = "SELECT * FROM shipping_addresses WHERE address_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, address_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    private ShippingAddressBean mapResultSet(ResultSet rs) throws Exception {
        ShippingAddressBean address = new ShippingAddressBean();
        address.setAddress_id(rs.getLong("address_id"));
        address.setUser_id(rs.getLong("user_id"));
        address.setPostal_code(rs.getString("postal_code"));
        address.setPrefecture(rs.getString("prefecture"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setBuilding(rs.getString("building"));
        address.setIs_default(rs.getBoolean("is_default"));
        address.setCreated_at(rs.getTimestamp("created_at"));
        return address;
    }
}
