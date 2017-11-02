package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import com.example.cinema_spring_app.model.repo.ReservationRepository;
import com.example.cinema_spring_app.view.EditReservationFrame;
import com.example.cinema_spring_app.view.MakeReservationFrame;
import com.example.cinema_spring_app.view.TableReservationPanel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final MakeReservationFrame makeReservationFrame;
    private final TableReservationPanel tableReservationPanel;
    private final EditReservationFrame editReservationFrame;
    private final ReservationObservable reservationObservable;

    @Lazy
    public ReservationController(ReservationRepository reservationRepository, MakeReservationFrame makeReservationFrame,
                                 TableReservationPanel tableReservationPanel, EditReservationFrame editReservationFrame,
                                 ReservationObservable reservationObservable) {
        this.reservationRepository = reservationRepository;
        this.makeReservationFrame = makeReservationFrame;
        this.tableReservationPanel = tableReservationPanel;
        this.editReservationFrame = editReservationFrame;
        this.reservationObservable = reservationObservable;
    }

    public void fillTheTable() {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation r : reservations) {
            String[] reservationData = {
                    String.valueOf(r.getId()),
                    r.getSeance().getMovie().getTitle(),
                    r.getCustomerName(),
                    r.getCustomerEmailAddress(),
                    r.getSeance().getDate().toString(),
                    r.getSeance().getTime().toString(),
                    r.getRow() + r.getSeat(),
                    r.getPrice().toString()
            };
            tableReservationPanel.fillTheRow(reservationData);
        }
    }

    public Reservation getSelectedReservation() {
        return reservationRepository.findOne(tableReservationPanel.getSelectedIndex());
    }

    public void addReservation(Reservation reservation, String row, int seat) {
        makeReservationFrame.setFieldsForReservation(reservation, row, seat);
        reservationRepository.save(reservation);
        updateView();

    }

    public void showEditedReservation(Reservation reservation) {
        editReservationFrame.showEditedReservation(reservation);
    }

    public void editReservation(Reservation reservation) {
        reservationRepository.updateCustomerName(reservation.getId(), (String) editReservationFrame.getReservationData()[0]);
        reservationRepository.updateCustomerEmailAddress(reservation.getId(), (String) editReservationFrame.getReservationData()[1]);
        reservationRepository.updateRow(reservation.getId(), (String) editReservationFrame.getReservationData()[2]);
        reservationRepository.updateSeat(reservation.getId(), (Integer) editReservationFrame.getReservationData()[3]);
        reservationRepository.updateTicketOption(reservation.getId(), (TicketOption) editReservationFrame.getReservationData()[4]);
        updateView();

    }

    public void removeReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
        updateView();
    }

    public void initializeSeatsPanel(Seance seance) {
        makeReservationFrame.initializeSeatsPanel(seance);
    }

    public void initializeRowBox(Reservation reservation) {
        editReservationFrame.initRowBox(reservation);
    }

    public void initializeSeatBox(Reservation reservation) {
        editReservationFrame.initSeatBox(reservation);
    }
    public void updateView(){
        reservationObservable.addObserver(tableReservationPanel);
        reservationObservable.setReservationsDB(reservationRepository.findAll());
    }
}