package jp.co.aforce.servlet.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import jp.co.aforce.bean.ProductBean;
import jp.co.aforce.dao.ProductsDAO;

@WebServlet("/views/admin/products")
@MultipartConfig // ← これを追加
public class AdminProduct extends HttpServlet {
    private final ProductsDAO dao = new ProductsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<ProductBean> products = dao.getAllProduct();
            req.setAttribute("products", products);

            String action = req.getParameter("action");
            if ("edit".equals(action)) {
                String idStr = req.getParameter("id");
                if (idStr != null && !idStr.isEmpty()) {
                    long id = Long.parseLong(idStr);
                    ProductBean edit_product = dao.getProductById(id);
                    req.setAttribute("edit_product", edit_product);
                }
            }

            req.getRequestDispatcher("/WEB-INF/views/admin/products/index.jsp")
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

            switch (action) {
                case "create": {
                    ProductBean p = new ProductBean();
                    p.setName(req.getParameter("name"));
                    p.setPrice(Integer.parseInt(req.getParameter("price")));
                    p.setDescription(req.getParameter("description"));
                    p.setStock_quantity(Integer.parseInt(req.getParameter("quantity")));

                    long productId = dao.createProductAndReturnId(p); // IDを取得するようなDAOが必要

                    // ファイル処理
                    Part filePart = req.getPart("image"); // name="image"
                    if (filePart != null && filePart.getSize() > 0) {
                        saveImage(filePart, productId, req);
                    }

                    break;
                }

                case "update": {
                    long id = Long.parseLong(req.getParameter("id"));
                    ProductBean p = new ProductBean();
                    p.setProduct_id(id);
                    p.setName(req.getParameter("name"));
                    p.setPrice(Integer.parseInt(req.getParameter("price")));
                    p.setDescription(req.getParameter("description"));
                    p.setStock_quantity(Integer.parseInt(req.getParameter("quantity")));
                    dao.updateProduct(p);

                    Part filePart = req.getPart("image");
                    if (filePart != null && filePart.getSize() > 0) {
                        saveImage(filePart, id, req);
                    }
                    break;
                }

                case "delete": {
                    long id = Long.parseLong(req.getParameter("id"));
                    dao.deleteProductById(id);
                    break;
                }
            }

            resp.sendRedirect(req.getContextPath() + "/views/admin/products");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void saveImage(Part filePart, long productId, HttpServletRequest req) throws IOException {
        String uploadPath = req.getServletContext().getRealPath("/img");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        File file = new File(uploadDir, productId + ".jpg");

        try (InputStream input = filePart.getInputStream();
             OutputStream output = new FileOutputStream(file)) {
            input.transferTo(output);
        }
    }
}
