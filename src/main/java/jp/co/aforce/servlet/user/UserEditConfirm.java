package jp.co.aforce.servlet.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserVerifyFormExecute
 */

@WebServlet("/views/user/edit/confirm")
public class UserEditConfirm extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームから送られてきた値を受け取って…
        String firstname = request.getParameter("firstname");
        String lastname  = request.getParameter("lastname");
        String phone      = request.getParameter("phone");

        // 確認画面でも使えるようにセッションに一時保存
        HttpSession session = request.getSession();
        session.setAttribute("firstname", firstname);
        session.setAttribute("lastname",  lastname);
        session.setAttribute("phone",      phone);

        // JSPに属性として渡して表示
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname",  lastname);
        request.setAttribute("phone",      phone);
        request.getRequestDispatcher("/WEB-INF/views/user/edit/confirm/index.jsp")
               .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GETで来たら編集フォームにリダイレクト
        response.sendRedirect(request.getContextPath() + "/views/user/edit/form");
    }
}