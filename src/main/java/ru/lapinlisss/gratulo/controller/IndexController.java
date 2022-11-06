package ru.lapinlisss.gratulo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lapinlisss.gratulo.model.entity.Account;
import ru.lapinlisss.gratulo.service.EventService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final EventService eventService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            account = new Account();
            account.setLogin("Гость");
        }
        model.addAttribute("account", account);
        model.addAttribute("events", eventService.findAllByAccount(account));
        return "index";
    }

}
