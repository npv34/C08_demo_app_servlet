package com.example.usermanager.Controller;

import com.example.usermanager.Entity.User;
import com.example.usermanager.Model.UserDAO;
import com.example.usermanager.Model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    protected UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserModel();
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String newPhone = req.getParameter("phone");
        String newAddress = req.getParameter("address");
        String role = req.getParameter("role");
        User user = new User(name, email, newPhone, newAddress);
        user.setRole(role);
        user.setId(id);
        this.userDAO.update(user);
        resp.sendRedirect("/users");
    }

    public void storeUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String role = req.getParameter("role");
            String password = req.getParameter("password");
            User user = new User(name, email, phone, address);
            user.setPassword(password);
            user.setRole(role);
            this.userDAO.save(user);
            resp.sendRedirect("/users");
        }catch (SQLException e) {
            System.out.println("Error add user: " + e.getMessage());
        }
    }

    public void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String keyword = req.getParameter("keyword");
            List<User> data;
            if (keyword == null) {
                data =  this.userDAO.getAll();
            } else {
                data =  this.userDAO.search(keyword);
            }

            req.setAttribute("users", data);
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/users/list.jsp");
            view.forward(req, resp);
        }catch (SQLException e) {
            System.out.println(e.getMessage() + "Error list");
        }
    }

    public void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/users/add.jsp");
        view.forward(req, resp);
    }

    public void showFormUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = this.userDAO.getById(id);
        req.setAttribute("user", user);
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/users/update.jsp");
        view.forward(req, resp);
    }

    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        this.userDAO.delete(id);
        resp.sendRedirect("/users");
    }
}
