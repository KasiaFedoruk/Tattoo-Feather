package pl.mg.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mg.project.entity.Contact;
import pl.mg.project.service.ContactService;

@Controller
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    @PostMapping("/authenticateContact")
    public String authenticateContact(@ModelAttribute("contact") Contact contact,
                                      Model model) {
        if (contact.getPhone() == null || contact.getPhone().isEmpty()) {
            model.addAttribute("error", "Incorrect username or password!");
            return "contact-form";
        }
            contactService.save(contact);
        return "contact-success";
    }
}
