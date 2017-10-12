package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import com.example.cinema_spring_app.model.repo.ReservationRepository;
import com.example.cinema_spring_app.view.MakeReservationFrame;
import com.example.cinema_spring_app.view.TableReservationPanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReservationControllerTest {

    ReservationRepository reservationRepositoryMock = Mockito.mock(ReservationRepository.class);
    MakeReservationFrame makeReservationFrameMock = Mockito.mock(MakeReservationFrame.class);
    TableReservationPanel tableReservationPanelMock = Mockito.mock(TableReservationPanel.class);
    ReservationController reservationController;
    Reservation testReservation = new Reservation();
    Seance seance = new Seance();
    Movie movie = new Movie();
    @Before
    public void setUp() throws Exception {
        reservationController = new ReservationController(reservationRepositoryMock, makeReservationFrameMock, tableReservationPanelMock);
        movie.setTitle("TestMovie");
        seance.setMovie(movie);
        seance.setDate(Date.valueOf("2014-01-01"));
        seance.setTime(Time.valueOf("10:00:00"));
        testReservation.setSeance(seance);
        testReservation.setCustomerName("Kowalski");
        testReservation.setCustomerEmailAddress("kow@onet.pl");
        testReservation.setTicketOption(TicketOption.STUDENT);
        testReservation.setSeat(3);
        testReservation.setRow("A");
        testReservation.setId(1);
        testReservation.setPrice();
    }

    @Test
    public void fillTheTable() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(testReservation);
        Mockito.when(reservationRepositoryMock.findAll()).thenReturn(reservations);
        reservationController.fillTheTable();
        Mockito.verify(reservationRepositoryMock, Mockito.atLeastOnce()).findAll();
        String[] data = {"1", "TestMovie", "Kowalski", "kow@onet.pl", "2014-01-01", "10:00:00", "A3", "13.00"};
        Mockito.verify(tableReservationPanelMock, Mockito.times(1)).fillTheRow(data);
    }

    @Test
    public void getSelectedReservation() throws Exception {
        reservationController.getSelectedReservation();
        Mockito.verify(reservationRepositoryMock).findOne(tableReservationPanelMock.getSelectedIndex());
        Mockito.verify(tableReservationPanelMock, Mockito.atLeastOnce()).getSelectedIndex();
    }

    @Test
    public void addReservation() throws Exception {
        reservationController.addReservation(testReservation, "A", 4);
        Mockito.verify(makeReservationFrameMock).setFieldsForReservation(testReservation, "A", 4);
        Mockito.verify(reservationRepositoryMock, Mockito.atLeastOnce()).save(testReservation);
    }

    @Test
    public void removeReservation() throws Exception {
        reservationController.removeReservation(testReservation);
        Mockito.verify(reservationRepositoryMock, Mockito.atLeastOnce()).delete(testReservation);

    }

    @Test
    public void initializeSeatsPanel() throws Exception {
        reservationController.initializeSeatsPanel(seance);
        Mockito.verify(makeReservationFrameMock).initializeSeatsPanel(seance);
    }

}