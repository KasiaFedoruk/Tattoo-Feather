package pl.mg.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mg.project.service.AppointmentService;
import pl.mg.project.service.AvailabilityService;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;
    private AvailabilityService availabilityService;

    public AppointmentController(AppointmentService appointmentService, AvailabilityService availabilityService) {
        this.appointmentService = appointmentService;
        this.availabilityService = availabilityService;
    }

    @GetMapping("/appointments")
    public String showAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        return "appointments";
    }
}
