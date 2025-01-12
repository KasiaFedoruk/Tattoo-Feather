package pl.mg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mg.project.entity.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
}
