package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
import com.example.cinema_spring_app.model.repo.MovieRepository;
import com.example.cinema_spring_app.view.AddMovieFrame;
import com.example.cinema_spring_app.view.DetailsPanel;
import com.example.cinema_spring_app.view.EditFrame;
import com.example.cinema_spring_app.view.TablePanel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieObservable movieObservable;
    private  final TablePanel tablePanel;
    private final DetailsPanel detailsPanel;
    private final AddMovieFrame addMovieFrame;
    private final EditFrame editFrame;

    @Lazy
    public MovieController(MovieRepository movieRepository, MovieObservable movieObservable, TablePanel tablePanel, DetailsPanel detailsPanel, AddMovieFrame addMovieFrame, EditFrame editFrame) {
        this.movieRepository = movieRepository;
        this.movieObservable = movieObservable;
        this.tablePanel = tablePanel;
        this.detailsPanel = detailsPanel;
        this.addMovieFrame = addMovieFrame;
        this.editFrame = editFrame;
    }

    public void fillTheTable() {
        List<Movie> movieList = movieRepository.findAll();
        for (Movie m : movieList) {
            String[] movieData = {String.valueOf(m.getId()), m.getTitle(), String.valueOf(m.getGenre())};
            tablePanel.getModel().addRow(movieData);
        }
    }
    public Movie getSelectedMovie() {
        Movie movie = null;
        if (tablePanel.getMovieTable().getSelectedRow() > -1) {
            int id = Integer.parseInt((String) tablePanel.getMovieTable().getModel().getValueAt(tablePanel.getMovieTable().getSelectedRow(), 0));
            movie = movieRepository.findOne(id);
        }
        return movie;
    }
    public void showSelected() {
        Movie movie = getSelectedMovie();
        detailsPanel.getTitleData().setText(movie.getTitle());
        detailsPanel.getPremiereData().setText(String.valueOf(movie.getYear()));
        detailsPanel.getDirectorData().setText(movie.getDirector());
        detailsPanel.getGenreData().setText(movie.getGenre().toString());
        detailsPanel.getDurationData().setText(String.valueOf(movie.getDuration()));
        detailsPanel.getAgeData().setText(movie.getAgeCategory().toString());
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
        editFrame.getTitleData().setText(movie.getTitle());
        editFrame.getPremiereData().setText(movie.getYear().toString());
        editFrame.getDirectorData().setText(movie.getDirector());
        editFrame.getDurationData().setText(String.valueOf(movie.getDuration()));
    }

    public void editMovie(Movie movie){
       /* movie.setTitle(editFrame.getTitleData().getText());
        movie.setYear(Date.valueOf(editFrame.getPremiereData().getText()));
        movie.setDirector(editFrame.getDirectorData().getText());
        movie.setGenre((MovieGenre) editFrame.getGenreBox().getSelectedItem());
        movie.setDuration(Integer.parseInt(editFrame.getDurationData().getText()));
        movie.setAgeCategory((MovieCategory) editFrame.getCategoryBox().getSelectedItem());*/

        movieRepository.updateTitle(movie.getId(),editFrame.getTitleData().getText());
        movieRepository.updateYear(movie.getId(), Date.valueOf(editFrame.getPremiereData().getText()));
        movieRepository.updateDirector(movie.getId(), editFrame.getDirectorData().getText());
        movieRepository.updateGenre(movie.getId(), (MovieGenre) editFrame.getGenreBox().getSelectedItem());
        movieRepository.updateDuration(movie.getId(), Integer.parseInt(editFrame.getDurationData().getText()));
        movieRepository.updateCategory(movie.getId(),(MovieCategory) editFrame.getCategoryBox().getSelectedItem());
    }

    public void updateView(){
        movieObservable.addObserver(tablePanel);
        movieObservable.setMoviesDB(movieRepository.findAll());
    }
}



