package com.example.javacore_customers_demo.controller;

import com.example.javacore_customers_demo.entity.Customer;
import com.example.javacore_customers_demo.model.CustomerModel;
import com.example.javacore_customers_demo.model.MySqlCustomerModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class DetailCustomerServlet extends HttpServlet {
    private CustomerModel customerModel;
    public DetailCustomerServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String id = req.getParameter("id");
        // kiểm tra trong database xem có tồn tại không.
        Customer customer = customerModel.findByID(id);

        HttpSession session = req.getSession();
        // nếu không trả về trang 404
        if (customer == null) {

            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            ArrayList<Customer> recentView = (ArrayList<Customer>) session.getAttribute("recentView");
            if (recentView == null){
                recentView = new ArrayList<Customer>();
            }
            boolean exist = false;
            for (int i = 0; i < recentView.size(); i++){
                if (recentView.get(i).getID().equals(customer.getID())){
                    exist = true;
                }
            }
            if (!exist){
                recentView.add(customer);
                session.setAttribute("recentView", recentView);
            }
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/admin/customers/detail.jsp").forward(req, resp);
        }

    }

}
