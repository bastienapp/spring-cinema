package fr.wildcodeschool.cinema.controller;

import fr.wildcodeschool.cinema.entity.User;
import fr.wildcodeschool.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/signIn")
    public User signIn(@RequestBody User user) {
        return userRepository.findByEmailIgnoreCaseAndPassword(user.getEmail(),
                user.getPassword());
    }

    // TODO : add movie to favorites

    // TODO : show favorites
}
