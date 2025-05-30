package jp.co.aforce.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.User;
import jp.co.aforce.dao.UserDAO;

public class isLogin {
	public static Boolean isValid(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Boolean isLogin = (Boolean) session.getAttribute("login");
		System.out.println(isLogin);
		if (isLogin) {
			User userfromSession = (User) session.getAttribute("user");
			String id = userfromSession.getId();
			User userfromDB = new UserDAO().getUserData(id);
			System.out.println(userfromDB +":"+userfromSession);
			return userfromDB.equals(userfromSession);
		}
		return false;

	}
}
