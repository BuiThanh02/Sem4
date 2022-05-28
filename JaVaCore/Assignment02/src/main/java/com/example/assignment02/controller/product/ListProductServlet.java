package com.example.assignment02.controller.product;

import com.example.assignment02.entity.Product;
import com.example.assignment02.model.MySqlProductModel;
import com.example.assignment02.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListProductServlet extends HttpServlet {
    private ProductModel productModel;

    public ListProductServlet() {
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productModel.findAll();
        req.setAttribute("title", "List Product");
        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/product/list.jsp").forward(req, resp);
    }
}
