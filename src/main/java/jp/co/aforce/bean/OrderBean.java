package jp.co.aforce.bean;

import java.sql.Timestamp;

public class OrderBean {
    private long order_id;
    private long user_id;
    private long shipping_address_id;
    private int total_amount;
    private String order_token;
    private String order_number;
    private String payment_method;
    private String guest_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    public OrderBean() {}

    public long getOrder_id() { return order_id; }
    public void setOrder_id(long order_id) { this.order_id = order_id; }

    public long getUser_id() { return user_id; }
    public void setUser_id(long user_id) { this.user_id = user_id; }

    public long getShipping_address_id() { return shipping_address_id; }
    public void setShipping_address_id(long shipping_address_id) { this.shipping_address_id = shipping_address_id; }

    public int getTotal_amount() { return total_amount; }
    public void setTotal_amount(int total_price) { this.total_amount = total_price; }

    public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getOrder_token() { return order_token; }
    public void setOrder_token(String order_token) { this.order_token = order_token; }

    public String getPayment_method() { return payment_method; }
    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    public Timestamp getUpdated_at() { return updated_at; }
    public void setUpdated_at(Timestamp updated_at) { this.updated_at = updated_at; }

	public String getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}
}
