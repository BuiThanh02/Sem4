package com.example.asignment.controller.product;

import com.example.asignment.entity.Product;
import com.example.asignment.model.MySqlProductModel;
import com.example.asignment.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    private ProductModel productModel;

    public DeleteProductServlet() {
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Product product = productModel.findById(id);

        if (product == null){
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else {
            boolean result = productModel.delete(id);
            if (result){
                resp.sendRedirect("/admin/product/list");
            }else {
                req.getRequestDispatcher("/admin/errors/500.jsp").forward(req, resp);
            }
        }
    }
}
