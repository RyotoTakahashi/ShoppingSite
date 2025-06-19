package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.CategoryBean;

public class CategoriesDAO extends DAO {

    public void createCategory(CategoryBean category) throws Exception {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();
        }
    }

    public CategoryBean getCategoryById(int category_id) throws Exception {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, category_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    public void updateCategory(CategoryBean category) throws Exception {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategory_id());
            ps.executeUpdate();
        }
    }

    public void deleteCategory(int category_id) throws Exception {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, category_id);
            ps.executeUpdate();
        }
    }

    private CategoryBean mapResultSet(ResultSet rs) throws Exception {
        CategoryBean category = new CategoryBean();
        category.setCategory_id(rs.getInt("category_id"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        return category;
    }
}
