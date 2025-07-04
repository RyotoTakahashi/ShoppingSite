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

@WebServlet("/views/user/logout/execute")
public class UserLogoutExecute extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if (session != null) {
            // セッションを無効化して属性を全てクリア
            session.invalidate();
        }
        // ログイン画面へリダイレクト（LoginServlet が "/login" にマッピングされている前提）
        response.sendRedirect("/ShoppingSite/views/guest/logout/success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // POST リクエストでも同じ処理を行う
        doGet(req, resp);
    }
    
}