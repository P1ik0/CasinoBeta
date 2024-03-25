package com.example.demo.Controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController{

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String phone, @RequestParam String email, @RequestParam String password) {
        // Создаем новый экземпляр User
        User user = new User();
        // Устанавливаем значения
        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        // Сохраняем пользователя в базе данных
        userRepository.save(user);
        // Возвращаем имя пользователя (или другое значение, если нужно)
        return "/Main"; // например, страница с сообщением об успешной регистрации
    }

    // Другие методы контроллера
}
