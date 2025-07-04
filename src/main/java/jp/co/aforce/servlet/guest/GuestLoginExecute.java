package jp.co.aforce.servlet.guest;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.LoginBean;
import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.LoginDAO;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.others.PasswordHasher;

/**
 * Servlet implementation class GuestLoginExecute
 */
@WebServlet("/views/guest/login/execute")
public class GuestLoginExecute extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			LoginDAO login = new LoginDAO();
			LoginBean logindata = login.getLoginByUserName(name);
			if (PasswordHasher.verifyPassword(password, logindata.getSalt(), logindata.getVerifier())) {
				UsersDAO users = new UsersDAO();
				UserBean userData = users.getUserById(logindata.getUser_id());
				session.setAttribute("userdata", userData);
				session.setAttribute("role", "user");
				if (userData.getIsDeleted()) {
					response.sendRedirect("/ShoppingSite/views/guest/delete");
					return;
				}
				if (userData.getIsAdmin()) {
					response.sendRedirect("/ShoppingSite/views/user/verify");
					return;
				} else {
					response.sendRedirect("/ShoppingSite/views/user/menu");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/ShoppingSite/views/guest/login/error");

	}
}
