package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.Seance;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class DetailsSeancePanel extends JPanel {

    private JTextArea movieData;
    private JTextArea dateData;
    private JTextArea timeData;
    private JTextArea hallData;
    private final SeanceController seanceController;
    private final TableSeancePanel tableSeancePanel;

    public DetailsSeancePanel(SeanceController seanceController, TableSeancePanel tableSeancePanel) {
        this.seanceController = seanceController;
        this.tableSeancePanel = tableSeancePanel;
        init();
    }

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel details = new JLabel("DETAILS");
        JPanel movieDetails = new JPanel();
        movieDetails.setLayout(new GridLayout(3, 2));
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel("Movie : ");
        movieData = new JTextArea("_____________________");
        titlePane.add(title);
        titlePane.add(movieData);
        JPanel datePane = new JPanel();
        JLabel date = new JLabel("Date : ");
        dateData = new JTextArea("_____________________");
        datePane.add(date);
        datePane.add(dateData);
        JPanel timePane = new JPanel();
        JLabel time = new JLabel("Time : ");
        timeData = new JTextArea("_____________________");
        timePane.add(time);
        timePane.add(timeData);
        JPanel hallPane = new JPanel();
        JLabel hall = new JLabel("Hall No: ");
        hallData = new JTextArea("_____________________");
        hallPane.add(hall);
        hallPane.add(hallData);

        movieDetails.add(titlePane);
        movieDetails.add(datePane);
        movieDetails.add(timePane);
        movieDetails.add(hallPane);
        add(details);
        add(movieDetails);

        JPanel buttonPane = new JPanel();
        JButton add = new JButton("Add new seance");
        JButton edit = new JButton("Edit");
        JButton remove = new JButton("Remove");
        JButton makeReservation = new JButton("Make reservation");
        buttonPane.add(add);
        buttonPane.add(edit);
        buttonPane.add(remove);
        buttonPane.add(makeReservation);
        add(buttonPane);

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSeance();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSeance();
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSeance();

            }
        });
    }
    private void editSeance() {
        Seance seance = seanceController.getSelectedSeance();
     //   EditSeanceFrame editFrame = new EditSeanceFrame(seance);
      //  editFrame.setVisible(true);
    }

    private void addSeance(){
       // AddSeance addSeanceFrame =  new AddSeance();
      // addSeanceFrame.setVisible(true);
    }
    private void removeSeance(){
        seanceController.removeSeance();
    }


    public JTextArea getMovieData() {
        return movieData;
    }

    public JTextArea getDateData() {
        return dateData;
    }

    public JTextArea getTimeData() {
        return timeData;
    }

    public JTextArea getHallData() {
        return hallData;
    }


}
