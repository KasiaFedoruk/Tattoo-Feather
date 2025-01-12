package pl.mg.project.service;

import pl.mg.project.entity.Availability;

import java.util.List;

public interface AvailabilityService {

    List<Availability> findAll();
    Availability findById(int id);
    void delete(int id);
    void save(Availability availability);
}
