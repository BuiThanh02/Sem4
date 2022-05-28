package com.example.asignment.controller.product;

import com.example.asignment.entity.Product;
import com.example.asignment.entity.myenum.ProductStatus;
import com.example.asignment.model.CategoryModel;
import com.example.asignment.model.MySqlCategoryModel;
import com.example.asignment.model.MySqlProductModel;
import com.example.asignment.model.ProductModel;
import com.example.asignment.util.DateTimeHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateProductServlet extends HttpServlet {
    private ProductModel productModel;
    private CategoryModel categoryModel;

    public CreateProductServlet() {
        this.productModel = new MySqlProductModel();
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // trả về form.
        req.setAttribute("product", new Product());
        req.setAttribute("categories", categoryModel.findAll());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String name = req.getParameter("name");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int status = Integer.parseInt(req.getParameter("status"));
        String description = req.getParameter("description");
        String detail = req.getParameter("detail");
        String thumbnail = req.getParameter("thumbnail");
        double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product();
        product.setName(name);
        product.setCategoryId(categoryId);
        product.setStatus(ProductStatus.of(status));
        product.setThumbnail(thumbnail);
        product.setDescription(description);
        product.setDetail(detail);
        product.setPrice(price);

        if (!product.isValid()) {
            req.setAttribute("category", categoryModel.findAll());
            req.setAttribute("product", product);
            req.setAttribute("action", 1);
            req.setAttribute("errors", product.getErrors());
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
            return;
        }
        if (productModel.save(product) != null) {
            resp.sendRedirect("/admin/product/list");
        } else {
            req.setAttribute("category", categoryModel.findAll());
            req.setAttribute("product", product);
            req.setAttribute("action", 1);
            req.setAttribute("errors", product.getErrors());
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
        }
    }
}
