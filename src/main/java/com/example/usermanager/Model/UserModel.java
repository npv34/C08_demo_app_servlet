package com.example.usermanager.Model;

import com.example.usermanager.Database.Database;
import com.example.usermanager.Entity.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel implements UserDAO{
    protected Connection conn;

    public UserModel() {
        Database database = Database.getInstance();
        this.conn = database.connect();
    }
    @Override
    public List<User> getAll() {
        // viet cau lenh SQL;
        List<User> users = new ArrayList<User>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String password = rs.getString(5);
                String address = rs.getString(6);
                String role = rs.getString(7);

                User user = new User(id, name, email, password, phone, address, role);
                users.add(user);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            String sql = "DELETE FROM  users WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            // gan gia tri cho tham so id
            statement.setInt(1, id);
            // thu hien truy van
            statement.execute();
        }catch (SQLException e) {
            System.out.println("Delete user fail" + e.getMessage());
        }
    }

    @Override
    public void save(User user) throws SQLException {
        try {
            String sql = "INSERT INTO users(name, email, phone, password, address, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            // gan gia tri cho tham so id
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getRole());
            // thu hien truy van
            statement.execute();
        }catch (SQLException e) {
            System.out.println("Add user fail" + e.getMessage());
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try {
            conn.setAutoCommit(false);
            String sql = "UPDATE users SET name = ?, phone = ?, address = ?, role = ? WHERE id = ?  ";
            PreparedStatement statement = conn.prepareStatement(sql);
            // gan gia tri cho tham so id
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getId());
            // thu hien truy van
            statement.execute();
            this.updateUser2();
            conn.commit();
        }catch (SQLException e) {
            conn.rollback();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public User getById(int id) {
        User user = null;
        try {
            String sql = "SELECT * FROM  users WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            // gan gia tri cho tham so id
            statement.setInt(1, id);
            // thu hien truy van
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(6);
                String role = rs.getString(7);
                user = new User(name, email, phone, address);
                user.setRole(role);
                user.setId(id);
            }
        }catch (SQLException e) {
            System.out.println("Get user fail" + e.getMessage());
        }
        return user;
    }

    @Override
    public User getAccount(String email, String password) {
        User user = null;
        try {
            String sql = "SELECT * FROM  users WHERE email = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            // gan gia tri cho tham so id
            statement.setString(1, email);
            statement.setString(2, password);
            // thu hien truy van
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String emailUser = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(6);
                String role = rs.getString(7);
                user = new User(name, emailUser, phone, address);
                user.setRole(role);
                user.setId(id);
            }
        }catch (SQLException e) {
            System.out.println("Get user fail" + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> search(String key) {
        // viet cau lenh SQL;
        List<User> users = new ArrayList<User>();
        try {
            String sql = "SELECT * FROM users WHERE name LIKE ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, '%' + key + '%');
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String password = rs.getString(5);
                String address = rs.getString(6);
                String role = rs.getString(7);
                User user = new User(id, name, email, password, phone, address, role);
                users.add(user);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void updateUser2() throws SQLException {

        String sql = "UPDATE users SET name = ?, phone = ?, address = ?, role = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        // gan gia tri cho tham so id
        statement.setString(1, "nam 123");
        statement.setString(2, "0099900");
        statement.setString(3, "HHN");
        statement.setString(4, "admin");
        statement.setInt(5, 7);
        boolean status = statement.execute();
        System.out.println(status);
        // thu hien truy van
        if (!status) {
            throw new SQLException("Unexpected statement");
        }
    }
}
