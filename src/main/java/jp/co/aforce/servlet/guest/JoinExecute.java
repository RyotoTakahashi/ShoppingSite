package jp.co.aforce.servlet.guest;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.LoginBean;
import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.LoginDAO;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.others.PasswordHasher;

@WebServlet("/views/guest/join/execute")
public class JoinExecute extends HttpServlet {

	private final LoginDAO loginDAO = new LoginDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null
				|| session.getAttribute("username") == null
				|| session.getAttribute("password") == null
				|| session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/views/guest/join");
			return;
		}

		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		UserBean userData = (UserBean) session.getAttribute("user");

		// セッションからは除去（念のため）
		session.invalidate();
		session = req.getSession();

		try {
			// usernameの重複チェック（LoginDAOでusername検索）
			if (loginDAO.getLoginByUserName(username) != null) {
				session.setAttribute("join_error", "このユーザー名は既に使用されています。");
				resp.sendRedirect(req.getContextPath() + "/views/guest/join");
				return;
			}

			// パスワードハッシュ化
			String[] hashAndSalt = PasswordHasher.createHashedPassword(password);
			LoginBean loginBean = new LoginBean();
			loginBean.setUserName(username);
			loginBean.setVerifier(hashAndSalt[0]);
			loginBean.setSalt(hashAndSalt[1]);

			// ユーザー登録
			UsersDAO usersDAO = new UsersDAO();
			usersDAO.createUser(userData);

			// 登録されたユーザー情報の取得（IDが必要）
			userData = usersDAO.getUserByUsername(username);
			long userId = userData.getUser_id();

			// LoginBean にID設定して保存
			loginBean.setUser_id(userId);
			loginDAO.createLogin(loginBean);

			// セッションにログイン済み情報をセット
			session.setAttribute("userdata", userData);
			session.setAttribute("role", "user");

			// 成功後の遷移
			resp.sendRedirect(req.getContextPath() + "/views/user/menu");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/views/guest/join/error");
		}
	}
}
