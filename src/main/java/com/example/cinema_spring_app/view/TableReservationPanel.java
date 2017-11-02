package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.ReservationController;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

@Component
public class TableReservationPanel extends JPanel implements Observer {

    private final MyTableModel model;
    private JTable reservationsTable;
    private final ReservationController reservationController;

    public TableReservationPanel(MyTableModel model, ReservationController reservationController) {
        this.model = model;
        this.reservationController = reservationController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel reservations = new JLabel("RESERVATIONS");
        this.model.addColumn("ID");
        this.model.addColumn("MOVIE");
        this.model.addColumn("CUSTOMER NAME");
        this.model.addColumn("CUSTOMER E-MAIL");
        this.model.addColumn("DATE");
        this.model.addColumn("TIME");
        this.model.addColumn("SEAT");
        this.model.addColumn("PRICE");
        reservationsTable = new JTable( this.model);
        JScrollPane scrollPanePanel = new JScrollPane(reservationsTable);
        scrollPanePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(reservations);
        add(scrollPanePanel);

    }
    public void fillTheRow(String[] rowData) {
        model.addRow(rowData);
    }
    public int getSelectedIndex() {
        int id = -1;
        if (reservationsTable.getSelectedRow() > -1) {
            id = Integer.parseInt((String) reservationsTable.getModel().getValueAt(reservationsTable.getSelectedRow(), 0));
        }
        return id;
    }
    public MyTableModel getModel() {
        return model;
    }

    @Override
    public void update(Observable o, Object arg) {
        model.setRowCount(0);
        reservationController.fillTheTable();

    }
}