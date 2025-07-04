package jp.co.aforce.servlet.buy;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.CartBean;
import jp.co.aforce.dao.CartDAO;
import jp.co.aforce.servlet.cart.CartUtils;
@WebServlet("/views/buy")
public class Buy extends HttpServlet{
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession();
	        Long userId = (Long) session.getAttribute("user_id");
	        String guestId = CartUtils.getOrCreateGuestId(request, response);

	        CartDAO cartDAO = new CartDAO();
	        List<CartBean> cartItems = null;

	        try {
	            cartItems = cartDAO.getCartItems(userId, guestId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "カート情報の取得に失敗しました。");
	        }

	        request.setAttribute("cartItems", cartItems);
	        session.setAttribute("cartItems", cartItems);
	        request.getRequestDispatcher("/WEB-INF/views/buy/index.jsp").forward(request, response);
	    }

}
