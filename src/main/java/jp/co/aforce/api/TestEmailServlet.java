package jp.co.aforce.api;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/testEmail")
public class TestEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

		String to = "r.takahashi.aforce@gmail.com";
		String subject = "test";
		String body = "testmessage";

		try {
			GmailApiUtil.sendEmail(to, subject, body);
			System.out.println("Email sent successfully!");
			resp.getWriter().println("Email sent successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("❌ Failed to send email: " + e.getMessage());
        }
    }
}