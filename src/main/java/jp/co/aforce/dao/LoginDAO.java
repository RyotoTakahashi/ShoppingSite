package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.LoginBean;

public class LoginDAO extends DAO {

    public void createLogin(LoginBean login) throws Exception {
        String sql = "INSERT INTO login (user_id, verifier, salt) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, login.getUser_id());
            ps.setString(2, login.getVerifier());
            ps.setString(3, login.getSalt());
            ps.executeUpdate();
        }
    }

    public LoginBean getLoginByUserId(long user_id) throws Exception {
        String sql = "SELECT * FROM login WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, user_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LoginBean login = new LoginBean();
                    login.setUser_id(rs.getLong("user_id"));
                    login.setVerifier(rs.getString("verifier"));
                    login.setSalt(rs.getString("salt"));
                    return login;
                }
            }
        }
        return null;
    }
}
