package app.service;

import app.entity.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public User loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void setUserStreaks(User user) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastLogin = user.getLastLogin();
        long hours = Math.abs(ChronoUnit.HOURS.between(today, lastLogin));

        if (hours >= 24 && hours < 48) {
            user.setStreaks(user.getStreaks() + 1);
        }
        user.setLastLogin(LocalDateTime.now());
        save(user);

    }
}