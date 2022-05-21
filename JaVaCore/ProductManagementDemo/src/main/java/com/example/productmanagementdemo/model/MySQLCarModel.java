package com.example.productmanagementdemo.model;

import com.example.productmanagementdemo.entity.Car;
import com.example.productmanagementdemo.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySQLCarModel implements CarModel{
    @Override
    public Car save(Car car) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into cars " +
                    "(Id, Name, Branch, Version, Price, Image, CreatedAt, UpdatedAt, Status)" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, car.getId());
            preparedStatement.setString(2, car.getName());
            preparedStatement.setString(3, car.getBranch());
            preparedStatement.setString(4, car.getVersion());
            preparedStatement.setDouble(5, car.getPrice());
            preparedStatement.setString(6, car.getImage());
            preparedStatement.setString(7, car.getCreatedAt().toString());
            preparedStatement.setString(8, car.getUpdatedAt().toString());
            preparedStatement.setInt(9, car.getStatus());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        try{
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from cars where Status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String Id = resultSet.getString("Id");
                String Name = resultSet.getString("Name");
                String Branch = resultSet.getString("Branch");
                String Version = resultSet.getString("Version");
                double Price = resultSet.getDouble("Price");
                String Image = resultSet.getString("Image");
                Car car = new Car(Id, Name, Branch, Version, Price, Image);
                list.add(car);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Car findByID(String id) {
        Car car = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from cars where Status = ? and Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String Name = resultSet.getString("Name");
                String Branch = resultSet.getString("Branch");
                String Version = resultSet.getString("Version");
                double Price = resultSet.getDouble("Price");
                String Image = resultSet.getString("Image");
                car = new Car(id, Name, Branch, Version, Price, Image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public Car update(String id, Car car) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update cars" +
                    "set Id = ?, Name = ?, Branch = ?, Version = ?, Price = ?, Image = ?, CreatedAt = ?, UpdatedAt = ?, Status = ? where Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, car.getId());
            preparedStatement.setString(2, car.getName());
            preparedStatement.setString(3, car.getBranch());
            preparedStatement.setString(4, car.getVersion());
            preparedStatement.setDouble(5, car.getPrice());
            preparedStatement.setString(6, car.getImage());
            preparedStatement.setString(7, car.getUpdatedAt().toString());
            preparedStatement.setString(8, car.getUpdatedAt().toString());
            preparedStatement.setInt(9, car.getStatus());
            preparedStatement.setString(10, id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update cars set Status = ? where Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, id);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
