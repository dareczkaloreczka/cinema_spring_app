package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.repo.ReservationRepository;
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

    @Lazy
    public ReservationController(ReservationRepository reservationRepository, MakeReservationFrame makeReservationFrame, TableReservationPanel tableReservationPanel) {
        this.reservationRepository = reservationRepository;
        this.makeReservationFrame = makeReservationFrame;
        this.tableReservationPanel = tableReservationPanel;
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

    public void addReservation(String row, int seat) {
        Reservation reservation = new Reservation();
        makeReservationFrame.setFieldsForReservation(reservation,row,seat);
        reservationRepository.save(reservation);

    }

    public void removeReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public List<Reservation> getBySeance(Seance seance){
        return reservationRepository.findBySeance(seance);
    }

    public void initializeSeatsPanel(Seance seance) {
        makeReservationFrame.initializeSeatsPanel(seance);
    }
}