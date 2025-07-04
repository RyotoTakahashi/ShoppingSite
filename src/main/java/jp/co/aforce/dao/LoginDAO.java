package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.LoginBean;

public class LoginDAO extends DAO {

    // ログイン情報の新規登録（重複でエラーになる可能性あり）
    public void createLogin(LoginBean login) throws Exception {
        String sql = "INSERT INTO login (username, user_id, verifier, salt) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, login.getUserName());
            ps.setLong(2, login.getUser_id());
            ps.setString(3, login.getVerifier());
            ps.setString(4, login.getSalt());
            ps.executeUpdate();
        }
    }

    // ユーザーIDで取得
    public LoginBean getLoginByUserId(long user_id) throws Exception {
        String sql = "SELECT * FROM login WHERE user_id = ?";
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

    // ユーザー名で取得
    public LoginBean getLoginByUserName(String username) throws Exception {
        String sql = "SELECT * FROM login WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    // REPLACE INTO で既存あれば上書き（MySQL専用）
    public void upsertLogin(LoginBean login) throws Exception {
        String sql = "REPLACE INTO login (username, user_id, verifier, salt) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, login.getUserName());
            ps.setLong(2, login.getUser_id());
            ps.setString(3, login.getVerifier());
            ps.setString(4, login.getSalt());
            ps.executeUpdate();
        }
    }

    // 共通のマッピング処理
    private LoginBean mapResultSet(ResultSet rs) throws Exception {
        LoginBean login = new LoginBean();
        login.setUserName(rs.getString("username"));
        login.setUser_id(rs.getLong("user_id"));
        login.setVerifier(rs.getString("verifier"));
        login.setSalt(rs.getString("salt"));
        return login;
    }
}
