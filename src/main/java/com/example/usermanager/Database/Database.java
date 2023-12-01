package com.example.usermanager.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    protected String jdbcURL;
    protected String jdbcUsername;
    protected String jdbcPassword;

    public static Database instance;


    private Database() {
        this.jdbcURL = "jdbc:mysql://localhost:3306/shop_app?useSSL=false";
        this.jdbcUsername = "root";
        this.jdbcPassword = "123456@Abc";
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage() + " error");
        }
        return null;
    }

}
