package com.example.cinema_spring_app.view;


import com.example.cinema_spring_app.controller.SeanceController;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.Observable;
import java.util.Observer;
@Component
public class TableSeancePanel extends JPanel implements Observer {

    private final  MyTableModel model;
    private JTable seanceTable;
    private final SeanceController seanceController;


    public TableSeancePanel(MyTableModel model, SeanceController seanceController) {
        this.model = model;
        this.seanceController = seanceController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel nowPlaying = new JLabel("REPERTOIRE");
        model.addColumn("ID");
        model.addColumn("Movie");
        model.addColumn("Date");
        model.addColumn("Time");
        seanceTable = new JTable(this.model);
        JScrollPane scrollPanePanel = new JScrollPane(seanceTable);
        scrollPanePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(nowPlaying);
        add(scrollPanePanel);

        seanceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (seanceTable.getSelectedRow() > -1) {
                   seanceController.showSelectedSeance();
                }
            }
        });

    }

    public  MyTableModel getModel() {
        return model;
    }

    public  JTable getSeanceTable() {
        return seanceTable;
    }

    public  void setSeanceTable(JTable seanceTable) {
        this.seanceTable = seanceTable;
    }

    @Override
    public void update(Observable o, Object arg) {
        model.setRowCount(0);
        seanceController.fillTheTable();

    }
}
