package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.AdminTokenBean;

public class AdminTokensDAO extends DAO {

    public void createAdminToken(AdminTokenBean token) throws Exception {
        String sql = "INSERT INTO admin_tokens (user_id, token) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, token.getUser_id());
            ps.setString(2, token.getToken());
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

    public List<AdminTokenBean> getAdminTokensByAdminId(long admin_id) throws Exception {
        String sql = "SELECT * FROM admin_tokens WHERE user_id = ?";
        List<AdminTokenBean> tokens = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, admin_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tokens.add(mapResultSet(rs));
                }
            }
        }
        return tokens;
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
        token.setUser_id(rs.getLong("user_id"));
        token.setToken(rs.getString("token"));
        token.setExpires_at(rs.getTimestamp("expires_at"));
        return token;
    }
}
