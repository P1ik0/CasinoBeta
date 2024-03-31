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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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




    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }
    @GetMapping("/slots3")
    public String slots3(){
        return  "slots3";
    }
    @GetMapping("/slots2")
    public String slots2(){
        return "slots1";
    }

    @GetMapping("/deposit")
    public String deposit() {
        return "deposit";
    }
    @GetMapping("/deposit2")
    public String deposit2() {
        return "deposit2";
    }

    @GetMapping("/sl")
    public String SlotMachine() {
        return "SlotMachine";
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
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "User with this username already exists");
            return null;
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "User with this email already exists");
            return null;
        }

        // Устанавливаем начальный баланс пользователя
        user.setBalance(Integer.parseInt(String.valueOf(0)));

        // Если пользователь с такими данными не существует, сохраняем нового пользователя
        userRepository.save(user);
        return "redirect:/register";
    }

    public class DateUtils {
        public static LocalDate parseDate(String dateString) {
            // Создаем форматтер для даты в формате "день.месяц.год"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            // Парсим строку в объект LocalDate с помощью указанного форматтера
            return LocalDate.parse(dateString, formatter);
        }
    }
    @PostMapping("/registerinfo")
    public String saveUserInfo(@ModelAttribute("user") User updatedUser, HttpSession session) {
        // Получаем пользователя из сессии
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Если пользователь не аутентифицирован, перенаправляем на страницу входа
            return "redirect:/login";
        }

        // Получаем пользователя из базы данных по его id
        Long userId = user.getId(); // Предполагается, что у пользователя есть id
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser == null) {
            // Обработка ситуации, если профиль не найден
            // Можно вернуть ошибку или другое представление
            return "error";
        }

        // Обновляем существующего пользователя с новыми данными
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setPlaceholder(updatedUser.getPlaceholder());
        existingUser.setDob(updatedUser.getDob());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setPhone(updatedUser.getPhone());

        // Устанавливаем флаг завершенности профиля в true
        existingUser.setProfileCompleted(true);

        // Сохраняем обновленного пользователя в базе данных
        userRepository.save(existingUser);
        session.setAttribute("user", existingUser);

        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, HttpSession session) {
        // Получаем пользователя из сессии
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Если пользователь не аутентифицирован, перенаправляем на страницу входа
            return "redirect:/login";
        }

        // Получаем пользователя из базы данных по его id
        Long userId = user.getId(); // Предполагается, что у пользователя есть id
        User userProfile = userRepository.findById(userId).orElse(null);

        if (userProfile == null) {
            // Обработка ситуации, если профиль не найден
            // Можно вернуть ошибку или другое представление
            return "error";
        }

        // Проверяем, завершен ли профиль, и выбираем представление для отображения
        if (userProfile.isProfileCompleted()) {
            model.addAttribute("userProfile", userProfile);
            return "profile-completed"; // Представление для завершенного профиля
        } else {
            model.addAttribute("userProfile", userProfile);
            return "profile"; // Представление для редактирования профиля
        }
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
            session.setAttribute("authenticated", true);
            session.setAttribute("user", user);
            model.addAttribute("message", "User successfully authenticated");
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Authentication failed");
            return "register";
        }
    }


    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Boolean isAuthenticated = (Boolean) session.getAttribute("authenticated");
        if (isAuthenticated != null && isAuthenticated) {
            model.addAttribute("menuText", "Профиль");
        } else {
            model.addAttribute("menuText", "Вход");
        }
        return "index";
    }

    @Controller
    public class LogoutController {

        @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.removeAttribute("user");
            session.setAttribute("authenticated", false);
            return "redirect:/Main";
        }
    }

}
