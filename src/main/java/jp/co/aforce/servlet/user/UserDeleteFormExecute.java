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
@WebServlet("/views/user/delete/execute")
public class UserDeleteFormExecute extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			UsersDAO usersDAO = new UsersDAO();
			long ID = (long) session.getAttribute("user_id");
			UserBean userBean = usersDAO.getUserById(ID);
			usersDAO.deleteUser(userBean);
			response.sendRedirect("/views/delete/success");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/views/delete/error");
		}

	}

}
