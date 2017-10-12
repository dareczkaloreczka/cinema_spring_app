package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.ReservationController;
import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MakeReservationFrame extends JDialog {

    private final SeanceController seanceController;
    private final ReservationController reservationController;
    private String rowNo;
    private int seatNo;
    private JTextField customerName;
    private JTextField customerEmailAddress;
    private JComboBox<TicketOption> ticketOptions;
    private JPanel seatsPanel;
    private JLabel chosenSeat;

    public MakeReservationFrame(SeanceController seanceController, ReservationController reservationController) {
        this.seanceController = seanceController;
        this.reservationController = reservationController;
        setSize(700, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        seatsPanel = new JPanel();
        JLabel label = new JLabel("CHOOSE YOUR SEAT");
        JPanel customerPane = new JPanel();
        JLabel name = new JLabel("Name: ");
        customerName = new JTextField(15);
        customerPane.add(name);
        customerPane.add(customerName);
        JLabel email = new JLabel("E-mail: ");
        customerEmailAddress = new JTextField(15);
        customerPane.add(email);
        customerPane.add(customerEmailAddress);
        JLabel option = new JLabel("Ticket option: ");
        Set<TicketOption> options = TicketOption.getOptions();
        ticketOptions = new JComboBox(options.toArray());
        chosenSeat = new JLabel("Your seat: ");
        customerPane.add(option);
        customerPane.add(ticketOptions);
        customerPane.add(chosenSeat);
        JButton submit = new JButton("Submit");
        add(label);
        add(seatsPanel);
        add(customerPane);
        add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservationController.addReservation(rowNo, seatNo);
                setVisible(false);
            }
        });

    }

    public List<JButton> seatsFactory(int nOfRows, int nOfSeats){
        List<JButton> seats = new ArrayList<>();
        String rows = "ABCDEFGHIJKLMNOPRSTUWXYZ";
        for (int i = 0; i <nOfRows ; i++) {
            for (int j = 0; j <nOfSeats ; j++) {
                JButton seat = new JButton();
                char r = rows.charAt(i);
                int s = j;
                seat.setText(r + String.valueOf(j));
                seats.add(seat);
            }
        }
        return seats;
    }

    public void initializeSeatsPanel(Seance seance) {
        seatsPanel.removeAll();
        //List<Reservation> bookedReservations = reservationController.getBySeance(seance);
        List<Reservation> bookedReservations = seance.getReservations();
        List<JButton> seats = seatsFactory(seance.getHall().getNumberOfRows(), seance.getHall().getSeatsInRow());
        seatsPanel.setLayout(new GridLayout(seance.getHall().getNumberOfRows(), seance.getHall().getSeatsInRow()));
        for (JButton seat : seats) {
            seat.setBackground(Color.CYAN);
            seat.setSize(new Dimension(5, 5));
            seat.setFont(new Font("Arial", Font.PLAIN, 7));
            String rowNo = seat.getText().substring(0, 1);
            int seatNo = Integer.parseInt(seat.getText().substring(1));
            seatsPanel.add(seat);
            for (Reservation r : bookedReservations) {
                if (r.getRow().equals(rowNo) && r.getSeat() == seatNo) {
                    seat.setBackground(Color.RED);
                    seat.setEnabled(false);
                }
            }
            seat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setRowNo(rowNo);
                    setSeatNo(seatNo);
                    for (JButton seat : seats) {
                        if (seat.getBackground().equals(Color.GREEN)) {
                            seat.setBackground(Color.CYAN);
                        }
                    }
                    seat.setBackground(Color.GREEN);
                    chosenSeat.setText("Your seat: " + rowNo + seatNo);
                }
            });
        }
    }

    public void setFieldsForReservation(Reservation reservation, String row, int seat){
        reservation.setCustomerName(customerName.getText());
        reservation.setCustomerEmailAddress(customerEmailAddress.getText());
        reservation.setSeance(seanceController.getSelectedSeance());
        reservation.setRow(row);
        reservation.setSeat(seat);
        reservation.setTicketOption((TicketOption) ticketOptions.getSelectedItem());
        reservation.setPrice();
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

}
