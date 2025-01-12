package pl.mg.project.service;

import org.springframework.stereotype.Service;
import pl.mg.project.entity.User;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();
    void save(User user);
    void deleteById(int id);
}
