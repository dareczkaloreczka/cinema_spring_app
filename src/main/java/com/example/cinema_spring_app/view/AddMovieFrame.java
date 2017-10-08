package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.MovieController;
import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Component

public class AddMovieFrame extends JDialog {


    private JTextField titleData;
    private JComboBox<Integer> premiereYear;
    private JComboBox<Integer> premiereMonth;
    private JComboBox<Integer> premiereDay;
    private JTextField directorData;
    private JComboBox<MovieGenre> genreBox;
    private JComboBox<Integer> durationData;
    private JComboBox<MovieCategory> categoryBox;
    private JButton save;
    private final MovieController movieController;

    public AddMovieFrame(MovieController movieController) throws HeadlessException {
        this.movieController = movieController;
        setTitle("Add movie");
        init();
    }

    private void init() {
        setSize(500, 250);
        JPanel mainPanel = new JPanel();
        JPanel movieDetails = new JPanel();
        movieDetails.setLayout(new GridLayout(3, 2));
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel("Tittle: ");
        titleData = new JTextField(15);
        titlePane.add(title);
        titlePane.add(titleData);
        JPanel premierePane = new JPanel();
        JLabel premiere = new JLabel("Premiere: ");
        premiereDay = new JComboBox<>(initDayBox());
        premiereMonth = new JComboBox<>(initMonthBox());
        premiereYear = new JComboBox<>(initYearBox());
        premierePane.add(premiere);
        premierePane.add(premiereDay);
        premierePane.add(premiereMonth);
        premierePane.add(premiereYear);
        JPanel directorPane = new JPanel();
        JLabel director = new JLabel("Director: ");
        directorData = new JTextField(15);
        directorPane.add(director);
        directorPane.add(directorData);
        JPanel genrePane = new JPanel();
        JLabel genre = new JLabel("Genre: ");
        Set<MovieGenre> genreSet = MovieGenre.getGenres();
        genreBox = new JComboBox(genreSet.toArray());
        genrePane.add(genre);
        genrePane.add(genreBox);
        JPanel durationPane = new JPanel();
        JLabel duration = new JLabel("Duration: ");
        durationData = new JComboBox(initDurationBox());
        durationPane.add(duration);
        durationPane.add(durationData);
        JPanel agePane = new JPanel();
        JLabel age = new JLabel("Age restrictions: ");
        Set<MovieCategory> categorySet = MovieCategory.getCategories();
        categoryBox = new JComboBox(categorySet.toArray());
        agePane.add(age);
        agePane.add(categoryBox);
        movieDetails.add(titlePane);
        movieDetails.add(premierePane);
        movieDetails.add(directorPane);
        movieDetails.add(genrePane);
        movieDetails.add(durationPane);
        movieDetails.add(agePane);
        save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        JPanel buttonPane = new JPanel();
        buttonPane.add(save);
        buttonPane.add(cancel);
        mainPanel.add(movieDetails);
        mainPanel.add(buttonPane);
        getContentPane().add(mainPanel);


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.addMovie();
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

    public void setFieldsForMovie(Movie movie){
        movie.setTitle(getTitleData().getText());
        movie.setYear(Date.valueOf(LocalDate.of(
                (Integer)getPremiereYear().getSelectedItem(),
                (Integer)getPremiereMonth().getSelectedItem(),
                (Integer)getPremiereDay().getSelectedItem())));
        movie.setDirector(getDirectorData().getText());
        movie.setGenre((MovieGenre) getGenreBox().getSelectedItem());
        movie.setDuration((Integer)(getDurationData().getSelectedItem()));
        movie.setAgeCategory((MovieCategory) getCategoryBox().getSelectedItem());
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
    private Integer[] initDurationBox(){
        Integer[] yearBox = new Integer[180];
        for (int i = 0; i <yearBox.length ; i++) {
            yearBox[i] = i;
        }
        return yearBox;
    }


    public JTextField getTitleData() {
        return titleData;
    }

    public void setTitleData(JTextField titleData) {
        this.titleData = titleData;
    }



    public JTextField getDirectorData() {
        return directorData;
    }

    public void setDirectorData(JTextField directorData) {
        this.directorData = directorData;
    }

    public JComboBox<MovieGenre> getGenreBox() {
        return genreBox;
    }

    public void setGenreBox(JComboBox<MovieGenre> genreBox) {
        this.genreBox = genreBox;
    }

    public JComboBox<Integer> getDurationData() {
        return durationData;
    }

    public void setDurationData(JComboBox<Integer> durationData) {
        this.durationData = durationData;
    }

    public JButton getSave() {
        return save;
    }

    public JComboBox<MovieCategory> getCategoryBox() {
        return categoryBox;
    }

    public void setCategoryBox(JComboBox<MovieCategory> categoryBox) {
        this.categoryBox = categoryBox;
    }

    public void setSave(JButton save) {
        this.save = save;
    }

    public MovieController getMovieController() {
        return movieController;
    }

    public JComboBox<Integer> getPremiereYear() {
        return premiereYear;
    }

    public void setPremiereYear(JComboBox<Integer> premiereYear) {
        this.premiereYear = premiereYear;
    }

    public JComboBox<Integer> getPremiereMonth() {
        return premiereMonth;
    }

    public void setPremiereMonth(JComboBox<Integer> premiereMonth) {
        this.premiereMonth = premiereMonth;
    }

    public JComboBox<Integer> getPremiereDay() {
        return premiereDay;
    }

    public void setPremiereDay(JComboBox<Integer> premiereDay) {
        this.premiereDay = premiereDay;
    }
}

