package pl.mg.project.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mg.project.dao.UserRepository;
import pl.mg.project.entity.User;
import pl.mg.project.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    public RegistrationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register-form";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            return "register-form";
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Użytkownik o takiej nazwie już istnieje!");
            return "register-form";
        }

        if ("admin".equalsIgnoreCase(user.getUsername())) {
            model.addAttribute("error", "Nie można zarejestrować użytkownika o nazwie 'admin'!");
            return "register-form";
        }

        userService.save(user);
        model.addAttribute("user", user);

        return "register-success";
    }

}