package com.example.demo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.demo.Server.UserService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

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
        return "register";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        // Проверяем, существует ли пользователь с таким же именем пользователя или адресом электронной почты

            // Если пользователя с таким именем пользователя или адресом электронной почты нет, сохраняем нового пользователя
            userRepository.save(user);
            model.addAttribute("message", "Submitted Successfully");
            return "redirect:/Main";
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
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            model.addAttribute("message", "User successfully authenticated");
            return "redirect:/Main";
        } else {
            model.addAttribute("message", "Authentication failed");
            return "register";
        }
    }

}