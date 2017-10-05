package com.example.cinema_spring_app.controller;
import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.repo.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Observable;
@Component
public class MovieObservable extends Observable {

    private final MovieRepository movieRepository;
    private List<Movie> moviesDB;

    public MovieObservable(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        moviesDB = movieRepository.findAll();
    }

    public List<Movie> getMoviesDB() {
        return moviesDB;
    }

    public void setMoviesDB(List<Movie> moviesDB) {
        this.moviesDB = moviesDB;
        setChanged();
        notifyObservers();
    }
}
