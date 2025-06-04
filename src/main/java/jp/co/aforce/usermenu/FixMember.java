package jp.co.aforce.usermenu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.User;
import jp.co.aforce.dao.UserDAO;

@WebServlet("/views/user-menu/fix")
public class FixMember extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setLastName(request.getParameter("lastname"));
		user.setFirstName(request.getParameter("firstname"));
		user.setAddress(request.getParameter("address"));
		user.setMailAddress(request.getParameter("mail"));
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.fixUser(user);
			session.setAttribute("user", user);
			response.sendRedirect("/views/user-menu/fix/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/views/user-menu/fix/fail.jsp");

	}

}
