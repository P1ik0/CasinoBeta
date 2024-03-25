package com.example.demo.Controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.Server.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/Main")
    public String mainPage() {
        return "Main";
    }

    // Change @GetMapping to @PostMapping
    @PostMapping("/Main")
    public String handleMainPostRequest() {
        // Logic for handling POST request on /Main
        return "Main"; // Or redirect to another page based on your logic
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @Autowired Model model) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            return "login";
        } else {
            return "login";
        }
    }

}