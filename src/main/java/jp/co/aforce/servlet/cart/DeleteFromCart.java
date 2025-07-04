package jp.co.aforce.servlet.cart;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.CartDAO;

@WebServlet("/cart/delete")
public class DeleteFromCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long cartItemId = Long.parseLong(request.getParameter("cart_item_id"));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CartDAO cartDAO = new CartDAO();

        try {
            cartDAO.deleteCartItem(cartItemId);
            response.sendRedirect(request.getContextPath() + "/views/home");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect(request.getContextPath() + "/views/home");
        }
    }
}