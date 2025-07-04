package jp.co.aforce.servlet.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.UsersDAO;

@WebServlet("/views/admin/users")
public class AdminUser extends HttpServlet {
    private final UsersDAO dao = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<UserBean> users = dao.getAllUsers();
            req.setAttribute("users", users);

            String action = req.getParameter("action");
            if ("edit".equals(action)) {
                long id = Long.parseLong(req.getParameter("id"));
                UserBean edit_user = dao.getUserById(id);
                req.setAttribute("edit_user", edit_user);
            }

            req.getRequestDispatcher("/WEB-INF/views/admin/users/index.jsp")
               .forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            String action = req.getParameter("action");

            if ("delete".equals(action)) {
                long id = Long.parseLong(req.getParameter("id"));
                dao.deleteUser(id);

            } else if ("update".equals(action)) {
                long id = Long.parseLong(req.getParameter("id"));
                UserBean user = dao.getUserById(id);  // 元データ取得

                String adminStr = req.getParameter("admin");
                user.setIsAdmin("admin".equals(adminStr));

                dao.updateUser(user);
            }

            resp.sendRedirect(req.getContextPath() + "/views/admin/users");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
