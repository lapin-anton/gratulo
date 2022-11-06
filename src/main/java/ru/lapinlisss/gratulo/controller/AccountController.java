package ru.lapinlisss.gratulo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lapinlisss.gratulo.model.entity.Account;
import ru.lapinlisss.gratulo.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public String login(@ModelAttribute Account account, HttpServletRequest req) {
        Optional<Account> accountDb = accountService.findAccount(account);
        if (accountDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("account", accountDb.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @GetMapping("/formAddAccount")
    public String addUser(Model model, HttpSession session) {
        Account account = getAccount(session);
        model.addAttribute("account", account);
        model.addAttribute("newAccount", new Account());
        return "addAccount";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Account account) {
        Account regAccount = accountService.save(account);
        if (regAccount == null) {
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/fail")
    public String fail(Model model, HttpSession session) {
        Account account = getAccount(session);
        model.addAttribute("account", account);
        model.addAttribute("message", "Учетная запись с таким логином уже зарегистрирована в приложении.");
        return "fail";
    }

    @GetMapping("/success")
    public String success(Model model, HttpSession session) {
        Account account = getAccount(session);
        model.addAttribute("account", account);
        return "success";
    }

    private Account getAccount(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            account = new Account();
            account.setLogin("Гость");
        }
        return account;
    }

}
