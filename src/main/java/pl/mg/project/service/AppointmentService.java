package pl.mg.project.service;

import pl.mg.project.entity.Appointment;
import pl.mg.project.entity.User;

import java.util.List;

public interface AppointmentService {

    List<Appointment> findAll();
    List<Appointment> findByUser(User user);
    void save(Appointment appointment);
    void delete(int id);

}
