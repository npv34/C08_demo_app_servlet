package com.example.usermanager.Model;

import com.example.usermanager.Entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAll() throws SQLException;
    void delete(int id) throws SQLException;
    void save(User user) throws SQLException;
    void update(User user) throws SQLException;
    User getById(int id);

    User getAccount(String email, String password);

    List<User> search(String key);

    void updateUser2() throws SQLException;
}
