package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.User;

public class UserDAO extends DAO {
	public Boolean login(String id, String password) throws Exception {
		Connection con = getConnection();
		String sql = "Select PASSWORD from users where member_id=?";
		PreparedStatement st = con.prepareStatement(sql);
		Boolean isLogin = false;
		st.setString(1, id);
		try {
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String Password = rs.getString("password");
				isLogin = password.equals(Password);
			}
		} catch (Exception e) {
		}

		st.close();
		con.close();
		return isLogin;
	}
	public User getUserData(String id)throws Exception{
		User user = new User();
		Connection con = getConnection();
		String sql = "select last_name, first_name, address, mail_address from users where member_id =?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			String lastName = rs.getString("last_name");
			String firstName = rs.getString("first_name");
			String address = rs.getString("address");
			String mailAddress = rs.getString("mail_address");
			user.setId(id);
			user.setLastName(lastName);
			user.setFirstName(firstName);
			user.setAddress(address);
			user.setMailAddress(mailAddress);
		}

		rs.close();
		st.close();
		con.close();

		return user;
	}
}
