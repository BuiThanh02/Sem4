package com.example.javacore_customers_demo.controller;

import com.example.javacore_customers_demo.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class recentCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Customer> list = (ArrayList<Customer>) session.getAttribute("recentView");
        if (list == null){
            list = new ArrayList<>();
        }
        req.setAttribute("title", "Recent View");
        req.setAttribute("listCustomer", list);
        req.getRequestDispatcher("/admin/customers/recent.jsp").forward(req, resp);
    }
}
