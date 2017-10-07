package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.ReservationController;
import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.TicketOption;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
//@Scope ("prototype")
public class MakeReservationFrame extends JDialog {

    private final SeanceController seanceController;
    private final ReservationController reservationController;
    private Seance seance;
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
        initializeSeatsPanel();
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

    private void initializeSeatsPanel(){
        if (seance != null){
      reservationController.initializeSeatsPanel();}
    }


    public SeanceController getSeanceController() {
        return seanceController;
    }

    public ReservationController getReservationController() {
        return reservationController;
    }

    public Seance getSeance() {
        return seance;
    }

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public void setCustomerName(JTextField customerName) {
        this.customerName = customerName;
    }

    public JTextField getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(JTextField customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public JComboBox<TicketOption> getTicketOptions() {
        return ticketOptions;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void setTicketOptions(JComboBox<TicketOption> ticketOptions) {
        this.ticketOptions = ticketOptions;
    }

    public JPanel getSeatsPanel() {
        return seatsPanel;
    }

    public void setSeatsPanel(JPanel seatsPanel) {
        this.seatsPanel = seatsPanel;
    }

    public JLabel getChosenSeat() {
        return chosenSeat;
    }

    public void setChosenSeat(JLabel chosenSeat) {
        this.chosenSeat = chosenSeat;
    }
}
