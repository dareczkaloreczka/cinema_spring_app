package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.CinemaHall;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AddSeanceFrame extends JDialog {


    private JTextField movieData;
    private JTextField dateData;
    private JTextField timeData;
    private JComboBox<CinemaHall> hallData;
    private final SeanceController seanceController;
    private final TableSeancePanel tableSeancePanel;

    public AddSeanceFrame(SeanceController seanceController, TableSeancePanel tableSeancePanel) throws HeadlessException {
        this.seanceController = seanceController;
        this.tableSeancePanel = tableSeancePanel;
        init();
    }

    private void init() {
        setTitle("Add seance");
        setSize(500, 250);
        JPanel mainPanel = new JPanel();
        JPanel seanceDetails = new JPanel();
        seanceDetails.setLayout(new GridLayout(3, 2));
        JPanel moviePane = new JPanel();
        JLabel movie = new JLabel("Movie: ");
        movieData = new JTextField("______________");
        moviePane.add(movie);
        moviePane.add(movieData);
        JPanel datePane = new JPanel();
        JLabel date = new JLabel("Date : ");
        dateData = new JTextField("_____________________");
        datePane.add(date);
        datePane.add(dateData);
        JPanel timePane = new JPanel();
        JLabel time = new JLabel("Time : ");
        timeData = new JTextField("_____________________");
        timePane.add(time);
        timePane.add(timeData);
        JPanel hallPane = new JPanel();
        JLabel hall = new JLabel("Hall No: ");
        hallData = new JComboBox();
        //List<CinemaHall> halls = hallPresenter.getDAO.getAllHalls();
        // = new JComboBox(halls.toArray());
        hallPane.add(hall);
        hallPane.add(hallData);
        seanceDetails.add(moviePane);
        seanceDetails.add(datePane);
        seanceDetails.add(timePane);
        seanceDetails.add(hallPane);
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        JPanel buttonPane = new JPanel();
        buttonPane.add(save);
        buttonPane.add(cancel);
        mainPanel.add(seanceDetails);
        mainPanel.add(buttonPane);
        getContentPane().add(mainPanel);


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seanceController.addSeance();
                setVisible(false);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }


    public JTextField getMovieData() {
        return movieData;
    }

    public JTextField getDateData() {
        return dateData;
    }

    public JTextField getTimeData() {
        return timeData;
    }

    public JComboBox<CinemaHall> getHallData() {
        return hallData;
    }


}
