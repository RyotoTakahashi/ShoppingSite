package jp.co.aforce.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Join
 */
@WebServlet("/views/member/check")
public class Check extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		request.setAttribute("id",request.getParameter("id"));
		request.setAttribute("password", password);
		request.setAttribute("firstname",request.getParameter("firstname"));
		request.setAttribute("lastname",request.getParameter("lastname"));
		request.setAttribute("address",request.getParameter("address"));
		request.setAttribute("mail",request.getParameter("mail"));
		request.getRequestDispatcher("/views/member/checker.jsp").forward(request, response);
		
	}

}