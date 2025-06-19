package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.AdminTokenBean;

public class AdminTokensDAO extends DAO {

    public void createAdminToken(AdminTokenBean token) throws Exception {
        String sql = "INSERT INTO admin_tokens (admin_id, token, expires_at, used) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, token.getAdmin_id());
            ps.setString(2, token.getToken());
            ps.setTimestamp(3, token.getExpires_at());
            ps.setBoolean(4, token.isUsed());
            ps.executeUpdate();
        }
    }

    public AdminTokenBean getAdminTokenById(long token_id) throws Exception {
        String sql = "SELECT * FROM admin_tokens WHERE token_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, token_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    public AdminTokenBean getAdminTokenByAdminId(long admin_id) throws Exception {
        String sql = "SELECT * FROM admin_tokens WHERE admin_id = ?";
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

    public void deleteAdminToken(long token_id) throws Exception {
        String sql = "DELETE FROM admin_tokens WHERE token_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, token_id);
            ps.executeUpdate();
        }
    }

    private AdminTokenBean mapResultSet(ResultSet rs) throws Exception {
        AdminTokenBean token = new AdminTokenBean();
        token.setToken_id(rs.getLong("token_id"));
        token.setAdmin_id(rs.getLong("admin_id"));
        token.setToken(rs.getString("token"));
        token.setExpires_at(rs.getTimestamp("expires_at"));
        token.setUsed(rs.getBoolean("used"));
        token.setCreated_at(rs.getTimestamp("created_at"));
        return token;
    }
}
