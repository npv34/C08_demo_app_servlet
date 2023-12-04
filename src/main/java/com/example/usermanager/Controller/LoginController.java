package com.example.usermanager.Controller;

import com.example.usermanager.Entity.User;
import com.example.usermanager.Model.UserDAO;
import com.example.usermanager.Model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    UserDAO userDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        this.userDAO = new UserModel();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showFormLogin(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       submitLogin(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public void showFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp");
        view.forward(req, resp);
    }

    public void submitLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // b1: Lay data tu request
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        // chuyen xuong model
        User user = this.userDAO.getAccount(email, password);
        session.setAttribute("userLogin", user);
        if (user == null) {
            session.setAttribute("Error", "Account not exist!");
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/users");
        }
    }
}
