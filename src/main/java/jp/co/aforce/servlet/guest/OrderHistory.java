package jp.co.aforce.servlet.guest;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.OrderBean;
import jp.co.aforce.dao.OrdersDAO;

public class OrderHistory extends HttpServlet{
	@WebServlet("/views/ordercheck")
	public class OrderConfirmServlet extends HttpServlet {
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	        String token = request.getParameter("token");
	        if (token == null || token.isEmpty()) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "トークンがありません");
	            return;
	        }

	        OrdersDAO orderDAO = new OrdersDAO();
	        try {
	            OrderBean order = orderDAO.getOrderByToken(token);
	            if (order == null) {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND, "注文が見つかりません");
	                return;
	            }

	            request.setAttribute("order", order);
	            request.getRequestDispatcher("/WEB-INF/views/ordercheck/complete/index.jsp").forward(request, response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }
	}
}
