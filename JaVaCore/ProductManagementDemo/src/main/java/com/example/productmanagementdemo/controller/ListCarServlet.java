package com.example.productmanagementdemo.controller;

import com.example.productmanagementdemo.entity.Car;
import com.example.productmanagementdemo.model.CarModel;
import com.example.productmanagementdemo.model.MySQLCarModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCarServlet extends HttpServlet {
    private final CarModel carModel;

    public ListCarServlet() {
        this.carModel = new MySQLCarModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> list = carModel.findAll();
        req.setAttribute("listCar", list);
        req.getRequestDispatcher("/admin/cars/list.jsp").forward(req, resp);
    }
}
