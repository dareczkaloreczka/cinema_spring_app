package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.CinemaHall;
import com.example.cinema_spring_app.model.Seance;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Component
public class EditSeanceFrame extends JDialog {


    private JTextField movieData;
    private JTextField dateData;
    private JTextField timeData;
    private JComboBox<CinemaHall> hallData;
    private final SeanceController seanceController;
    private Seance seance;
    public EditSeanceFrame(SeanceController seanceController) throws HeadlessException {
        this.seanceController = seanceController;
        init();
    }

    private void init() {
        setTitle("Edit seance");
        setSize(500, 250);
        JPanel mainPanel = new JPanel();
        JPanel seanceDetails = new JPanel();
        seanceDetails.setLayout(new GridLayout(3, 2));
        seanceDetails.setLayout(new GridLayout(3, 2));
        JPanel moviePane = new JPanel();
        JLabel movie = new JLabel("Movie: ");
        movieData = new JTextField(15);
        moviePane.add(movie);
        moviePane.add(movieData);
        JPanel datePane = new JPanel();
        JLabel date = new JLabel("Date : ");
        dateData = new JTextField(15);
        datePane.add(date);
        datePane.add(dateData);
        JPanel timePane = new JPanel();
        JLabel time = new JLabel("Time : ");
        timeData = new JTextField(15);
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
                //seance = seanceController.getSelectedSeance();
                seanceController.editSeance(seance);
                seanceController.updateView();
                setVisible(false);
            }
        });

    }

    public JTextField getMovieData() {
        return movieData;
    }

    public void setMovieData(JTextField movieData) {
        this.movieData = movieData;
    }

    public JTextField getDateData() {
        return dateData;
    }

    public void setDateData(JTextField dateData) {
        this.dateData = dateData;
    }

    public JTextField getTimeData() {
        return timeData;
    }

    public void setTimeData(JTextField timeData) {
        this.timeData = timeData;
    }

    public JComboBox<CinemaHall> getHallData() {
        return hallData;
    }

    public void setHallData(JComboBox<CinemaHall> hallData) {
        this.hallData = hallData;
    }

    public SeanceController getSeanceController() {
        return seanceController;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }
}
