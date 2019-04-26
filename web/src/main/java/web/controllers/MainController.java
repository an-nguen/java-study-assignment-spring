package web.controllers;

import domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import persistence.services.UserServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private UserServiceImpl userService;

    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers(Model model) {
        return (List<User>) this.userService.select();
    }

    @RequestMapping(value = "/usersJSON", method = RequestMethod.GET)
    public List<Map<String, String>> getUsersJSON(Model model) {
        return this.userService.getJSON();
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }
}
