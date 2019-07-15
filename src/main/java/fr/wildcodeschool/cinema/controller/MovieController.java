package fr.wildcodeschool.cinema.controller;

import fr.wildcodeschool.cinema.entity.Movie;
import fr.wildcodeschool.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/movies")
    public Movie create(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("/movies")
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @PutMapping("/movies/{id}")
    public Movie update(@PathVariable Long id,
                        @RequestBody Movie movieUpdate) {
        Movie movieFromDB = movieRepository.findById(id).get();
        if (movieUpdate.getTitle() != null && !movieUpdate.getTitle().isEmpty()) {
            movieFromDB.setTitle(movieUpdate.getTitle());
        }
        if (movieUpdate.getReleaseDate() != null) {
            movieFromDB.setReleaseDate(movieUpdate.getReleaseDate());
        }
        return movieRepository.save(movieFromDB);
    }
}
