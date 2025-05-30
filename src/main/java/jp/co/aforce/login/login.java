package jp.co.aforce.login;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.User;
import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class login
 */
@WebServlet("/views/login")
public class login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String SigninorLogin = request.getParameter("action");
		UserDAO userDAO = new UserDAO();
		Boolean flag = false;
		if ("login".equals(SigninorLogin)) {
			try {
				flag = userDAO.login(id, password);
			} catch (Exception e) {
				System.out.println("login");
				e.printStackTrace();
			}
			if (flag) {
				try {
					Boolean isLogin = true;
					User user = new UserDAO().getUserData(id);
					session.setAttribute("user", user);
					session.setAttribute("login", isLogin);
					response.sendRedirect("user-menu");
				} catch (Exception e) {
					System.out.println("user");
					e.printStackTrace();
					response.sendRedirect("login/login-error.jsp");
				}
			} else {
				response.sendRedirect("login/login-error.jsp");
			}
		} else {
			request.setAttribute("id", id);
		      request.setAttribute("password", password);
		      RequestDispatcher dispatcher = request.getRequestDispatcher("member/join.jsp");
		      dispatcher.forward(request, response);
		}
	}
}
