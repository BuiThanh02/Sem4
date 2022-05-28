package com.example.assignment02.controller.category;

import com.example.assignment02.entity.Category;
import com.example.assignment02.model.CategoryModel;
import com.example.assignment02.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public DeleteCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryModel.findById(id);
        if (category == null){
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else {
            boolean result = categoryModel.delete(id);
            if (result){
                resp.sendRedirect("/admin/category/list");
            }else {
                req.getRequestDispatcher("/admin/errors/505.jsp").forward(req, resp);
            }
        }
    }
}
