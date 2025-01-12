package pl.mg.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mg.project.entity.Contact;
import pl.mg.project.service.ContactService;

import java.util.List;

@Controller
public class HomeController {



    @GetMapping("/")
    public String register() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("loggedUser") != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }


    @GetMapping("/works")
    public String showWorks() {
        return "works";
    }

    @GetMapping("/price")
    public String showPrice() {
        return "price";
    }



}

