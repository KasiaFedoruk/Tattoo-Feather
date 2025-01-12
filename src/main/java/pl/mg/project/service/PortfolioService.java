package pl.mg.project.service;

import pl.mg.project.entity.Portfolio;

import java.util.List;

public interface PortfolioService {

    List<Portfolio> findAll();
    void delete(int id);
    void save(Portfolio portfolio);
}
