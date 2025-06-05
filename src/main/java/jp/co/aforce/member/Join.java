package jp.co.aforce.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.User;
import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class Join
 */
@WebServlet("/views/member/registration")
public class Join extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		request.setAttribute("id",request.getParameter("id"));
		request.setAttribute("password", password);
		request.setAttribute("firstname",request.getParameter("firstname"));
		request.setAttribute("lastname",request.getParameter("lastname"));
		request.setAttribute("address",request.getParameter("address"));
		request.setAttribute("mail",request.getParameter("mail"));
		if("fix".equals(request.getParameter("act"))){
			request.getRequestDispatcher("/views/member/join.jsp").forward(request, response);
			return;
		}
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setAddress(request.getParameter("address"));
		user.setMailAddress(request.getParameter("mail"));
		try {
			UserDAO userDAO = new UserDAO();
			userDAO.setNewUser(user, request.getParameter("password"));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/member/joined.jsp").forward(request, response);
		
	}

}
