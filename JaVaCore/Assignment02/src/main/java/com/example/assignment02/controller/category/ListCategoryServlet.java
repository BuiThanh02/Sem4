package com.example.assignment02.controller.category;

import com.example.assignment02.entity.Category;
import com.example.assignment02.model.CategoryModel;
import com.example.assignment02.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public ListCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> list = categoryModel.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/product/list.jsp").forward(req, resp);
    }
}
