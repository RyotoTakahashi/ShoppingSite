package jp.co.aforce.servlet.cart;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.CartDAO;

@WebServlet("/cart/update")
public class UpdateToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long cartItemId = Long.parseLong(request.getParameter("cart_item_id"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CartDAO cartDAO = new CartDAO();

        try {
            if (newQuantity <= 0) {
                cartDAO.deleteCartItem(cartItemId);
            } else {
                cartDAO.updateQuantity(cartItemId, newQuantity);
            }
            response.sendRedirect(request.getContextPath() + "/views/home");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect(request.getContextPath() + "/views/home");
        }
    }
}