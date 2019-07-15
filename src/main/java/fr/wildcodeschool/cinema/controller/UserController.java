package fr.wildcodeschool.cinema.controller;

import fr.wildcodeschool.cinema.entity.Movie;
import fr.wildcodeschool.cinema.entity.User;
import fr.wildcodeschool.cinema.repository.MovieRepository;
import fr.wildcodeschool.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/users/signUp")
    public User signUp(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/users/signIn")
    public User signIn(@RequestBody User user) {
        return userRepository.findByEmailIgnoreCaseAndPassword(user.getEmail(),
                user.getPassword());
    }

    @PostMapping("/users/{idUser}/movies/{idMovie}")
    public User addFavorite(@PathVariable Long idUser,
                            @PathVariable Long idMovie) {
        User user = userRepository.findById(idUser).get();
        Movie movie = movieRepository.findById(idMovie).get();
        user.getMovies().add(movie);
        movie.getUsers().add(user);
        movieRepository.save(movie);
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{idUser}/movies/{idMovie}")
    public User removeFavorite(@PathVariable Long idUser,
                               @PathVariable Long idMovie) {
        User user = userRepository.findById(idUser).get();
        Movie movie = movieRepository.findById(idMovie).get();
        user.getMovies().remove(movie);
        movie.getUsers().remove(user);
        movieRepository.save(movie);
        return userRepository.save(user);
    }
}
