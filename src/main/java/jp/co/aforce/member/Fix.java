package jp.co.aforce.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.User;
import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class Join
 */
@WebServlet("/views/member/fixUser")
public class Fix extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {HttpSession session= request.getSession();
		User user = (User)session.getAttribute("user");
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setAddress(request.getParameter("address"));
		user.setMailAddress(request.getParameter("mail"));
		
			UserDAO userDAO = new UserDAO();
			userDAO.fixUser(user);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			response.sendRedirect("fix-error");
			return;
		}
		request.getRequestDispatcher("/views/user-menu").forward(request, response);
		
	}

}
