package ru.lapinlisss.gratulo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.lapinlisss.gratulo.model.dto.EventCategoryDto;
import ru.lapinlisss.gratulo.model.entity.Account;
import ru.lapinlisss.gratulo.model.entity.Event;
import ru.lapinlisss.gratulo.service.EventCategoryService;
import ru.lapinlisss.gratulo.service.EventService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final EventCategoryService eventCategoryService;

    @GetMapping("/formAddEvent")
    public String addEvent(Model model, HttpSession session) {
        Account account = getAccount(session);
        model.addAttribute("account", account);
        model.addAttribute("categories", eventCategoryService.findAll());
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @PostMapping("/createEvent")
    public String createEvent(@ModelAttribute Event event, HttpSession session) {
        event.setAccount(getAccount(session));
        event.setEventCategory(eventCategoryService.findById(event.getEventCategory().getId()));
        eventService.add(event);
        return "redirect:/";
    }

    @GetMapping("/formUpdateEvent/{eventId}")
    public String formUpdatePost(Model model, @PathVariable("eventId") int id, HttpSession session) {
        Account account = getAccount(session);
        model.addAttribute("account", account);
        model.addAttribute("event", eventService.findById((long) id));
        model.addAttribute("categories", eventCategoryService.findAll());
        return "updateEvent";
    }

    @PostMapping("/updateEvent")
    public String updateEvent(@ModelAttribute Event event, HttpSession session) {
        event.setAccount(getAccount(session));
        event.setEventCategory(eventCategoryService.findById(event.getEventCategory().getId()));
        eventService.update(event);
        return "redirect:/";
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
