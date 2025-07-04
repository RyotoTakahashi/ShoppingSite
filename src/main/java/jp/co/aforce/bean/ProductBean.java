package jp.co.aforce.bean;

import java.sql.Timestamp;

public class ProductBean {
    private long product_id;
    private String name;
    private String description;
    private int price;
    private int stock_quantity;
    private Timestamp created_at;
    private Timestamp updated_at;

    public ProductBean() {}

    public long getProduct_id() { return product_id; }
    public void setProduct_id(long product_id) { this.product_id = product_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPrice() { return price; }
    public void setPrice(int i) { this.price = i; }

    public int getStock_quantity() { return stock_quantity; }
    public void setStock_quantity(int stock_quantity) { this.stock_quantity = stock_quantity; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    public Timestamp getUpdated_at() { return updated_at; }
    public void setUpdated_at(Timestamp updated_at) { this.updated_at = updated_at; }
}
