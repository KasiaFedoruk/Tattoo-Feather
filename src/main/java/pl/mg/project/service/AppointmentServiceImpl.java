package pl.mg.project.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.project.dao.AppointmentRepository;
import pl.mg.project.entity.Appointment;
import pl.mg.project.entity.User;

import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{

    @PersistenceContext
    private EntityManager entityManager;

    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findByUser(User user) {
        return appointmentRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public void delete(int id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment with ID " + id + " does not exist");
        }
        appointmentRepository.deleteById(id);
    }
}
