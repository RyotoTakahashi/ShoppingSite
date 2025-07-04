package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.UserBean;

public class UsersDAO extends DAO {

    // ユーザー登録
    public void createUser(UserBean user) throws Exception {
        String sql = "INSERT INTO users (username, first_name, last_name, email, phone, ismale, age, isdeleted) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirst_name());
            ps.setString(3, user.getLast_name());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setBoolean(6, user.getIsMale());
            ps.setInt(7, user.getAge());
            ps.setBoolean(8, false);  // 論理削除フラグ
            ps.executeUpdate();
        }
    }

    // IDでユーザー取得
    public UserBean getUserById(long user_id) throws Exception {
        String sql = "SELECT * FROM users WHERE user_id = ?";
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
    public UserBean getUserByUsername(String username) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ?";
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

    // 全ユーザー取得（削除されていないものだけ）
    public List<UserBean> getAllUsers() throws Exception {
        String sql = "SELECT * FROM users";
        List<UserBean> users = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UserBean user = mapResultSet(rs);
                if (!user.getIsDeleted()) {  // 削除済みは除外
                    users.add(user);
                }
            }
        }
        return users;
    }

    // ユーザー情報更新
    public void updateUser(UserBean user) throws Exception {
        String sql = "UPDATE users SET username = ?, first_name = ?, last_name = ?, email = ?, phone = ?, isadmin = ?, age = ?, updated_at = CURRENT_TIMESTAMP "
                   + "WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirst_name());
            ps.setString(3, user.getLast_name());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setBoolean(6, user.getIsAdmin());
            ps.setInt(7, user.getAge());
            ps.setLong(8, user.getUser_id());
            ps.executeUpdate();
        }
    }

    // 論理削除（UserBean版）
    public void deleteUser(UserBean user) throws Exception {
        deleteUser(user.getUser_id());
    }

    // 論理削除（ID版）
    public void deleteUser(long id) throws Exception {
        String sql = "UPDATE users SET isdeleted = true, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    // ResultSet → UserBean 変換
    private UserBean mapResultSet(ResultSet rs) throws Exception {
        UserBean user = new UserBean();
        user.setUser_id(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setIsAdmin(rs.getBoolean("isadmin"));
        user.setIsMale(rs.getBoolean("ismale"));
        user.setAge(rs.getInt("age"));
        user.setCreated_at(rs.getTimestamp("created_at"));
        user.setUpdated_at(rs.getTimestamp("updated_at"));
        user.setIsDeleted(rs.getBoolean("isdeleted"));
        return user;
    }
}
