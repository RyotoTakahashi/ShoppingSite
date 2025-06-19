package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.LoginAdminBean;

public class LoginAdminsDAO extends DAO {

    public void createLoginAdmin(LoginAdminBean loginAdmin) throws Exception {
        String sql = "INSERT INTO login_admin (admin_id, verifier, salt) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, loginAdmin.getAdmin_id());
            ps.setString(2, loginAdmin.getVerifier());
            ps.setString(3, loginAdmin.getSalt());
            ps.executeUpdate();
        }
    }

    public LoginAdminBean getLoginAdminByAdminId(long admin_id) throws Exception {
        String sql = "SELECT * FROM login_admin WHERE admin_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, admin_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LoginAdminBean loginAdmin = new LoginAdminBean();
                    loginAdmin.setAdmin_id(rs.getLong("admin_id"));
                    loginAdmin.setVerifier(rs.getString("verifier"));
                    loginAdmin.setSalt(rs.getString("salt"));
                    return loginAdmin;
                }
            }
        }
        return null;
    }
}