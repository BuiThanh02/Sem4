package com.example.assignment02.model;

import com.example.assignment02.entity.Product;
import com.example.assignment02.entity.myenum.CategoryStatus;
import com.example.assignment02.entity.myenum.ProductStatus;
import com.example.assignment02.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductModel implements ProductModel{
    @Override
    public Product save(Product product) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into product " +
                    "(categoryId, name, description, thumbnail, price, createdAt, updatedAt, status) " +
                    "values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getThumbnail());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setString(6, product.getCreatedAt().toString());
            preparedStatement.setString(7, product.getUpdatedAt().toString());
            preparedStatement.setInt(8, product.getStatus().getValue());
            preparedStatement.execute();
            return product;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from product where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Product obj = new Product();
                obj.setId(id);
                obj.setCategoryId(categoryId);
                obj.setName(name);
                obj.setDescription(description);
                obj.setThumbnail(thumbnail);
                obj.setPrice(price);
                obj.setCreatedAt(createdAt);
                obj.setUpdatedAt(updatedAt);
                obj.setStatus(ProductStatus.of(intStatus));
                list.add(obj);
            }
            System.out.println("Action success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findById(int id) {
        Product obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from product where status = ? and id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int categoryId = resultSet.getInt("categoryId");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                obj = new Product();
                obj.setId(id);
                obj.setCategoryId(categoryId);
                obj.setName(name);
                obj.setDescription(description);
                obj.setThumbnail(thumbnail);
                obj.setPrice(price);
                obj.setCreatedAt(createdAt);
                obj.setUpdatedAt(updatedAt);
                obj.setStatus(ProductStatus.of(intStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Product update(int id, Product updateProduct) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update product " +
                    "set categoryId = ?, name = ?, description = ?, thumbnail = ?, price = ?, createdAt = ?, updatedAt = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, updateProduct.getCategoryId());
            preparedStatement.setString(2, updateProduct.getName());
            preparedStatement.setString(3, updateProduct.getDescription());
            preparedStatement.setString(4, updateProduct.getThumbnail());
            preparedStatement.setDouble(5, updateProduct.getPrice());
            preparedStatement.setString(6, updateProduct.getCreatedAt().toString());
            preparedStatement.setString(7, updateProduct.getUpdatedAt().toString());
            preparedStatement.setInt(8, updateProduct.getStatus().getValue());
            preparedStatement.setInt(9, id);
            preparedStatement.execute();
            return updateProduct;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update product " +
                    "set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, ProductStatus.DAXOA.getValue());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            System.out.println("Action success!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
