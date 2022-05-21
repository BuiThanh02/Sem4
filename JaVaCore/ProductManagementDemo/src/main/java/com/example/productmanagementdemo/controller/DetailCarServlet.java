package com.example.productmanagementdemo.controller;

import com.example.productmanagementdemo.entity.Car;
import com.example.productmanagementdemo.model.CarModel;
import com.example.productmanagementdemo.model.MySQLCarModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailCarServlet extends HttpServlet {
    private CarModel carModel;

    public DetailCarServlet() {
        this.carModel = new MySQLCarModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String Id = req.getParameter("Id");
        // kiểm tra trong database xem có tồn tại không.
        Car car = carModel.findByID(Id);
        // nếu không trả về trang 404
        if (car == null) {
            req.setAttribute("message", "Car not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            req.setAttribute("car", car);
            req.getRequestDispatcher("/admin/cars/detail.jsp").forward(req, resp);
        }

    }

}
