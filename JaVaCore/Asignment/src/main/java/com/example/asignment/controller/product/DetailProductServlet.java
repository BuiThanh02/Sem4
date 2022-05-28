package com.example.asignment.controller.product;

import com.example.asignment.entity.Category;
import com.example.asignment.entity.Product;
import com.example.asignment.model.CategoryModel;
import com.example.asignment.model.MySqlCategoryModel;
import com.example.asignment.model.MySqlProductModel;
import com.example.asignment.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class DetailProductServlet extends HttpServlet {
    private ProductModel productModel;
    private CategoryModel categoryModel;

    public DetailProductServlet() {
        this.productModel = new MySqlProductModel();
        this.categoryModel = new MySqlCategoryModel();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        int id = Integer.parseInt(req.getParameter("id"));
        // kiểm tra trong database xem có tồn tại không.
        Product product = productModel.findById(id);
        int cateId = product.getCategoryId();
        Category category = categoryModel.findById(cateId);
        // nếu không trả về trang 404
        if (product == null) {
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            req.setAttribute("product", product);
            req.setAttribute("category", category);
            req.getRequestDispatcher("/admin/product/detail.jsp").forward(req, resp);
        }

    }
}
