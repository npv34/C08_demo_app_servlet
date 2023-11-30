package com.example.usermanager.Controller;

import com.example.usermanager.Entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UserController {
    protected ArrayList<User> users;

    public UserController(ArrayList<User> users) {
        this.users = users;
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        int index = getIndexUser(email);
        User user = users.get(index);

        String newName = req.getParameter("name");
        String newPhone = req.getParameter("phone");
        String newAddress = req.getParameter("address");
        user.setName(newName);
        user.setPhone(newPhone);
        user.setAddress(newAddress);

        users.set(index, user);
        resp.sendRedirect("/users");
    }

    public void storeUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String role = req.getParameter("role");
        User user = new User(name, email, phone, address);
        user.setRole(role);
        users.add(user);
        resp.sendRedirect("/users");
    }

    public void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users);
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/users/list.jsp");
        view.forward(req, resp);
    }

    public void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/users/add.jsp");
        view.forward(req, resp);
    }

    public void showFormUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        int index = getIndexUser(email);
        User user = users.get(index);
        req.setAttribute("user", user);
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/users/update.jsp");
        view.forward(req, resp);
    }

    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String email = req.getParameter("email");
        int index = getIndexUser(email);

        if (index == -1) {
            throw new Exception("User not exist!");
        } else {
            users.remove(index);
            resp.sendRedirect("/users");
        }
    }

    private int getIndexUser(String email) {
        int index = -1;
        // tim vi tri index cua user can xoa
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
