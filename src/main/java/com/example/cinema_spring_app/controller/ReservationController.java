package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import com.example.cinema_spring_app.model.repo.ReservationRepository;
import com.example.cinema_spring_app.view.MakeReservationFrame;
import com.example.cinema_spring_app.view.MgmtReservationFrame;
import com.example.cinema_spring_app.view.TableReservationPanel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.util.List;

@Component
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final MakeReservationFrame makeReservationFrame;
    private final SeanceController seanceController;
    private final TableReservationPanel tableReservationPanel;

    @Lazy
    public ReservationController(ReservationRepository reservationRepository, MakeReservationFrame makeReservationFrame, SeanceController seanceController, MgmtReservationFrame mgmtReservationFrame, TableReservationPanel tableReservationPanel) {
        this.reservationRepository = reservationRepository;
        this.makeReservationFrame = makeReservationFrame;
        this.seanceController = seanceController;
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
            tableReservationPanel.getModel().addRow(reservationData);
        }
    }

    public Reservation getSelectedReservation() {
        Reservation reservation = null;
        if (tableReservationPanel.getReservationsTable().getSelectedRow() > -1) {
            int id = Integer.parseInt((String) tableReservationPanel.getReservationsTable().getModel().getValueAt(tableReservationPanel.getReservationsTable().getSelectedRow(), 0));
            reservation = reservationRepository.findOne(id);
        }
        return reservation;
    }

    public void addReservation(String row, int seat) {
        Reservation reservation = new Reservation();
        reservation.setCustomerName(makeReservationFrame.getCustomerName().getText());
        reservation.setCustomerEmailAddress(makeReservationFrame.getCustomerEmailAddress().getText());
        reservation.setSeance(makeReservationFrame.getSeance());
        reservation.setRow(row);
        reservation.setSeat(seat);
        reservation.setTicketOption((TicketOption) makeReservationFrame.getTicketOptions().getSelectedItem());
        reservation.setPrice();
        reservationRepository.save(reservation);

    }

    public void removeReservation() {
        Reservation reservation = getSelectedReservation();
        reservationRepository.delete(reservation);

    }

    public void initializeSeatsPanel() {

        Seance seance = seanceController.getSelectedSeance();
        List<Reservation> bookedReservations = reservationRepository.findBySeance(seance);
        List<JButton> seats = makeReservationFrame.seatsFactory(seance.getHall().getNumberOfRows(), seance.getHall().getSeatsInRow());
        makeReservationFrame.getSeatsPanel().setLayout(new GridLayout(seance.getHall().getNumberOfRows(), seance.getHall().getSeatsInRow()));
        for (JButton seat : seats) {
            seat.setBackground(Color.CYAN);
            seat.setSize(new Dimension(5,5));
            seat.setFont(new Font("Arial", Font.PLAIN, 7));
            String rowNo = seat.getText().substring(0, 1);
            int seatNo = Integer.parseInt(seat.getText().substring(1));
            makeReservationFrame.getSeatsPanel().add(seat);
            for (Reservation r : bookedReservations) {
                if (r.getRow().equals(rowNo) && r.getSeat() == seatNo) {
                    seat.setBackground(Color.RED);
                    seat.setEnabled(false);
                }
            }
            seat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    makeReservationFrame.setRowNo(rowNo);
                    makeReservationFrame.setSeatNo(seatNo);
                    for  (JButton seat : seats) {
                        if(seat.getBackground().equals(Color.GREEN)){
                            seat.setBackground(Color.CYAN);
                        }
                    }
                    seat.setBackground(Color.GREEN);
                    makeReservationFrame.getChosenSeat().setText("Your seat: " + rowNo + seatNo);
                }
            });
        }
    }
}