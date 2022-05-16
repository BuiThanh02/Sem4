package com.example.javacore_customers_demo.controller;

import com.example.javacore_customers_demo.entity.Customer;
import com.example.javacore_customers_demo.model.CustomerModel;
import com.example.javacore_customers_demo.model.MySqlCustomerModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCustomerServlet extends HttpServlet {
    private CustomerModel customerModel;
    public ListCustomerServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> list = customerModel.findAll();
        req.setAttribute("listCustomer", list);
        req.getRequestDispatcher("/admin/customers/list.jsp").forward(req, resp);
    }

}
