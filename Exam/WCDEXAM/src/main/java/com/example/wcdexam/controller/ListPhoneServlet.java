package com.example.wcdexam.controller;


import com.example.wcdexam.entity.Brand;
import com.example.wcdexam.entity.Phone;
import com.example.wcdexam.model.BrandModel;
import com.example.wcdexam.model.MySQLBrandModel;
import com.example.wcdexam.model.MySQLPhoneModel;
import com.example.wcdexam.model.PhoneModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListPhoneServlet extends HttpServlet {
    private PhoneModel phoneModel;
    private BrandModel brandModel;
    public ListPhoneServlet() {
        this.phoneModel = new MySQLPhoneModel();
        this.brandModel = new MySQLBrandModel();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Phone> list = phoneModel.findAll();
        List<Brand> listB = brandModel.findAll();
        request.setAttribute("title","List Phone");
        request.setAttribute("list",list);
        request.setAttribute("listB",listB);
        request.getRequestDispatcher("/admin/products/list.jsp").forward(request,response);
    }
}
