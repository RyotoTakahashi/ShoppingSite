package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.ProductCategoryBean;

public class ProductCategoriesDAO extends DAO {

    public void createProductCategory(ProductCategoryBean pc) throws Exception {
        String sql = "INSERT INTO product_categories (product_id, category_id) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, pc.getProduct_id());
            ps.setInt(2, pc.getCategory_id());
            ps.executeUpdate();
        }
    }

    public ProductCategoryBean getProductCategory(long product_id, int category_id) throws Exception {
        String sql = "SELECT * FROM product_categories WHERE product_id = ? AND category_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, product_id);
            ps.setInt(2, category_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductCategoryBean pc = new ProductCategoryBean();
                    pc.setProduct_id(rs.getLong("product_id"));
                    pc.setCategory_id(rs.getInt("category_id"));
                    return pc;
                }
            }
        }
        return null;
    }

    public void updateProductCategory(ProductCategoryBean pc) throws Exception {
        String sql = "UPDATE product_categories SET category_id = ? WHERE product_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pc.getCategory_id());
            ps.setLong(2, pc.getProduct_id());
            ps.executeUpdate();
        }
    }
}
