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
import java.time.LocalDate;
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
            tableMoviePanel.getModel().addRow(movieData);
        }
    }
    public void fillTheTableForSelectedMovie(Movie movie){
        showMovieSeances.getModel().setRowCount(0);
        List<Seance> seancesOfSelectedMovie = seanceRepository.findByMovie(movie, new Sort(
                new Sort.Order(Sort.Direction.ASC, "date"), new Sort.Order(Sort.Direction.ASC, "time")));
        for (Seance s : seancesOfSelectedMovie) {
            String[] seanceData = {String.valueOf(s.getId()), s.getMovie().getTitle(),String.valueOf(s.getDate()), String.valueOf(s.getTime()), String.valueOf(s.getHall().getId())};
            showMovieSeances.getModel().addRow(seanceData);
        }
    }

    public List<Movie> getAllTheMovies(){
        return movieRepository.findAll();
    }
    public Movie getSelectedMovie() {
        Movie movie = null;
        if (tableMoviePanel.getMovieTable().getSelectedRow() > -1) {
            int id = Integer.parseInt((String) tableMoviePanel.getMovieTable().getModel().getValueAt(tableMoviePanel.getMovieTable().getSelectedRow(), 0));
            movie = movieRepository.findOne(id);
        }
        return movie;
    }
    public void showSelected() {
        Movie movie = getSelectedMovie();
        detailsMoviePanel.getTitleData().setText(movie.getTitle());
        detailsMoviePanel.getPremiereData().setText(String.valueOf(movie.getYear()));
        detailsMoviePanel.getDirectorData().setText(movie.getDirector());
        detailsMoviePanel.getGenreData().setText(movie.getGenre().toString());
        detailsMoviePanel.getDurationData().setText(String.valueOf(movie.getDuration()));
        detailsMoviePanel.getAgeData().setText(movie.getAgeCategory().toString());
    }
    public void addMovie() {
        Movie movie = new Movie();
        movie.setTitle(addMovieFrame.getTitleData().getText());
        movie.setYear(Date.valueOf(LocalDate.of(
                (Integer) addMovieFrame.getPremiereYear().getSelectedItem(),
                (Integer) addMovieFrame.getPremiereMonth().getSelectedItem(),
                (Integer) addMovieFrame.getPremiereDay().getSelectedItem())));
        movie.setDirector(addMovieFrame.getDirectorData().getText());
        movie.setGenre((MovieGenre) addMovieFrame.getGenreBox().getSelectedItem());
        movie.setDuration((Integer)(addMovieFrame.getDurationData().getSelectedItem()));
        movie.setAgeCategory((MovieCategory) addMovieFrame.getCategoryBox().getSelectedItem());
        movieRepository.save(movie);
        updateView();

    }
    public void removeMovie(){
        Movie movie = getSelectedMovie();
        movieRepository.delete(movie);
        updateView();
    }

    public void showEditedMovie(Movie movie){
        editMovieFrame.getTitleData().setText(movie.getTitle());
        editMovieFrame.getDirectorData().setText(movie.getDirector());
    }

    public void editMovie(Movie movie){

        movieRepository.updateTitle(movie.getId(), editMovieFrame.getTitleData().getText());
        movieRepository.updateYear(movie.getId(), Date.valueOf(LocalDate.of(
                (Integer) addMovieFrame.getPremiereYear().getSelectedItem(),
                (Integer) addMovieFrame.getPremiereMonth().getSelectedItem(),
                (Integer) addMovieFrame.getPremiereDay().getSelectedItem())));
        movieRepository.updateDirector(movie.getId(), editMovieFrame.getDirectorData().getText());
        movieRepository.updateGenre(movie.getId(), (MovieGenre) editMovieFrame.getGenreBox().getSelectedItem());
        movieRepository.updateDuration(movie.getId(), (Integer)(editMovieFrame.getDurationData().getSelectedItem()));
        movieRepository.updateCategory(movie.getId(),(MovieCategory) editMovieFrame.getCategoryBox().getSelectedItem());
    }

    public void updateView(){
        movieObservable.addObserver(tableMoviePanel);
        movieObservable.setMoviesDB(movieRepository.findAll());
    }
}



