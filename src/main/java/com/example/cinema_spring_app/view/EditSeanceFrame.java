package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.HallController;
import com.example.cinema_spring_app.controller.MovieController;
import com.example.cinema_spring_app.controller.SeanceController;
import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.Seance;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EditSeanceFrame extends JDialog {


    private JComboBox<Movie> movieData;
    private JComboBox<Integer> year;
    private JComboBox<Integer> month;
    private JComboBox<Integer> day;
    private JComboBox<Integer> hour;
    private JComboBox<Integer> minute;
    private JComboBox<Integer> hallData;
    private final SeanceController seanceController;
    private final HallController hallController;
    private final MovieController movieController;
    private Seance seance;
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
        List<Integer> halls = hallController.getAllHalls().stream().map(hall -> hall.getId()).collect(Collectors.toList());
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
                seanceController.editSeance(seance);
                seanceController.updateView();
                setVisible(false);
            }
        });

    }

    public JComboBox<Movie> getMovieData() {
        return movieData;
    }

    public void setMovieData(JComboBox<Movie> movieData) {
        this.movieData = movieData;
    }

    public MovieController getMovieController() {
        return movieController;
    }

    public JComboBox<Integer> getYear() {
        return year;
    }

    public void setYear(JComboBox<Integer> year) {
        this.year = year;
    }

    public JComboBox<Integer> getMonth() {
        return month;
    }

    public void setMonth(JComboBox<Integer> month) {
        this.month = month;
    }

    public JComboBox<Integer> getDay() {
        return day;
    }

    public void setDay(JComboBox<Integer> day) {
        this.day = day;
    }

    public HallController getHallController() {
        return hallController;
    }

    public JComboBox<Integer> getHour() {
        return hour;
    }

    public void setHour(JComboBox<Integer> hour) {
        this.hour = hour;
    }

    public JComboBox<Integer> getMinute() {
        return minute;
    }

    public void setMinute(JComboBox<Integer> minute) {
        this.minute = minute;
    }

    public JComboBox<Integer> getHallData() {
        return hallData;
    }

    public void setHallData(JComboBox<Integer> hallData) {
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
    private Integer[] initDayBox(){
        Integer[] dayBox = new Integer[31];
        int counter = 1;
        for (int i = 0; i <dayBox.length ; i++) {
            dayBox[i] = counter;
            counter++;
        }
        return dayBox;
    }
    private Integer[] initMonthBox(){
        Integer[] monthBox = new Integer[12];
        int counter = 1;
        for (int i = 0; i <monthBox.length ; i++) {
            monthBox[i] = counter;
            counter++;
        }
        return monthBox;
    }
    private Integer[] initYearBox(){
        Integer[] yearBox = new Integer[20];
        int counter = 2000;
        for (int i = 0; i <yearBox.length ; i++) {
            yearBox[i] = counter;
            counter++;
        }
        return yearBox;
    }
    private Integer[] initHourBox(){
        Integer[] hourBox = new Integer[24];
        for (int i = 0; i <hourBox.length ; i++) {
            hourBox[i] = i;
        }
        return hourBox;
    }
    private Integer[] initMinuteBox(){
        Integer[] minuteBox = new Integer[60];
        for (int i = 0; i <minuteBox.length ; i++) {
            minuteBox[i] = i;
        }
        return minuteBox;
    }
}
