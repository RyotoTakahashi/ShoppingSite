package jp.co.aforce.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/views/admin/*", "/views/user/*" })
public class RoleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("role") == null) {
			// 未ログインまたはロールなし
			res.sendRedirect("/views/error/unauthorized");
			return;
		}

		String role = (String) session.getAttribute("role");
		String uri = req.getRequestURI();

		// アクセス制限の判定
		if (uri.contains("/admin/") && !role.equals("admin")) {
			res.sendRedirect("/views/error/forbidden");
			return;
		}

		if (uri.contains("/user/") && !role.equals("user") && !role.equals("admin")) {
			// userページにはadminもアクセスできるようにするなら、ここで許可
			res.sendRedirect("/views/error/unauthorized");
			return;
		}

		// ロールOKなら次の処理へ
		chain.doFilter(request, response);
	}
}