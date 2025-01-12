package pl.mg.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mg.project.entity.Portfolio;
import pl.mg.project.service.PortfolioService;

import java.util.List;

@Controller
public class PortfolioController {


    private PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/portfolio")
    public String showPortfolio(Model model) {
        List<Portfolio> photos = portfolioService.findAll();
        model.addAttribute("photos", photos);

        return "portfolio";
    }
}
