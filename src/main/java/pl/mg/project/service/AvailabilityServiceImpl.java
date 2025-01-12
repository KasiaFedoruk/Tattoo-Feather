package pl.mg.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.project.dao.AvailabilityRepository;
import pl.mg.project.entity.Availability;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    private AvailabilityRepository availabilityRepository;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Availability findById(int id) {
        Optional<Availability> result = availabilityRepository.findById(id);
        Availability availability = null;
        if (result.isPresent()) {
            availability = result.get();
        } else {
            throw new RuntimeException("Nie ma takiego id " + id);
        }
        return availability;
    }

    @Override
    @Transactional
    public void delete(int id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Availability availability) {
        availabilityRepository.save(availability);
    }
}
