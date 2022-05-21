package com.example.productmanagementdemo.controller;

import com.example.productmanagementdemo.entity.Car;
import com.example.productmanagementdemo.model.CarModel;
import com.example.productmanagementdemo.model.MySQLCarModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditCarServlet extends HttpServlet {

    private CarModel carModel;
    public EditCarServlet() {
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
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/cars/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String Id = req.getParameter("Id");
        Car existingCar = carModel.findByID(Id);
        if(existingCar == null){
            req.setAttribute("message", "Car not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else{
            String Name = req.getParameter("Name");
            String Branch = req.getParameter("Branch");
            String Version = req.getParameter("Version");
            String strPrice = req.getParameter("Price");
            Double Price = Double.parseDouble(strPrice);
            String Image = req.getParameter("Image");
            LocalDateTime CreatedAt = existingCar.getCreatedAt();
            Car car = new Car(Id, Name, Branch, Version, Price, Image, CreatedAt);
            if (carModel.update(Id, car) != null) {
                resp.sendRedirect("/admin");
            } else {
                // nếu có trả về trang form
                req.setAttribute("car", car);
                req.setAttribute("action", 2);
                req.getRequestDispatcher("/admin/students/form.jsp").forward(req, resp);
            }
        }
    }

}
