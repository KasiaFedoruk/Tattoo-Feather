package pl.mg.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mg.project.dao.UserRepository;
import pl.mg.project.entity.User;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login-page";
    }

    @PostMapping("/authenticateTheUser")
    public String authenticateUser(@RequestParam String username,
                                   @RequestParam String password,
                                   Model model, HttpSession session) {

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Incorrect username or password!");
            model.addAttribute("user", new User());
            return "login-page";
        }

        User user = userOptional.get();

        if (!BCrypt.checkpw(password, user.getPassword())) {
            model.addAttribute("error", "Incorrect username or password!");
            model.addAttribute("user", new User());
            return "login-page";
        }

        session.setAttribute("loggedUser", user);

        if ("admin".equals(user.getUsername())) {
            return "redirect:/admin-panel";
        } else {
            return "redirect:/user-panel";
        }
    }
}
