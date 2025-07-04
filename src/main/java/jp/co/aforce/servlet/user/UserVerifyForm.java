package jp.co.aforce.servlet.user;

import java.io.IOException;
import java.security.SecureRandom;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.api.GmailApiUtil;
import jp.co.aforce.bean.AdminTokenBean;
import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.AdminTokensDAO;

/**
 * Servlet implementation class UserVerifyForm
 */
@WebServlet("/views/user/verify")
public class UserVerifyForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();;
		SecureRandom random = new SecureRandom();
		UserBean users = (UserBean)session.getAttribute("userdata");
		long ID = users.getUser_id();
		String second_verifier="";
		for (int i=0; i<8; i++) {
			String a = Integer.toString(random.nextInt(10));
			second_verifier +=a;
		}
		AdminTokenBean token = new AdminTokenBean();
		token.setUser_id(ID);
		token.setToken(second_verifier);

		String to = users.getEmail();
		String subject = "2段階認証トークン";
		String body = second_verifier;
		try {
			GmailApiUtil.sendEmail(to, subject, body);
			System.out.println("Email sent successfully!");
			AdminTokensDAO tokensDAO= new AdminTokensDAO();
			tokensDAO.createAdminToken(token);
			request.getRequestDispatcher("/WEB-INF/views/user/verify/index.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to send email: " + e.getMessage());
			session.invalidate();
			response.sendRedirect("/ShoppingSite/views/guest/loginerror");
		}
	}
}
