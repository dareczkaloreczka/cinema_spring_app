package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.MovieController;
import com.example.cinema_spring_app.model.MovieCategory;
import com.example.cinema_spring_app.model.MovieGenre;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

@Component

public class AddMovieFrame extends JDialog {


    private JTextField titleData;
    private JTextField premiereData;
    private JTextField directorData;
    private JComboBox<MovieGenre> genreBox;
    private JTextField durationData;
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
        premiereData = new JTextField(15);
        premierePane.add(premiere);
        premierePane.add(premiereData);
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
        durationData = new JTextField(15);
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

    public JTextField getTitleData() {
        return titleData;
    }

    public void setTitleData(JTextField titleData) {
        this.titleData = titleData;
    }

    public JTextField getPremiereData() {
        return premiereData;
    }

    public void setPremiereData(JTextField premiereData) {
        this.premiereData = premiereData;
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

    public JTextField getDurationData() {
        return durationData;
    }

    public void setDurationData(JTextField durationData) {
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
}

