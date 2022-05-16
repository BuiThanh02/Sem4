package com.example.javacore_customers_demo.controller;

import com.example.javacore_customers_demo.entity.Customer;
import com.example.javacore_customers_demo.model.CustomerModel;
import com.example.javacore_customers_demo.model.MySqlCustomerModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCustomerServlet extends HttpServlet {
    private CustomerModel customerModel;

    public DeleteCustomerServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String id = req.getParameter("id");
        // kiểm tra trong database xem có tồn tại không.
        Customer customer = customerModel.findByID(id);
        // nếu không trả về trang 404
        if (customer == null) {

            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            boolean result = customerModel.delete(id); // xoá mềm.
            if (result) {
                resp.sendRedirect("/admin/customers/list");
            } else {

                req.getRequestDispatcher("/admin/errors/500.jsp").forward(req, resp);
            }
        }
    }

}
