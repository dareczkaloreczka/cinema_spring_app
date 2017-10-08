package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.repo.MovieRepository;
import com.example.cinema_spring_app.model.repo.SeanceRepository;
import com.example.cinema_spring_app.view.AddMovieFrame;
import com.example.cinema_spring_app.view.DetailsMoviePanel;
import com.example.cinema_spring_app.view.EditMovieFrame;
import com.example.cinema_spring_app.view.ShowMovieSeances;
import com.example.cinema_spring_app.view.TableMoviePanel;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.List;


@Component
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieObservable movieObservable;
    private  final TableMoviePanel tableMoviePanel;
    private final DetailsMoviePanel detailsMoviePanel;
    private final AddMovieFrame addMovieFrame;
    private final EditMovieFrame editMovieFrame;
    private final SeanceRepository seanceRepository;
    private final ShowMovieSeances showMovieSeances;

    @Lazy
    public MovieController(MovieRepository movieRepository, MovieObservable movieObservable, TableMoviePanel tableMoviePanel, DetailsMoviePanel detailsMoviePanel, AddMovieFrame addMovieFrame, EditMovieFrame editMovieFrame, SeanceRepository seanceRepository, ShowMovieSeances showMovieSeances) {
        this.movieRepository = movieRepository;
        this.movieObservable = movieObservable;
        this.tableMoviePanel = tableMoviePanel;
        this.detailsMoviePanel = detailsMoviePanel;
        this.addMovieFrame = addMovieFrame;
        this.editMovieFrame = editMovieFrame;
        this.seanceRepository = seanceRepository;
        this.showMovieSeances = showMovieSeances;
    }

    public void fillTheTable() {
        List<Movie> movieList = movieRepository.findAll();
        for (Movie m : movieList) {
            String[] movieData = {String.valueOf(m.getId()), m.getTitle(), String.valueOf(m.getGenre())};
            tableMoviePanel.fillTheRow(movieData);
        }
    }
    public void fillTheTableForSelectedMovie(Movie movie){
        showMovieSeances.clearContent();
        List<Seance> seancesOfSelectedMovie = seanceRepository.findByMovie(movie, new Sort(
                new Sort.Order(Sort.Direction.ASC, "date"), new Sort.Order(Sort.Direction.ASC, "time")));
        for (Seance s : seancesOfSelectedMovie) {
            String[] seanceData = {String.valueOf(s.getId()), s.getMovie().getTitle(),String.valueOf(s.getDate()), String.valueOf(s.getTime()), String.valueOf(s.getHall().getId())};
            showMovieSeances.fillTheRow(seanceData);
        }
    }

    public List<Movie> getAllTheMovies(){
        return movieRepository.findAll();
    }
    public Movie getSelectedMovie() {
            return movieRepository.findOne(tableMoviePanel.getSelectedIndex());
    }
    public void showSelected(Movie movie) {
        detailsMoviePanel.setMovieData(movie);
    }
    public void addMovie() {
        Movie movie = new Movie();
        addMovieFrame.setFieldsForMovie(movie);
        movieRepository.save(movie);
        updateView();

    }
    public void removeMovie(Movie movie){
        movieRepository.delete(movie);
        updateView();
    }

    public void showEditedMovie(Movie movie){
        editMovieFrame.setFields(movie);

    }

    public void editMovie(Movie movie){
        movieRepository.updateTitle(movie.getId(), editMovieFrame.getMovieData()[0]);
        movieRepository.updateYear(movie.getId(), Date.valueOf(editMovieFrame.getMovieData()[1]));
        movieRepository.updateDirector(movie.getId(), editMovieFrame.getMovieData()[2]);
        movieRepository.updateGenre(movie.getId(), MovieGenre.valueOf(editMovieFrame.getMovieData()[3]));
        movieRepository.updateDuration(movie.getId(), Integer.parseInt(editMovieFrame.getMovieData()[4]));
        movieRepository.updateCategory(movie.getId(),MovieCategory.valueOf(editMovieFrame.getMovieData()[5]));
    }

    public void updateView(){
        movieObservable.addObserver(tableMoviePanel);
        movieObservable.setMoviesDB(movieRepository.findAll());
    }
}



