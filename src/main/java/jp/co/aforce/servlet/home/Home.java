package jp.co.aforce.servlet.home;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.ProductBean;
import jp.co.aforce.dao.ProductsDAO;

@WebServlet("/views/home")
public class Home extends HttpServlet {
    private final ProductsDAO dao = new ProductsDAO();
    private static final int PAGE_SIZE = 12;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String keyword = req.getParameter("keyword");
            String sort = req.getParameter("sort");
            String pageParam = req.getParameter("page");

            if (keyword == null) keyword = "";
            if (sort == null) sort = "new";
            int page = pageParam != null ? Integer.parseInt(pageParam) : 1;
            int offset = (page - 1) * PAGE_SIZE;

            List<ProductBean> products = dao.searchProducts(keyword, sort, PAGE_SIZE, offset);
            int total = dao.countProducts(keyword);
            boolean hasPrev = page > 1;
            boolean hasNext = offset + PAGE_SIZE < total;

            req.setAttribute("products", products);
            req.setAttribute("keyword", keyword);
            req.setAttribute("sort", sort);
            req.setAttribute("page", page);
            req.setAttribute("hasPrev", hasPrev);
            req.setAttribute("hasNext", hasNext);

            req.getRequestDispatcher("/WEB-INF/views/home/index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}