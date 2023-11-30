package com.example.usermanager;
import com.example.usermanager.Controller.UserController;
import com.example.usermanager.Entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    protected ArrayList<User> users;
    protected UserController userController;
    @Override
    public void init() throws ServletException {
        users = new ArrayList<User>();
        users.add(new User("John", "John@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff", "Jeff1@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff2", "Jeff2@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff3", "Jeff3@gmail.com", "00990898", "Ha Noi"));
        users.add(new User("Jeff3", "Jeff4@gmail.com", "00990898", "Ha Noi"));
        userController = new UserController(users);
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
                try {
                    userController.deleteUser(req, resp);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "create":
                userController.showFormCreate(req, resp);
                break;
            case "update":
                userController.showFormUpdate(req, resp);
                break;
            default:
                userController.showListUser(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        switch (action) {
            case "create":
                userController.storeUser(req, resp);
                break;
            case "update":
                userController.updateUser(req, resp);
                break;
        }
    }
}
