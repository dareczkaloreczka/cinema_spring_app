package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.repo.ReservationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Observable;

@Component
public class ReservationObservable extends Observable{

    private final ReservationRepository reservationRepository;
    private List<Reservation> reservationsDB;

    public ReservationObservable(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        reservationsDB = reservationRepository.findAll();
    }

    public List<Reservation> getReservationsDB() {
        return reservationsDB;
    }

    public void setReservationsDB(List<Reservation> reservationsDB) {
        this.reservationsDB = reservationsDB;
        setChanged();
        notifyObservers();
    }
}
