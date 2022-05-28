package com.example.wcdexam.controller;


import com.example.wcdexam.entity.Phone;
import com.example.wcdexam.model.MySQLPhoneModel;
import com.example.wcdexam.model.PhoneModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailPhoneServlet extends HttpServlet {
    private PhoneModel phoneModel;
    public DetailPhoneServlet(){
        this.phoneModel = new MySQLPhoneModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Phone phone = phoneModel.findById(id);
        if (phone==null){
            req.setAttribute("message","Product not found!");
            req.getRequestDispatcher("/errors/404.jsp");
        }else {
            req.setAttribute("product",phone);
            req.setAttribute("title","Detail Phone");
            req.getRequestDispatcher("/admin/products/detail.jsp").forward(req,resp);
        }
    }
}
