package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.ProductBean;

public class ProductsDAO extends DAO {

	public void createProduct(ProductBean product) throws Exception {
		String sql = "INSERT INTO products (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getStock_quantity());
			ps.executeUpdate();
		}
	}

	public ProductBean getProductById(long product_id)throws Exception {
		String sql = "SELECT * FROM products WHERE product_id = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, product_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSet(rs);
				}
			}

			return null;
		}
	}

	public ProductBean getProductById(long product_id, Connection con) throws Exception {
		String sql = "SELECT * FROM products WHERE product_id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		{
			ps.setLong(1, product_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSet(rs);
				}
			}

			return null;
		}
	}

	public void updateProduct(ProductBean product) throws Exception {
		String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock_quantity = ?, updated_at = CURRENT_TIMESTAMP WHERE product_id = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getStock_quantity());
			ps.setLong(5, product.getProduct_id());
			ps.executeUpdate();
		}
	}

	public List<ProductBean> getAllProduct() throws Exception {
		String sql = "SELECT * FROM products";
		List<ProductBean> products = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					products.add(mapResultSet(rs));
				}
			}
		}
		return products;
	}
	public long createProductAndReturnId(ProductBean p) throws Exception {
	    String sql = "INSERT INTO products (name, price, description, stock_quantity) VALUES (?, ?, ?, ?)";
	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, p.getName());
	        ps.setInt(2, p.getPrice());
	        ps.setString(3, p.getDescription());
	        ps.setInt(4, p.getStock_quantity());
	        ps.executeUpdate();

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) return rs.getLong(1);
	            else throw new SQLException("Product IDの取得に失敗しました。");
	        }
	    }
	}
	public void deleteProductById(long product_id) throws Exception {
		String sql = "DELETE FROM products WHERE product_id = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, product_id);
			int a = ps.executeUpdate();
			System.out.print(a);
		}
	}

	public List<ProductBean> searchByName(String keyword, int page, int pageSize) throws Exception {
		List<ProductBean> results = new ArrayList<>();

		String sql = "SELECT * FROM products WHERE name LIKE ? ORDER BY product_id LIMIT ? OFFSET ?";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, "%" + keyword + "%");
			ps.setInt(2, pageSize);
			ps.setInt(3, (page - 1) * pageSize);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					results.add(mapResultSet(rs));
				}
			}
		}

		return results;
	}

	public void decreaseStock(long productId, int quantity, Connection con) throws Exception {
		String sql = "UPDATE products SET stock_quantity = stock_quantity - ? " +
				"WHERE product_id = ? AND stock_quantity >= ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, quantity); // 減らす数量
			ps.setLong(2, productId); // 商品ID
			ps.setInt(3, quantity); // 条件：在庫が足りてるか

			int updated = ps.executeUpdate();

			if (updated == 0) {
				throw new Exception("在庫が不足しています（product_id=" + productId + "）");
			}
		}
	}
	public List<ProductBean> searchProducts(String keyword, String sort, int limit, int offset) throws Exception {
	    List<ProductBean> results = new ArrayList<>();

	    String baseSql = "SELECT * FROM products WHERE name LIKE ?";
	    String orderBy;

	    switch (sort) {
	        case "price_asc":
	            orderBy = " ORDER BY price ASC";
	            break;
	        case "price_desc":
	            orderBy = " ORDER BY price DESC";
	            break;
	        default:
	            orderBy = " ORDER BY product_id DESC";  // 新着順
	    }

	    String sql = baseSql + orderBy + " LIMIT ? OFFSET ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, "%" + keyword + "%");
	        ps.setInt(2, limit);
	        ps.setInt(3, offset);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                results.add(mapResultSet(rs));
	            }
	        }
	    }

	    return results;
	}
	public int countProducts(String keyword) throws Exception {
	    String sql = "SELECT COUNT(*) FROM products WHERE name LIKE ?";
	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, "%" + keyword + "%");

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) return rs.getInt(1);
	        }
	    }
	    return 0;
	}


	private ProductBean mapResultSet(ResultSet rs) throws Exception {
		ProductBean product = new ProductBean();
		product.setProduct_id(rs.getLong("product_id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getInt("price"));
		product.setStock_quantity(rs.getInt("stock_quantity"));
		product.setCreated_at(rs.getTimestamp("created_at"));
		product.setUpdated_at(rs.getTimestamp("updated_at"));
		return product;
	}

}
