package com.example.javacore_customers_demo.controller;

import com.example.javacore_customers_demo.entity.Customer;
import com.example.javacore_customers_demo.model.CustomerModel;
import com.example.javacore_customers_demo.model.MySqlCustomerModel;
import com.example.javacore_customers_demo.util.DateTimeHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditCustomerServlet extends HttpServlet {
    private CustomerModel customerModel;
    public EditCustomerServlet() {
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
            // nếu có trả về trang detail
            req.setAttribute("customer", customer);
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/customers/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String id = req.getParameter("id");
        Customer existingCustomer = customerModel.findByID(id);
        if(existingCustomer == null){

            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else{
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String image = req.getParameter("image");
            String stringBirthday = req.getParameter("birthday");

            LocalDateTime birthday = DateTimeHelper.convertStringToLocalDateTime(stringBirthday);
            Customer customer = new Customer(id, name, phone, image, birthday);
            // validate dữ liệu
            if (customerModel.update(id, customer) != null) {
                resp.sendRedirect("/admin/customers/list");
            } else {
                // nếu có trả về trang form
                req.setAttribute("customer", customer);
                req.setAttribute("action", 2);
                req.getRequestDispatcher("/admin/customers/form.jsp").forward(req, resp);
            }
        }
    }

}
