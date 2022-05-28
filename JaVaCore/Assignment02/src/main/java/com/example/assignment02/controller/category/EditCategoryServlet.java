package com.example.assignment02.controller.category;

import com.example.assignment02.entity.Category;
import com.example.assignment02.model.CategoryModel;
import com.example.assignment02.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public EditCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryModel.findById(id);
        if (category == null){
            req.getRequestDispatcher("/admin/errors/404.jsp");
        }else {
            req.setAttribute("category", category);
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        Category exCategory = categoryModel.findById(id);

        if (exCategory == null){
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else {
            String name = req.getParameter("name");
            int status = Integer.parseInt(req.getParameter("status"));

            Category category = new Category(name, status);

            if (categoryModel.update(id, category) != null){
                resp.sendRedirect("/admin/category/list");
            }else {
                req.setAttribute("category", category);
                req.setAttribute("action", 2);
                req.getRequestDispatcher("/admin/category/form.jsp");
            }
        }
    }
}
