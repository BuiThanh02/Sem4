package com.example.wcdexam.controller;


import com.example.wcdexam.entity.Phone;
import com.example.wcdexam.model.MySQLPhoneModel;
import com.example.wcdexam.model.PhoneModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePhoneServlet extends HttpServlet {
    private PhoneModel phoneModel;
    public DeletePhoneServlet(){
        this.phoneModel = new MySQLPhoneModel();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Phone phone = phoneModel.findById(id);
        if (phone==null){
            request.setAttribute("message","Phone not found!");
            request.getRequestDispatcher("errors/404.jsp").forward(request,response);
        }else{
            boolean result = phoneModel.delete(id);
            if (result){
                response.sendRedirect("/admin/products/list");
            }else {
                request.setAttribute("message","Action fails");
                request.getRequestDispatcher("errors/500.jsp").forward(request,response);
            }
        }
    }
}
