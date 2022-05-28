package com.example.assignment02.controller.category;

import com.example.assignment02.entity.Category;
import com.example.assignment02.model.CategoryModel;
import com.example.assignment02.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public DetailCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryModel.findById(id);

        if (category == null){
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else {
            req.setAttribute("category", category);
            req.getRequestDispatcher("/admin/category/detail.jsp").forward(req, resp);
        }
    }
}
