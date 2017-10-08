package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.Movie;
import org.springframework.stereotype.Component;
import javax.swing.*;
@Component
public class ShowMovieSeances extends JDialog {

    private final SeanceController seanceController;
    private final MyTableModel model;
    private JTable selectedMovieSeancesTable;
    private Movie movie;

    public ShowMovieSeances(SeanceController seanceController, MyTableModel model) {
        this.seanceController = seanceController;
        this.model = model;
        selectedMovieSeancesTable = new JTable(model);
        setSize(500, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JLabel nowPlaying = new JLabel("SEANCES");
        model.addColumn("ID");
        model.addColumn("Movie");
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("Hall No");
        JScrollPane scrollPanePanel = new JScrollPane(selectedMovieSeancesTable);
        scrollPanePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(nowPlaying);
        add(scrollPanePanel);
    }

    public void fillTheRow(String[] rowData) {
        getModel().addRow(rowData);
    }
    public void clearContent(){
        getModel().setRowCount(0);
    }
    public MyTableModel getModel() {
        return model;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
