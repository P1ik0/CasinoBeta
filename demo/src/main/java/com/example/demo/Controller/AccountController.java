package com.example.demo.Controller;

import com.example.demo.Server.AccountService;
import com.example.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountService.withdraw(id, amount);
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Получение баланса пользователя из базы данных
        Long userId = 1L; // Предположим, что у пользователя с идентификатором 1L
        Optional<Account> accountOptional = accountService.getAccount(userId);
        double balance = accountOptional.map(Account::getBalance).orElse(0.0);

        // Добавление баланса в модель
        model.addAttribute("balance", balance);

        // Возвращаем имя представления (шаблона Thymeleaf)
        return "profile";
    }

}
