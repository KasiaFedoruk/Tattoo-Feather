package pl.mg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mg.project.entity.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
}
