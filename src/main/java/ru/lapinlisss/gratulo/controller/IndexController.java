package ru.lapinlisss.gratulo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lapinlisss.gratulo.model.Account;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            account = new Account();
            account.setLogin("Гость");
        }
        model.addAttribute("account", account);
        return "index";
    }

}
