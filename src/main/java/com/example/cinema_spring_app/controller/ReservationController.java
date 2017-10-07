package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import com.example.cinema_spring_app.model.repo.ReservationRepository;
import com.example.cinema_spring_app.view.MakeReservationFrame;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final MakeReservationFrame makeReservationFrame;
    private final SeanceController seanceController;

    @Lazy
    public ReservationController(ReservationRepository reservationRepository, MakeReservationFrame makeReservationFrame, SeanceController seanceController) {
        this.reservationRepository = reservationRepository;
        this.makeReservationFrame = makeReservationFrame;
        this.seanceController = seanceController;
    }

    public void addReservation(String row, int seat){
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

    public void initializeSeatsPanel(){

        Seance seance = seanceController.getSelectedSeance();
        List<Reservation> bookedReservations = reservationRepository.findBySeance(seance);
            List<JButton> seats = makeReservationFrame.seatsFactory(seance.getHall().getNumberOfRows(), seance.getHall().getSeatsInRow());
            makeReservationFrame.getSeatsPanel().setLayout(new GridLayout(seance.getHall().getNumberOfRows(), seance.getHall().getSeatsInRow()));
            for (JButton seat : seats){
                String rowNo = seat.getText().substring(0,1);
                int seatNo = Integer.parseInt(seat.getText().substring(1));
                makeReservationFrame.getSeatsPanel().add(seat);
                for (Reservation r : bookedReservations){
                    if (r.getRow().equals(rowNo) && r.getSeat() == seatNo ){
                        seat.setBackground(Color.RED);
                        seat.setEnabled(false);
                    }
                }
                seat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeReservationFrame.setRowNo(rowNo);
                        makeReservationFrame.setSeatNo(seatNo);
                        seat.setBackground(Color.GREEN);
                        makeReservationFrame.getChosenSeat().setText("Your seat: " + rowNo + seatNo);
                    }
                });
            }
        }
    }