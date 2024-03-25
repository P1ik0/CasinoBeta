package com.example.demo.Server;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User register(String username, String phone, String email, String password) {
        // Проверяем, существует ли пользователь с таким именем
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("User with this username already exists");
        }

        // Создаем новый экземпляр пользователя
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);

        // Сохраняем пользователя в базе данных
        return userRepository.save(user);
    }
}
