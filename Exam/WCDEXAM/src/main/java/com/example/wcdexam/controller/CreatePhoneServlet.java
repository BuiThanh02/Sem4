package com.example.wcdexam.controller;

import com.example.wcdexam.entity.Phone;
import com.example.wcdexam.entity.myenum.PhoneStatus;
import com.example.wcdexam.model.BrandModel;
import com.example.wcdexam.model.MySQLBrandModel;
import com.example.wcdexam.model.MySQLPhoneModel;
import com.example.wcdexam.model.PhoneModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePhoneServlet extends HttpServlet {
    private PhoneModel phoneModel;
    private BrandModel brandModel;
    public CreatePhoneServlet(){
        this.phoneModel = new MySQLPhoneModel();
        this.brandModel= new MySQLBrandModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("brands",brandModel.findAll());
        req.setAttribute("phone",new Phone());
        req.setAttribute("action",1);
        req.getRequestDispatcher("/admin/products/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        int brandId = Integer.parseInt(req.getParameter("brandId"));
        Double price = Double.valueOf(req.getParameter("price"));
        String description = req.getParameter("description");
        int status = Integer.parseInt(req.getParameter("status"));

        Phone phone = new Phone();
        phone.setName(name);
        phone.setBrandId(brandId);
        phone.setStatus(PhoneStatus.of(status));
        phone.setPrice(price);
        phone.setDescription(description);
        if (!phone.isValid()){
            req.setAttribute("phone", phone);
            req.setAttribute("brands",brandModel.findAll());
            req.setAttribute("action",1);
            req.setAttribute("errors",phone.getErrors());
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req,res);
        }
        if (phoneModel.save(phone)!=null){
            res.sendRedirect("/admin/products/list");
        }else {
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req,res);
        }
    }

} 