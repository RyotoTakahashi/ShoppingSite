package jp.co.aforce.bean;

import java.math.BigDecimal;

public class OrderItemBean {
    private long order_item_id;
    private long order_id;
    private long product_id;
    private int quantity;
    private BigDecimal unit_price;

    public OrderItemBean() {}

    public long getOrder_item_id() { return order_item_id; }
    public void setOrder_item_id(long order_item_id) { this.order_item_id = order_item_id; }

    public long getOrder_id() { return order_id; }
    public void setOrder_id(long order_id) { this.order_id = order_id; }

    public long getProduct_id() { return product_id; }
    public void setProduct_id(long product_id) { this.product_id = product_id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getUnit_price() { return unit_price; }
    public void setUnit_price(BigDecimal unit_price) { this.unit_price = unit_price; }
}
