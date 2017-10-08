package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

public class MovieControllerTest {

    MovieRepository movieRepositoryMock = Mockito.mock(MovieRepository.class);
    TableMoviePanel moviePanelMock = Mockito.mock(TableMoviePanel.class);
    DetailsMoviePanel detailsMoviePanelMock = Mockito.mock(DetailsMoviePanel.class);
    MovieObservable movieObservable = new MovieObservable(movieRepositoryMock);
    AddMovieFrame addMovieFrameMock = Mockito.mock(AddMovieFrame.class);
    EditMovieFrame editMovieFrameMock = Mockito.mock(EditMovieFrame.class);
    ShowMovieSeances showMovieSeancesMock = Mockito.mock(ShowMovieSeances.class);
    SeanceRepository seanceRepositoryMock = Mockito.mock(SeanceRepository.class);
    Movie testMovie = new Movie();

    @Before
    public void setUp() throws Exception {
        testMovie.setTitle("Test movie");
        testMovie.setYear(Date.valueOf("2017-03-10"));
        testMovie.setDuration(120);
        testMovie.setDirector("Tony Hawk");
        testMovie.setGenre(MovieGenre.DRAMA);
        testMovie.setAgeCategory(MovieCategory.OVER_15YO);
    }

    @Test
    public void showSelectedMovie() throws Exception {
        MovieController movieController = new MovieController(movieRepositoryMock, movieObservable,
                moviePanelMock, detailsMoviePanelMock, addMovieFrameMock, editMovieFrameMock,
                seanceRepositoryMock, showMovieSeancesMock);

      Mockito.when(movieRepositoryMock.findOne(anyInt())).thenReturn(testMovie);

      movieController.showSelected(testMovie);





    }

}