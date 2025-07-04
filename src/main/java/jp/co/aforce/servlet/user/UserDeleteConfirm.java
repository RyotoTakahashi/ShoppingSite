package jp.co.aforce.servlet.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserVerifyFormExecute
 */

@WebServlet("/views/user/delete/confirm")
public class UserDeleteConfirm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/views/user/delete/confirm/index.jsp").forward(request, response);
    }
}