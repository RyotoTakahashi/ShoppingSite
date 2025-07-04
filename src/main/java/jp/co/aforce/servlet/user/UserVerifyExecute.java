package jp.co.aforce.servlet.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.AdminTokenBean;
import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.AdminTokensDAO;

/**
 * Servlet implementation class UserVerifyFormExecute
 */@WebServlet("/views/user/verify/execute")
 public class UserVerifyExecute extends HttpServlet {

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			try {
				String token = request.getParameter("token");
				
				UserBean users = (UserBean) session.getAttribute("userdata");
				long ID = users.getUser_id();
				var AdminTokens = new AdminTokensDAO();
				List<AdminTokenBean> adminTokens = AdminTokens.getAdminTokensByAdminId(ID);

				long nowMillis = System.currentTimeMillis();

				for (AdminTokenBean tokenBean : adminTokens) {
					System.out.println(tokenBean.getUser_id());
					if (tokenBean != null && tokenBean.getToken().equals(token)) {
						if (tokenBean.getExpires_at().getTime() > nowMillis) {
							AdminTokens.deleteAdminToken(tokenBean.getToken_id());
							session.setAttribute("role", "admin");
							response.sendRedirect(request.getContextPath() + "/views/admin");
							return;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/views/user/tokenerror");
		}
	}
