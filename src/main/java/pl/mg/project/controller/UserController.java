package pl.mg.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mg.project.entity.Appointment;
import pl.mg.project.entity.Availability;
import pl.mg.project.entity.User;
import pl.mg.project.service.AppointmentService;
import pl.mg.project.service.AvailabilityService;
import pl.mg.project.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user-panel")
public class UserController {

    private AppointmentService appointmentService;
    private AvailabilityService availabilityService;
    private UserService userService;

    public UserController(AppointmentService appointmentService, AvailabilityService availabilityService, UserService userService) {
        this.appointmentService = appointmentService;
        this.availabilityService = availabilityService;
        this.userService = userService;
    }

    @GetMapping
    public String showUserPanel(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        if ("admin".equals(user.getUsername())) {
            return "redirect:/admin-panel";
        }

        // Fetch user's appointments
        List<Appointment> userAppointments = appointmentService.findByUser(user);

        // Fetch available slots
        model.addAttribute("user", user);
        model.addAttribute("appointments", userAppointments);
        model.addAttribute("availabilities", availabilityService.findAll());

        return "user-panel";
    }

    @PostMapping("/book")
    public String bookAppointment(@RequestParam("availabilityId") int availabilityId, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        Availability availability = availabilityService.findById(availabilityId);
        if (availability == null) {
            throw new RuntimeException("Availability not found with ID: " + availabilityId);
        }

        Appointment appointment = new Appointment(availability.getDate(), availability.getTime(), user, availability.getArtist());
        appointmentService.save(appointment);

        availabilityService.delete(availabilityId);

        return "redirect:/user-panel";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<Appointment> userAppointments = appointmentService.findByUser(user);

        for (Appointment appointment : userAppointments) {
            Availability availability = new Availability(
                    appointment.getDate(),
                    appointment.getTime(),
                    appointment.getArtist()
            );
            availabilityService.save(availability);
        }

        userAppointments.forEach(appointment -> appointmentService.delete(appointment.getId()));

        userService.deleteById(user.getId());

        session.invalidate();

        return "redirect:/";
    }
}
