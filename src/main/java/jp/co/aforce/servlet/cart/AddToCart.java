package jp.co.aforce.servlet.cart;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.CartBean;
import jp.co.aforce.dao.CartDAO;

@WebServlet("/cart/add")
public class AddToCart extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        String guestId = CartUtils.getOrCreateGuestId(request, response);

        long productId = Long.parseLong(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartDAO cartDAO = new CartDAO();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            CartBean existingItem = cartDAO.getCartItem(userId, guestId, productId);
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                cartDAO.updateCartItem(existingItem);
            } else {
                CartBean newItem = new CartBean();
                newItem.setUserId(userId);
                newItem.setGuestId(guestId);
                newItem.setProductId(productId);
                newItem.setQuantity(quantity);
                cartDAO.insertCartItem(newItem);
            }

            response.sendRedirect(request.getContextPath() + "/views/home");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect(request.getContextPath() + "/views/home");
        }
    }
}
