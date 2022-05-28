package com.example.wcdexam.model;


import com.example.wcdexam.entity.Phone;
import com.example.wcdexam.entity.myenum.PhoneStatus;
import com.example.wcdexam.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySQLPhoneModel implements PhoneModel{

    @Override
    public Phone save(Phone phone) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into phones " +
                    "(brandId,name,price,description,createdAt,updatedAt,status) "+
                    "values " +
                    "(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,phone.getBrandId());
            preparedStatement.setString(2, phone.getName());
            preparedStatement.setDouble(3,phone.getPrice());
            preparedStatement.setString(4,phone.getDescription());
            preparedStatement.setString(5,phone.getCreatedAt().toString());
            preparedStatement.setString(6,phone.getUpdatedAt().toString());
            preparedStatement.setInt(7,phone.getStatus().getValue());
            System.out.println("Connection success");
            preparedStatement.execute();
            return  phone;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Phone> findAll() {
        List<Phone> list =  new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from phones where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, PhoneStatus.ACTIVE.getValue());
            System.out.println("Action success!");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int brandId = resultSet.getInt("brandId");
                String name =  resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Phone phone =new Phone();
                phone.setId(id);
                phone.setBrandId(brandId);
                phone.setName(name);
                phone.setPrice(price);
                phone.setDescription(description);
                phone.setCreatedAt(createdAt);
                phone.setUpdatedAt(updatedAt);
                phone.setStatus(PhoneStatus.of(intStatus));
                list.add(phone);
            }
            System.out.println("Action success");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Phone findById(int id) {
        Phone phone = null;

        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from phones where status = ? and id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, PhoneStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                int brandId = resultSet.getInt("brandId");
                String name =  resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                phone =new Phone();
                phone.setId(id);
                phone.setBrandId(brandId);
                phone.setName(name);
                phone.setDescription(description);
                phone.setPrice(price);
                phone.setCreatedAt(createdAt);
                phone.setUpdatedAt(updatedAt);
                phone.setStatus(PhoneStatus.of(intStatus));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phone;
    }

    @Override
    public Phone update(int id, Phone updatePhone) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection =  ConnectionHelper.getConnection();
            String sqlQuery = "update phones " +
                    "set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,PhoneStatus.DELETED.getValue());
            preparedStatement.setInt(2,id);
            System.out.println("Connection success!");
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
