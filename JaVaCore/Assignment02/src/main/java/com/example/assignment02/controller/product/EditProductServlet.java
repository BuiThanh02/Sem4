package com.example.assignment02.controller.product;

import com.example.assignment02.entity.Product;
import com.example.assignment02.entity.myenum.ProductStatus;
import com.example.assignment02.model.CategoryModel;
import com.example.assignment02.model.MySqlCategoryModel;
import com.example.assignment02.model.MySqlProductModel;
import com.example.assignment02.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {
    private ProductModel productModel;
    private CategoryModel categoryModel;


    public EditProductServlet() {
        this.productModel = new MySqlProductModel();
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Product product = productModel.findById(id);
        if (product == null){
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else {
            req.setAttribute("product", product);
            req.setAttribute("category", categoryModel.findAll());
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Product existProduct = productModel.findById(id);
        if (existProduct == null){
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else {
            String name = req.getParameter("name");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            int status = Integer.parseInt(req.getParameter("status"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            double price = Double.parseDouble(req.getParameter("price"));
            Product product = new Product();
            product.setName(name);
            product.setCategoryId(categoryId);
            product.setStatus(ProductStatus.of(status));
            product.setThumbnail(thumbnail);
            product.setDescription(description);
            product.setPrice(price);
            if (!product.isValid()) {
                req.setAttribute("categories", categoryModel.findAll());
                req.setAttribute("product", product);
                req.setAttribute("action", 2);
                req.setAttribute("errors", product.getErrors());
                req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
                return;
            }
            if (productModel.update(id, product) != null) {
                resp.sendRedirect("/admin/product/list");
            } else {
                req.setAttribute("product", product);
                req.setAttribute("categories", categoryModel.findAll());
                req.setAttribute("action", 2);
                req.setAttribute("errors", product.getErrors());
                req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
            }
        }
    }
}
