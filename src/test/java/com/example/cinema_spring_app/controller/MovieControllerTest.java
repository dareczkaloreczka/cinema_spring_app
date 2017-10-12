package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.CinemaHall;
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
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

public class MovieControllerTest {

    MovieRepository movieRepositoryMock = Mockito.mock(MovieRepository.class);
    TableMoviePanel moviePanelMock = Mockito.mock(TableMoviePanel.class);
    DetailsMoviePanel detailsMoviePanelMock = Mockito.mock(DetailsMoviePanel.class);
    MovieObservable movieObservable = Mockito.mock(MovieObservable.class);
    AddMovieFrame addMovieFrameMock = Mockito.mock(AddMovieFrame.class);
    EditMovieFrame editMovieFrameMock = Mockito.mock(EditMovieFrame.class);
    ShowMovieSeances showMovieSeancesMock = Mockito.mock(ShowMovieSeances.class);
    Movie testMovie = new Movie();
    List<Seance> seances = new ArrayList<>();
    Seance seance = new Seance();
    MovieController movieController;


    @Before
    public void setUp() throws Exception {
        testMovie.setTitle("Test movie");
        testMovie.setYear(Date.valueOf("2017-03-10"));
        testMovie.setDuration(120);
        testMovie.setDirector("Tony Hawk");
        testMovie.setGenre(MovieGenre.DRAMA);
        testMovie.setAgeCategory(MovieCategory.OVER_15YO);
        seance.setMovie(testMovie);
        seance.setDate(Date.valueOf("2014-02-02"));
        seance.setTime(Time.valueOf("12:00:00"));
        seance.setHall(new CinemaHall());
        seance.setId(5);
        seances.add(seance);
        testMovie.setSeances(seances);
        movieController = new MovieController(movieRepositoryMock, movieObservable,
                moviePanelMock, detailsMoviePanelMock, addMovieFrameMock, editMovieFrameMock,
                showMovieSeancesMock);
    }

    @Test
    public void fillTheTable() throws Exception {
        List<Movie> movies = new ArrayList<>();
        movies.add(testMovie);
        Mockito.when(movieRepositoryMock.findAll()).thenReturn(movies);
        movieController.fillTheTable();
        Mockito.verify(movieRepositoryMock, Mockito.atLeastOnce()).findAll();
        String[] data = {"null", "Test movie", "DRAMA"};
        Mockito.verify(moviePanelMock, Mockito.times(1)).fillTheRow(data);

    }

    @Test
    public void fillTheTableForSelectedMovie() throws Exception {
        movieController.fillTheTableForSelectedMovie(testMovie);
        Mockito.verify(showMovieSeancesMock, Mockito.times(1)).clearContent();
        String[] data = {"5", "Test movie", "2014-02-02", "12:00:00", "0"};
        Mockito.verify(showMovieSeancesMock, Mockito.times(1)).fillTheRow(data);
    }

    @Test
    public void getAllTheMovies() throws Exception {
        movieController.getAllTheMovies();
        Mockito.verify(movieRepositoryMock, Mockito.atLeastOnce()).findAll();
    }

    @Test
    public void showSelectedMovie() throws Exception {
      movieController.showSelected(testMovie);
      Mockito.verify(detailsMoviePanelMock).setMovieData(testMovie);
    }

    @Test
    public void getSelectedMovie() throws Exception {
        movieController.getSelectedMovie();
        Mockito.verify(movieRepositoryMock).findOne(moviePanelMock.getSelectedIndex());
        Mockito.verify(moviePanelMock, Mockito.atLeastOnce()).getSelectedIndex();
    }

    @Test
    public void addMovie() throws Exception {
        movieController.addMovie(testMovie);
        Mockito.verify(addMovieFrameMock).setFieldsForMovie(testMovie);
        Mockito.verify(movieRepositoryMock, Mockito.atLeastOnce()).save(testMovie);
    }

    @Test
    public void removeMovie() throws Exception {
        movieController.removeMovie(testMovie);
        Mockito.verify(movieRepositoryMock, Mockito.atLeastOnce()).delete(testMovie);
    }

    @Test
    public void showEditedMovie() throws Exception {
      movieController.showEditedMovie(testMovie);
      Mockito.verify(editMovieFrameMock).setFields(testMovie);
    }

    @Test
    public void editMovie() throws Exception {
        String[] movieData = {"TestMovie","2015-05-05", "Unknown", "DRAMA", "155", "NO_RESTRICTIONS"};
        Mockito.when(editMovieFrameMock.getMovieData()).thenReturn(movieData);
        movieController.editMovie(testMovie);
        Mockito.verify(movieRepositoryMock).updateTitle(testMovie.getId(), editMovieFrameMock.getMovieData()[0]);
        Mockito.verify(movieRepositoryMock).updateYear(testMovie.getId(), Date.valueOf(editMovieFrameMock.getMovieData()[1]));
        Mockito.verify(movieRepositoryMock).updateDirector(testMovie.getId(), editMovieFrameMock.getMovieData()[2]);
        Mockito.verify(movieRepositoryMock).updateGenre(testMovie.getId(), MovieGenre.valueOf(editMovieFrameMock.getMovieData()[3]));
        Mockito.verify(movieRepositoryMock).updateDuration(testMovie.getId(), Integer.parseInt(editMovieFrameMock.getMovieData()[4]));
        Mockito.verify(movieRepositoryMock).updateCategory(testMovie.getId(),MovieCategory.valueOf(editMovieFrameMock.getMovieData()[5]));
    }

    @Test
    public void updateView() throws Exception {
        movieController.updateView();
        Mockito.verify(movieObservable).addObserver(moviePanelMock);
        Mockito.verify(movieObservable).setMoviesDB(movieRepositoryMock.findAll());
    }
}