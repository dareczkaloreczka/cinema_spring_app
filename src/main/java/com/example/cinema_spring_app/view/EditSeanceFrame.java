package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.HallController;
import com.example.cinema_spring_app.controller.MovieController;
import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.CinemaHall;
import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.Seance;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class EditSeanceFrame extends JDialog {

    private JComboBox<Movie> movieData;
    private JComboBox<Integer> year;
    private JComboBox<Integer> month;
    private JComboBox<Integer> day;
    private JComboBox<Integer> hour;
    private JComboBox<Integer> minute;
    private JComboBox<CinemaHall> hallData;
    private final SeanceController seanceController;
    private final HallController hallController;
    private final MovieController movieController;


    public EditSeanceFrame(SeanceController seanceController, HallController hallController, MovieController movieController) throws HeadlessException {
        this.seanceController = seanceController;
        this.hallController = hallController;
        this.movieController = movieController;
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
        movieData = new JComboBox(movieController.getAllTheMovies().toArray());
        moviePane.add(movie);
        moviePane.add(movieData);
        JPanel datePane = new JPanel();
        JLabel date = new JLabel("Date : ");
        year = new JComboBox<>(initYearBox());
        month = new JComboBox<>(initMonthBox());
        day = new JComboBox<>(initDayBox());
        datePane.add(date);
        datePane.add(day);
        datePane.add(month);
        datePane.add(year);
        JPanel timePane = new JPanel();
        JLabel time = new JLabel("Time : ");
        hour = new JComboBox<>(initHourBox());
        minute = new JComboBox<>(initMinuteBox());
        timePane.add(time);
        timePane.add(hour);
        timePane.add(minute);
        JPanel hallPane = new JPanel();
        JLabel hallNo = new JLabel("Hall No: ");
        List<CinemaHall> halls = hallController.getAllHalls();
        hallData = new JComboBox(halls.toArray());
        hallPane.add(hallNo);
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
                seanceController.editSeance(seanceController.getSelectedSeance());
                seanceController.updateView();
                setVisible(false);
            }
        });
    }

    public Object[] getSeanceData() {
        Object[] seanceData = {
                movieData.getSelectedItem(),
                convertBoxesToDate(),
                convertBoxesToTime(),
                hallData.getSelectedItem()};
        return seanceData;
    }

    private Date convertBoxesToDate() {
        int y = (Integer) year.getSelectedItem();
        int m = (Integer) month.getSelectedItem();
        int d = (Integer) day.getSelectedItem();
        return Date.valueOf(LocalDate.of(y, m, d));
    }

    private Time convertBoxesToTime() {
        int h = (Integer) hour.getSelectedItem();
        int min = (Integer) minute.getSelectedItem();
        return Time.valueOf(LocalTime.of(h, min));
    }


    private Integer[] initDayBox() {
        Integer[] dayBox = new Integer[31];
        int counter = 1;
        for (int i = 0; i < dayBox.length; i++) {
            dayBox[i] = counter;
            counter++;
        }
        return dayBox;
    }

    public void showEditedSeance(Seance seance) {
        movieData.setSelectedItem(seance.getMovie());
        day.setSelectedItem(seance.getDate().toLocalDate().getDayOfMonth());
        month.setSelectedItem(seance.getDate().toLocalDate().getMonth());
        year.setSelectedItem(seance.getDate().toLocalDate().getYear());
        hour.setSelectedItem(seance.getTime().toLocalTime().getHour());
        minute.setSelectedItem(seance.getTime().toLocalTime().getMinute());
        hallData.setSelectedItem(seance.getHall().getId());
    }

    private Integer[] initMonthBox() {
        Integer[] monthBox = new Integer[12];
        int counter = 1;
        for (int i = 0; i < monthBox.length; i++) {
            monthBox[i] = counter;
            counter++;
        }
        return monthBox;
    }

    private Integer[] initYearBox() {
        Integer[] yearBox = new Integer[20];
        int counter = 2000;
        for (int i = 0; i < yearBox.length; i++) {
            yearBox[i] = counter;
            counter++;
        }
        return yearBox;
    }

    private Integer[] initHourBox() {
        Integer[] hourBox = new Integer[24];
        for (int i = 0; i < hourBox.length; i++) {
            hourBox[i] = i;
        }
        return hourBox;
    }

    private Integer[] initMinuteBox() {
        Integer[] minuteBox = new Integer[60];
        for (int i = 0; i < minuteBox.length; i++) {
            minuteBox[i] = i;
        }
        return minuteBox;
    }
}
