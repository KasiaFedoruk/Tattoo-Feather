package pl.mg.project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mg.project.entity.*;
import pl.mg.project.service.*;

import java.util.List;

@Controller
@RequestMapping("/admin-panel")
public class AdminController {

    private ContactService contactService;
    private ArtistService artistService;
    private AvailabilityService availabilityService;
    private PortfolioService portfolioService;
    private AppointmentService appointmentService;

    public AdminController(ContactService contactService, ArtistService artistService, AvailabilityService availabilityService, PortfolioService portfolioService, AppointmentService appointmentService) {
        this.contactService = contactService;
        this.artistService = artistService;
        this.availabilityService = availabilityService;
        this.portfolioService = portfolioService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String showAdminPanel(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"admin".equals(user.getUsername())) {
            return "redirect:/login";
        }
        return "admin-panel";
    }

    @GetMapping("/contact")
    public String showContact(Model model) {
        List<Contact> contacts = contactService.findAll();
        model.addAttribute("contact", contacts);
        return "admin-panel/admin-contact";
    }

    @PostMapping("/delete-message")
    public String deleteMessage(@RequestParam("messageId") int id) {
        contactService.delete(id);
        return "redirect:/admin-panel/";
    }


    @GetMapping("/artist")
    public String showArtist(Model model) {
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artist", artists);
        return "admin-panel/admin-artist";
    }

    @PostMapping("/add-artist")
    public String addArtist(@RequestParam("name") String name,
                            @RequestParam("photoURL") String photoURL,
                            @RequestParam("description") String description) {
        Artist artist = new Artist();
        artist.setName(name);
        artist.setPhotoURL(photoURL);
        artist.setDescription(description);
        artistService.save(artist);
        return "redirect:/admin-panel/artist";
    }

    @PostMapping("/delete-artist")
    public String deleteMessage(@RequestParam("artistName") String name) {
        artistService.delete(name);
        return "redirect:/admin-panel/artist";
    }

    @GetMapping("/availability")
    public String showAvailability(Model model) {
        List<Availability> availabilities = availabilityService.findAll();
        List<Artist> artists = artistService.findAll();
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("artists", artists);
        return"admin-panel/admin-availability";
    }

    @PostMapping("/add-availability")
    public String addAvailability(@RequestParam("date") String date,
                                  @RequestParam("time") String time,
                                  @RequestParam("artistId") int artistId) {
        Artist artist = artistService.findById(artistId);
        if (artist == null) {
            throw new RuntimeException("Nie ma takiego id " + artistId);
        }
        Availability availability = new Availability(date, time, artist);
        availabilityService.save(availability);
        return "redirect:/admin-panel/availability";
    }

    @PostMapping("/delete-availability")
    public String deleteAvailability(@RequestParam("availabilityId") int availabilityId) {
        availabilityService.delete(availabilityId);
        return "redirect:/admin-panel/availability";
    }

    @GetMapping("/portfolio")
    public String showPortfolio(Model model) {
        List<Portfolio> portfolios = portfolioService.findAll();
        List<Artist> artists = artistService.findAll();
        model.addAttribute("portfolios", portfolios);
        model.addAttribute("artists", artists);
        return "admin-panel/admin-portfolio";
    }

    @PostMapping("/add-portfolio")
    public String addPortfolio(@RequestParam("photoURL") String photoURL,
                               @RequestParam("artistId") int artistId) {
        Artist artist = artistService.findById(artistId);
        if (artist == null) {
            throw new RuntimeException("Nie ma takiego id " + artistId);
        }
        Portfolio portfolio = new Portfolio(photoURL, artist);
        portfolioService.save(portfolio);
        return "redirect:/admin-panel/portfolio";
    }

    @PostMapping("/delete-portfolio")
    public String deletePortfolio(@RequestParam("portfolioId") int portfolioId) {
        portfolioService.delete(portfolioId);
        return "redirect:/admin-panel/portfolio";
    }

    @GetMapping("/appointment")
    public String showAppointments(Model model) {
        List<Appointment> appointments = appointmentService.findAll();
        model.addAttribute("appointments", appointments);
        return "admin-panel/admin-appointment";
    }

    @PostMapping("/delete-appointment")
    public String deleteAppointment(@RequestParam("appointmentId") int appointmentId) {
        System.out.println(appointmentId);
        appointmentService.delete(appointmentId);
        return "redirect:/admin-panel/appointment";
    }
}
