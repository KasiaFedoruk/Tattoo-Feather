package pl.mg.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.project.PasswordEncoderUtil;
import pl.mg.project.dao.UserRepository;
import pl.mg.project.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        String encryptedPassword = PasswordEncoderUtil.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
