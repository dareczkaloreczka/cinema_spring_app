package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
import com.example.cinema_spring_app.model.repo.MovieRepository;
import com.example.cinema_spring_app.view.AddMovieFrame;
import com.example.cinema_spring_app.view.DetailsMoviePanel;
import com.example.cinema_spring_app.view.EditMovieFrame;
import com.example.cinema_spring_app.view.TableMoviePanel;
import org.springframework.context.annotation.Lazy;
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

    @Lazy
    public MovieController(MovieRepository movieRepository, MovieObservable movieObservable, TableMoviePanel tableMoviePanel, DetailsMoviePanel detailsMoviePanel, AddMovieFrame addMovieFrame, EditMovieFrame editMovieFrame) {
        this.movieRepository = movieRepository;
        this.movieObservable = movieObservable;
        this.tableMoviePanel = tableMoviePanel;
        this.detailsMoviePanel = detailsMoviePanel;
        this.addMovieFrame = addMovieFrame;
        this.editMovieFrame = editMovieFrame;
    }

    public void fillTheTable() {
        List<Movie> movieList = movieRepository.findAll();
        for (Movie m : movieList) {
            String[] movieData = {String.valueOf(m.getId()), m.getTitle(), String.valueOf(m.getGenre())};
            tableMoviePanel.getModel().addRow(movieData);
        }
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
        movie.setYear(Date.valueOf(addMovieFrame.getPremiereData().getText()));
        movie.setDirector(addMovieFrame.getDirectorData().getText());
        movie.setGenre((MovieGenre) addMovieFrame.getGenreBox().getSelectedItem());
        movie.setDuration(Integer.parseInt(addMovieFrame.getDurationData().getText()));
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
        editMovieFrame.getPremiereData().setText(movie.getYear().toString());
        editMovieFrame.getDirectorData().setText(movie.getDirector());
        editMovieFrame.getDurationData().setText(String.valueOf(movie.getDuration()));
    }

    public void editMovie(Movie movie){
       /* movie.setTitle(editFrame.getTitleData().getText());
        movie.setYear(Date.valueOf(editFrame.getPremiereData().getText()));
        movie.setDirector(editFrame.getDirectorData().getText());
        movie.setGenre((MovieGenre) editFrame.getGenreBox().getSelectedItem());
        movie.setDuration(Integer.parseInt(editFrame.getDurationData().getText()));
        movie.setAgeCategory((MovieCategory) editFrame.getCategoryBox().getSelectedItem());*/

        movieRepository.updateTitle(movie.getId(), editMovieFrame.getTitleData().getText());
        movieRepository.updateYear(movie.getId(), Date.valueOf(editMovieFrame.getPremiereData().getText()));
        movieRepository.updateDirector(movie.getId(), editMovieFrame.getDirectorData().getText());
        movieRepository.updateGenre(movie.getId(), (MovieGenre) editMovieFrame.getGenreBox().getSelectedItem());
        movieRepository.updateDuration(movie.getId(), Integer.parseInt(editMovieFrame.getDurationData().getText()));
        movieRepository.updateCategory(movie.getId(),(MovieCategory) editMovieFrame.getCategoryBox().getSelectedItem());
    }

    public void updateView(){
        movieObservable.addObserver(tableMoviePanel);
        movieObservable.setMoviesDB(movieRepository.findAll());
    }
}



