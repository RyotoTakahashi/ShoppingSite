package jp.co.aforce.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.User;

/**
 * Servlet implementation class fixCheck
 */
@WebServlet("/views/user-menu/fix/fixCheck")
public class fixCheck extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String firstname, lastname, address, mail;

		User user = (User) session.getAttribute("user");

		firstname = (request.getParameter("firstname") != null)
				? (String) request.getParameter("firstname")
				: user.getFirstName();

		lastname = (request.getParameter("lastname") != null)
				? (String) request.getParameter("lastname")
				: user.getLastName();

		address = (request.getParameter("address") != null)
				? (String) request.getParameter("address")
				: user.getAddress();

		mail = (request.getParameter("mail") != null)
				? (String) request.getParameter("mail")
				: user.getMailAddress();

		request.setAttribute("firstname", firstname);
		request.setAttribute("lastname", lastname);
		request.setAttribute("address", address);
		request.setAttribute("mail", mail);
		request.getRequestDispatcher("check.jsp").forward(request, response);
	}

}
