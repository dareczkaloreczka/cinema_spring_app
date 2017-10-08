package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.MovieController;
import com.example.cinema_spring_app.model.Movie;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Observable;
import java.util.Observer;

@Component

public class TableMoviePanel extends JPanel implements Observer {

    private final MyTableModel model;
    private final JTable movieTable;
    private final MovieController movieController;

    public TableMoviePanel(MyTableModel model, MovieController movieController) {
        this.model = model;
        this.movieController = movieController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel nowPlaying = new JLabel("NOW PLAYING");
        this.model.addColumn("ID");
        this.model.addColumn("Title");
        this.model.addColumn("Genre");
        movieTable = new JTable(this.model);
        JScrollPane scrollPanePanel = new JScrollPane(movieTable);
        scrollPanePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(nowPlaying);
        add(scrollPanePanel);


        movieTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (getMovieTable().getSelectedRow() > -1) {
                    movieController.showSelected(movieController.getSelectedMovie());
                }
            }
        });

    }

    public void fillTheRow(String[] rowData) {
        getModel().addRow(rowData);
    }

    public int getSelectedIndex() {
        int id = -1;
        if (getMovieTable().getSelectedRow() > -1) {
            id = Integer.parseInt((String) getMovieTable().getModel().getValueAt(getMovieTable().getSelectedRow(), 0));
        }
        return id;
    }

    public void clearContent() {
        getModel().setRowCount(0);
    }

    public MyTableModel getModel() {
        return model;
    }


    public JTable getMovieTable() {
        return movieTable;
    }


    @Override
    public void update(Observable o, Object arg) {
        model.setRowCount(0);
        movieController.fillTheTable();

    }
}
