package jp.co.aforce.bean;

public class ProductCategoryBean {
    private long product_id;
    private int category_id;

    public ProductCategoryBean() {}

    public long getProduct_id() { return product_id; }
    public void setProduct_id(long product_id) { this.product_id = product_id; }

    public int getCategory_id() { return category_id; }
    public void setCategory_id(int category_id) { this.category_id = category_id; }
}
