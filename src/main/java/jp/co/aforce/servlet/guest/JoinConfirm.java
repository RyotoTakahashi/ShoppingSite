package jp.co.aforce.servlet.guest;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.UserBean;

/**
 * Servlet implementation class GuestLoginExecute
 */
@WebServlet("/views/guest/join/confirm")
public class JoinConfirm extends HttpServlet {

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");
	        int age = Integer.parseInt(req.getParameter("age"));
	        boolean isMale = req.getParameter("gender").equals("male");
	        String lastName = req.getParameter("lastname");
	        String firstName = req.getParameter("firstname");
	        String eMail = req.getParameter("email");
	        String phone = req.getParameter("phone");
	        UserBean user = new UserBean();
	        
	        user.setUsername(username);
	        user.setAge(age);
	        user.setIsMale(isMale);
	        user.setLast_name(lastName);
	        user.setFirst_name(firstName);
	        user.setEmail(eMail);
	        user.setPhone(phone);
	        req.setAttribute("user", user);

	        // 必須チェック
	        if (username == null || username.isBlank() ||
	            password == null || password.isBlank()) {
	            resp.sendRedirect(req.getContextPath() + "/views/guest/join");
	            return;
	        }
	        HttpSession session = req.getSession();
	        session.setAttribute("user", user);
	        session.setAttribute("username", username);
	        session.setAttribute("password", password);
	        
	        req.getRequestDispatcher("/WEB-INF/views/guest/join/confirm/index.jsp")
	           .forward(req, resp);
}}
