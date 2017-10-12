package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.CinemaHall;
import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.repo.SeanceRepository;
import com.example.cinema_spring_app.view.AddSeanceFrame;
import com.example.cinema_spring_app.view.DetailsSeancePanel;
import com.example.cinema_spring_app.view.EditSeanceFrame;
import com.example.cinema_spring_app.view.TableSeancePanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SeanceControllerTest {

    SeanceRepository seanceRepositoryMock = Mockito.mock(SeanceRepository.class);
    TableSeancePanel tableSeancePanelMock = Mockito.mock(TableSeancePanel.class);
    DetailsSeancePanel detailsSeancePanelMock = Mockito.mock(DetailsSeancePanel.class);
    AddSeanceFrame addSeanceFrameMock = Mockito.mock(AddSeanceFrame.class);
    EditSeanceFrame editSeanceFrameMock = Mockito.mock(EditSeanceFrame.class);
    SeanceObservable seanceObservableMock = Mockito.mock(SeanceObservable.class);
    Seance testSeance = new Seance();
    Movie movie;
    CinemaHall hall;
    SeanceController seanceController;
    @Before
    public void setUp() throws Exception {
        movie = new Movie();
        movie.setTitle("TestMovie");
        hall = new CinemaHall();
        hall.setId(1);
        testSeance.setId(1);
        testSeance.setMovie(movie);
        testSeance.setDate(Date.valueOf("2015-02-02"));
        testSeance.setTime(Time.valueOf("22:00:00"));
        testSeance.setHall(hall);
        seanceController = new SeanceController(seanceRepositoryMock, tableSeancePanelMock, detailsSeancePanelMock,
                addSeanceFrameMock, editSeanceFrameMock, seanceObservableMock);
    }

    @Test
    public void fillTheTable() throws Exception {
        List<Seance> seances = new ArrayList<>();
        seances.add(testSeance);
        Mockito.when(seanceRepositoryMock.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "date"),
                new Sort.Order(Sort.Direction.ASC, "time")))).thenReturn(seances);
        seanceController.fillTheTable();
        Mockito.verify(seanceRepositoryMock, Mockito.atLeastOnce()).findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "date"),
                new Sort.Order(Sort.Direction.ASC, "time")));
        String[] data = {"1", "TestMovie","2015-02-02", "22:00:00"};
        Mockito.verify(tableSeancePanelMock, Mockito.times(1)).fillTheRow(data);
    }

    @Test
    public void getSelectedSeance() throws Exception {
        seanceController.getSelectedSeance();
        Mockito.verify(seanceRepositoryMock).findOne(tableSeancePanelMock.getSelectedIndex());
        Mockito.verify(tableSeancePanelMock, Mockito.atLeastOnce()).getSelectedIndex();
    }

    @Test
    public void showSelectedSeance() throws Exception {
        seanceController.showSelectedSeance(testSeance);
        Mockito.verify(detailsSeancePanelMock).setSeanceData(testSeance);
    }

    @Test
    public void addSeance() throws Exception {
        seanceController.addSeance(testSeance);
        Mockito.verify(addSeanceFrameMock).setFieldsForSeance(testSeance);
        Mockito.verify(seanceRepositoryMock, Mockito.atLeastOnce()).save(testSeance);
    }

    @Test
    public void removeSeance() throws Exception {
        seanceController.removeSeance(testSeance);
        Mockito.verify(seanceRepositoryMock, Mockito.atLeastOnce()).delete(testSeance);

    }

    @Test
    public void editSeance() throws Exception {
        Object[] seanceData = {movie, Date.valueOf("2015-02-02"), Time.valueOf("22:00:00"), hall};
        Mockito.when(editSeanceFrameMock.getSeanceData()).thenReturn(seanceData);
        seanceController.editSeance(testSeance);
        Mockito.verify(seanceRepositoryMock).updateMovie(testSeance.getId(), (Movie) editSeanceFrameMock.getSeanceData()[0]);
        Mockito.verify(seanceRepositoryMock).updateDate(testSeance.getId(), (Date)(editSeanceFrameMock.getSeanceData()[1]));
        Mockito.verify(seanceRepositoryMock).updateTime(testSeance.getId(), (Time) (editSeanceFrameMock.getSeanceData()[2]));
        Mockito.verify(seanceRepositoryMock).updateHall(testSeance.getId(), (CinemaHall) (editSeanceFrameMock.getSeanceData()[3]));
    }

    @Test
    public void showEditedSeance() throws Exception {
        seanceController.showEditedSeance(testSeance);
        Mockito.verify(editSeanceFrameMock).showEditedSeance(testSeance);
    }

    @Test
    public void updateView() throws Exception {
        seanceController.updateView();
        Mockito.verify(seanceObservableMock).addObserver(tableSeancePanelMock);
        Mockito.verify(seanceObservableMock).setSeancesDB(seanceRepositoryMock.findAll());
    }

}