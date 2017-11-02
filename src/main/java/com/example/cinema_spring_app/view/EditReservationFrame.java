package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.ReservationController;
import com.example.cinema_spring_app.model.Reservation;
import com.example.cinema_spring_app.model.TicketOption;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
@Component
public class EditReservationFrame extends JDialog {

    private JTextField customerNameData;
    private JTextField customerEmailData;
    private JComboBox<String>  rowData;
    private JComboBox<Integer> seatData;
    private JComboBox<TicketOption> ticketOptionData;

    private final ReservationController reservationController;


    @Lazy
    public EditReservationFrame(ReservationController reservationController) throws HeadlessException {
        this.reservationController = reservationController;
        init();
    }

    private void init() {
        setTitle("Edit reservation");
        setSize(500, 250);
        JPanel mainPanel = new JPanel();
        JPanel reservationDetails = new JPanel();
        reservationDetails.setLayout(new GridLayout(2, 2));
        JPanel customerNamePane = new JPanel();
        JLabel customerName = new JLabel("Customer Name: ");
        customerNameData = new JTextField(15);
        customerNamePane.add(customerName);
        customerNamePane.add(customerNameData);
        JPanel emailPane = new JPanel();
        JLabel customerEmail = new JLabel("Customer Email : ");
        customerEmailData = new JTextField(15);
        emailPane.add(customerEmail);
        emailPane.add(customerEmailData);
        JPanel seatPane = new JPanel();
        JLabel row = new JLabel("Seat : ");
        String[] possibleRows =  {"A", "B" , "C", "D", "E", "F", "G", "H", "I"};
        Integer[] possibleSeats =  {0,01,2,3,4,5,6,7,8,9};
       rowData = new JComboBox();
       seatData = new JComboBox();
        seatPane.add(row);
        seatPane.add(rowData);
        seatPane.add(seatData);
        JPanel ticketOptionPane = new JPanel();
        JLabel ticketOption = new JLabel("TicketOption: ");
        Set<TicketOption> optionSet = TicketOption.getOptions();
        ticketOptionData = new JComboBox(optionSet.toArray());
        ticketOptionPane.add(ticketOption);
        ticketOptionPane.add(ticketOptionData);
        reservationDetails.add(customerNamePane);
        reservationDetails.add(emailPane);
        reservationDetails.add(seatPane);
        reservationDetails.add(ticketOptionPane);
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        JPanel buttonPane = new JPanel();
        buttonPane.add(save);
        buttonPane.add(cancel);
        mainPanel.add(reservationDetails);
        mainPanel.add(buttonPane);
        getContentPane().add(mainPanel);



        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               reservationController.editReservation(reservationController.getSelectedReservation());
               //reservationController.updateView();
                setVisible(false);
            }
        });
    }

    public Object[] getReservationData() {
        Object[] reservationData = {
                customerNameData.getText(),
                customerEmailData.getText(),
                rowData.getSelectedItem(),
                seatData.getSelectedItem(),
                ticketOptionData.getSelectedItem()};
        return reservationData;
    }

    public void showEditedReservation(Reservation reservation) {
        customerNameData.setText(reservation.getCustomerName());
        customerEmailData.setText(reservation.getCustomerEmailAddress());
        rowData.setSelectedItem(reservation.getRow());
        seatData.setSelectedItem(reservation.getSeat());
        ticketOptionData.setSelectedItem(reservation.getTicketOption());

    }

    public void initRowBox(Reservation reservation){
        String[] possibleRows =  {"A", "B" , "C", "D", "E", "F", "G", "H", "I"};
        int scope = reservation.getSeance().getHall().getNumberOfRows();
        rowData.removeAllItems();
        for (int i = 0; i <scope ; i++) {
            rowData.addItem(possibleRows[i]);
        }
    }
    public void initSeatBox(Reservation reservation){
        int scope = reservation.getSeance().getHall().getSeatsInRow();
        seatData.removeAllItems();
        for (int i = 0; i <scope ; i++) {
            seatData.addItem(i);
        }
    }
}
