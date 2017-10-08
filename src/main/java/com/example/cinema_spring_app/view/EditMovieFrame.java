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
public class EditMovieFrame extends JDialog {

    private JTextField titleData;
    private JComboBox<Integer> premiereYear;
    private JComboBox<Integer> premiereMonth;
    private JComboBox<Integer> premiereDay;
    private JTextField directorData;
    private JComboBox<MovieGenre> genreBox;
    private JComboBox<Integer> durationData;
    private JComboBox<MovieCategory> categoryBox;
    private final MovieController movieController;
    // private Movie movie;

    public EditMovieFrame(MovieController movieController) throws HeadlessException {
        this.movieController = movieController;
        init();
    }

    private void init() {
        setTitle("Edit movie");
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
        durationData = new JComboBox<>(initDurationBox());
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
        JButton save = new JButton("Save");
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

                movieController.editMovie(movieController.getSelectedMovie());
                movieController.updateView();
                setVisible(false);
            }
        });
    }

    public String[] getMovieData() {
        String[] movieData = {
                titleData.getText(),
                convertBoxesToDate().toString(),
                directorData.getText(),
                genreBox.getSelectedItem().toString(),
                durationData.getSelectedItem().toString(),
                categoryBox.getSelectedItem().toString()};
        return movieData;
    }

    private Date convertBoxesToDate() {
        int year = (Integer) premiereYear.getSelectedItem();
        int month = (Integer) premiereMonth.getSelectedItem();
        int day = (Integer) premiereDay.getSelectedItem();
        return Date.valueOf(LocalDate.of(year, month, day));
    }

    public void setFields(Movie movie) {
        titleData.setText(movie.getTitle());
        directorData.setText(movie.getDirector());
    }

    public JTextField getTitleData() {
        return titleData;
    }

    public void setTitleData(JTextField titleData) {
        this.titleData = titleData;
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

   /* public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }*/

    public JComboBox<MovieCategory> getCategoryBox() {
        return categoryBox;
    }

    public void setCategoryBox(JComboBox<MovieCategory> categoryBox) {
        this.categoryBox = categoryBox;
    }

    public MovieController getMovieController() {
        return movieController;
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

    private Integer[] initDurationBox() {
        Integer[] yearBox = new Integer[180];
        for (int i = 0; i < yearBox.length; i++) {
            yearBox[i] = i;
        }
        return yearBox;
    }
}
