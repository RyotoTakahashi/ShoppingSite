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

@WebFilter("/*") // 全てのリクエストに適用
public class AccessFilter implements Filter {

	@SuppressWarnings("unused")
	private static final String TARGET_DOMAIN = "shoppingsiteryototakahashi.f5.si"; // ← ここはあなたのドメインに書き換え

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
 
		String scheme = req.getScheme(); // "http" or "https"
		String serverName = req.getServerName(); // ドメイン or IP
		String requestURI = req.getRequestURI();
		String queryString = req.getQueryString();
		String fullPath = requestURI + (queryString != null ? "?" + queryString : "");

		// 1. HTTPだったらHTTPSへ
		if ("http".equalsIgnoreCase(scheme)) {
			String redirectUrl = "https://" + serverName + fullPath;
			resp.sendRedirect(redirectUrl);
			return;
		}

//		 2. IPアドレスだったらドメインへ（HTTPS）
//		if (serverName.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
//			String redirectUrl = "https://" + TARGET_DOMAIN + fullPath;
//			resp.sendRedirect(redirectUrl);
//			return;
//		}

		// 通常のリクエストはそのまま通す
		chain.doFilter(servletRequest, servletResponse);
	}

}
