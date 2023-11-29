package com.example.usermanager;

import com.example.usermanager.Entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    protected ArrayList<User> users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<User>();
        users.add(new User("John", "John@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff", "Jeff@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff2", "Jeff@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff3", "Jeff@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff3", "Jeff@gmail.com", "00990898", "Ha Noi"));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        switch (action) {
            case "delete":
                break;
            case "create":
                showFormCreate(req, resp);
                break;
            default:
                showListUser(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lay du lieu request
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        User user = new User(name, email, phone, address);
        users.add(user);
        resp.sendRedirect("/users");
    }

    public void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users);
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/users.jsp");
        view.forward(req, resp);
    }

    public void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/add.jsp");
        view.forward(req, resp);
    }
}
