package pl.mg.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.project.dao.PortfolioRepository;
import pl.mg.project.entity.Portfolio;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    private PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(int id) {
        portfolioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }
}
