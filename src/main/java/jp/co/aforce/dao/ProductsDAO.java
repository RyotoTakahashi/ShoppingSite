package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.ProductBean;

public class ProductsDAO extends DAO {

    public void createProduct(ProductBean product) throws Exception {
        String sql = "INSERT INTO products (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getStock_quantity());
            ps.executeUpdate();
        }
    }

    public ProductBean getProductById(long product_id) throws Exception {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, product_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        }
        return null;
    }

    public void updateProduct(ProductBean product) throws Exception {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock_quantity = ?, updated_at = CURRENT_TIMESTAMP WHERE product_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getStock_quantity());
            ps.setLong(5, product.getProduct_id());
            ps.executeUpdate();
        }
    }

    private ProductBean mapResultSet(ResultSet rs) throws Exception {
        ProductBean product = new ProductBean();
        product.setProduct_id(rs.getLong("product_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setStock_quantity(rs.getInt("stock_quantity"));
        product.setCreated_at(rs.getTimestamp("created_at"));
        product.setUpdated_at(rs.getTimestamp("updated_at"));
        return product;
    }
}
