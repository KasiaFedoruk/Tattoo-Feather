package pl.mg.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mg.project.entity.Artist;
import pl.mg.project.service.ArtistService;

import java.util.List;

@Controller
public class ArtistController {

    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public String showArtists(Model model) {
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);

        return "artists";
    }
}
