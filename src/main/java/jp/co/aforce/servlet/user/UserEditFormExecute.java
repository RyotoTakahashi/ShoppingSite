package jp.co.aforce.servlet.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.UsersDAO;

/**
 * Servlet implementation class UserVerifyFormExecute
 */
@WebServlet("/views/user/edit/execute")
public class UserEditFormExecute extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			UsersDAO usersDAO = new UsersDAO();
			long ID = (long) session.getAttribute("user_id");
			UserBean userBean = new UserBean();
			String firstName = (String) session.getAttribute("firstname");
			String lastName = (String) session.getAttribute("lastname");
			String phone = (String) session.getAttribute("phone");
			userBean = usersDAO.getUserById(ID);
			userBean.setFirst_name(firstName);
			userBean.setLast_name(lastName);
			userBean.setPhone(phone);
			usersDAO.updateUser(userBean);
			
			response.sendRedirect("/views/user/edit/success");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
