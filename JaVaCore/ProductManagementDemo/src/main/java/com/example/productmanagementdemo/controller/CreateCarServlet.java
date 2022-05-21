package com.example.productmanagementdemo.controller;

import com.example.productmanagementdemo.entity.Car;
import com.example.productmanagementdemo.model.CarModel;
import com.example.productmanagementdemo.model.MySQLCarModel;
import com.example.productmanagementdemo.util.DateTimeHelper;
import com.example.productmanagementdemo.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class CreateCarServlet extends HttpServlet{
    private CarModel carModel;

    public CreateCarServlet() {
        this.carModel = new MySQLCarModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // trả về form.
        req.setAttribute("car", new Car());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/cars/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String Id = req.getParameter("Id");
        String Name = req.getParameter("Name");
        String Branch = req.getParameter("Branch");
        String Version = req.getParameter("Version");
        String strPrice = req.getParameter("Price");
        Double Price = Double.parseDouble(strPrice);
        String Image = req.getParameter("Image");
        Car car = new Car(Id, Name, Branch, Version, Price, Image);
        HashMap<String, String> errors = new HashMap<>();
        // validate dữ liệu theo kiểu cùi bắp.
        if (Id == null || Id.length() == 0) {
            errors.put("Id", "Please enter Id");
        }
        if (Name == null || Name.length() == 0) {
            errors.put("Name", "Please enter Name");
        }
        if (Branch == null || Branch.length() == 0) {
            errors.put("Branch", "Please enter Branch");
        }
        if (Version == null || Version.length() == 0) {
            errors.put("Version", "Please enter Version");
        }
        if (strPrice == null || strPrice.length() == 0) {
            errors.put("Price", "Please enter Price");
        }
        if (Image == null || Image.length() == 0) {
            errors.put("Image", "Please enter Image");
        }
        if (errors.size() > 0) {
            req.setAttribute("car", car);
            req.setAttribute("action", 1);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/admin/cars/form.jsp").forward(req, resp);
            return;
        }
        if (carModel.save(car) != null) {
            resp.sendRedirect("/admin");
        } else {
            req.getRequestDispatcher("/admin/cars/form.jsp").forward(req, resp);
        }
    }
}
