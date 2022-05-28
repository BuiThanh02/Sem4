package com.example.assignment02.controller.category;

import com.example.assignment02.entity.Category;
import com.example.assignment02.model.CategoryModel;
import com.example.assignment02.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public CreateCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("category", new Category());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        int status = Integer.parseInt(req.getParameter("status"));
        Category category = new Category(name, status);
        if (!category.isValid()){
            req.setAttribute("category", category);
            req.setAttribute("action", 1);
            req.setAttribute("errors", category.getErrors());
            req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
            return;
        }
        if (categoryModel.save(category) != null){
            resp.sendRedirect("/admin/category/list");
        }else {
            req.getRequestDispatcher("/admin/category/form.jsp").forward(req,resp);
        }
    }
}
