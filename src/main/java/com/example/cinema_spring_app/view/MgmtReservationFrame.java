package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.ReservationController;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class MgmtReservationFrame extends JDialog {

    private final TableReservationPanel tableReservationPanel;
    private final ReservationController reservationController;

    public MgmtReservationFrame(TableReservationPanel tableReservationPanel, ReservationController reservationController){
        this.tableReservationPanel = tableReservationPanel;
        this.reservationController = reservationController;
        init();
    }

    private void init() {
        setTitle("Reservations");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(550, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(tableReservationPanel);
        JPanel buttonPane = new JPanel();
        JButton edit = new JButton("Edit");
        JButton remove = new JButton("Remove");
        buttonPane.add(edit);
        buttonPane.add(remove);
        add(buttonPane);
        reservationController.fillTheTable();

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservationController.removeReservation(reservationController.getSelectedReservation());
            }
        });

    }


}
