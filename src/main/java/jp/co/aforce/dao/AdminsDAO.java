package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.AdminBean;

public class AdminsDAO extends DAO {

    public void createAdmin(AdminBean admin) throws Exception {
        String sql = "INSERT INTO admins (username, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getFirst_name());
            ps.setString(3, admin.getLast_name());
            ps.setString(4, admin.getEmail());
            ps.executeUpdate();
        }
    }

    public AdminBean getAdminById(long admin_id) throws Exception {
        String sql = "SELECT * FROM admins WHERE admin_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, admin_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    public void updateAdmin(AdminBean admin) throws Exception {
        String sql = "UPDATE admins SET username = ?, first_name = ?, last_name = ?, email = ?, updated_at = CURRENT_TIMESTAMP WHERE admin_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getFirst_name());
            ps.setString(3, admin.getLast_name());
            ps.setString(4, admin.getEmail());
            ps.setLong(5, admin.getAdmin_id());
            ps.executeUpdate();
        }
    }

    private AdminBean mapResultSet(ResultSet rs) throws Exception {
        AdminBean admin = new AdminBean();
        admin.setAdmin_id(rs.getLong("admin_id"));
        admin.setUsername(rs.getString("username"));
        admin.setFirst_name(rs.getString("first_name"));
        admin.setLast_name(rs.getString("last_name"));
        admin.setEmail(rs.getString("email"));
        admin.setCreated_at(rs.getTimestamp("created_at"));
        admin.setUpdated_at(rs.getTimestamp("updated_at"));
        return admin;
    }
}
