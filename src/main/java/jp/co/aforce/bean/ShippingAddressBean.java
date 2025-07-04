package jp.co.aforce.bean;

import java.sql.Timestamp;

public class ShippingAddressBean {
	private long address_id;
	private String postal_code;
	private String address;
	private String building;
	private Long user_id;
	private boolean is_default;
	private Timestamp created_at;

	public ShippingAddressBean() {
	}
	public ShippingAddressBean(String postal, String address, String building) {
		this.postal_code= postal;
		this.address = address;
		this.building = building;
		
	}

	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public boolean isIs_default() {
		return is_default;
	}

	public void setIs_default(boolean is_default) {
		this.is_default = is_default;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
