package com.example.wcdexam.model;

import com.example.wcdexam.entity.Brand;
import com.example.wcdexam.entity.myenum.BrandStatus;
import com.example.wcdexam.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySQLBrandModel implements BrandModel{

    @Override
    public Brand save(Brand brand) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into brands " +
                    "(name ,createdAt ,updatedAt ,status) "+
                    "values " +
                    "(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,brand.getName());
            preparedStatement.setString(2,brand.getCreatedAt().toString());
            preparedStatement.setString(3,brand.getUpdatedAt().toString());
            preparedStatement.setInt(4,brand.getStatus().getValue());
            System.out.println("Action success");
            preparedStatement.execute();
            return  brand;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from brands where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, BrandStatus.ACTIVE.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Brand brand = new Brand(id, name);
                brand.setCreatedAt(createdAt);
                brand.setCreatedAt(updatedAt);
                brand.setStatus(BrandStatus.of(intStatus));
                list.add(brand);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Brand findById(int id) {
        Brand brand= null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from categories where status = ? and id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, BrandStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                brand = new Brand(id, name);
                brand.setCreatedAt(createdAt);
                brand.setCreatedAt(updatedAt);
                brand.setStatus(BrandStatus.of(intStatus));}
        }catch (SQLException e){
            e.printStackTrace();
        }
        return brand;
    }

    @Override
    public Brand update(int id, Brand updateBrand) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
