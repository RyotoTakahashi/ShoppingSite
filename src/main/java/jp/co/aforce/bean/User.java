package jp.co.aforce.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String lastName;
	private String firstName;
	private String address;
	private String mailAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User Users = (User) obj;
		return this.getId().equals(Users.getId()) &&
				this.getLastName().equals(Users.getLastName()) &&
				this.getFirstName().equals(Users.getFirstName()) &&
				this.getAddress().equals(Users.getAddress()) &&
				this.getMailAddress().equals(Users.getMailAddress());
	}

}
