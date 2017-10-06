package com.example.cinema_spring_app.view;

import com.example.cinema_spring_app.controller.MovieController;
import com.example.cinema_spring_app.model.Movie;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component

public class DetailsMoviePanel extends JPanel implements Observable {

    private JTextArea titleData;
    private JTextArea premiereData;
    private JTextArea directorData;
    private JTextArea genreData;
    private JTextArea durationData;
    private JTextArea ageData;
    private final TableMoviePanel observer;
    private final MovieController movieController;
    private final EditMovieFrame editMovieFrame;
    private final AddMovieFrame addMovieFrame;
    private final ShowMovieSeances showMovieSeances;

    public DetailsMoviePanel(TableMoviePanel observer, MovieController movieController, EditMovieFrame editMovieFrame,
                             AddMovieFrame addMovieFrame, ShowMovieSeances showMovieSeances) {
        this.observer = observer;
        this.movieController = movieController;
        this.editMovieFrame = editMovieFrame;
        this.addMovieFrame = addMovieFrame;
        this.showMovieSeances = showMovieSeances;
        init();

    }

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel details = new JLabel("DETAILS");
        JPanel movieDetails = new JPanel();
        movieDetails.setLayout(new GridLayout(3, 2));
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel("Tittle: ");
        titleData = new JTextArea("_____________________");
        titlePane.add(title);
        titlePane.add(titleData);
        JPanel premierePane = new JPanel();
        JLabel premiere = new JLabel("Premiere: ");
        premiereData = new JTextArea("_____________________");
        premierePane.add(premiere);
        premierePane.add(premiereData);
        JPanel directorPane = new JPanel();
        JLabel director = new JLabel("Director: ");
        directorData = new JTextArea("_____________________");
        directorPane.add(director);
        directorPane.add(directorData);
        JPanel genrePane = new JPanel();
        JLabel genre = new JLabel("Genre: ");
        genreData = new JTextArea("_____________________");
        genrePane.add(genre);
        genrePane.add(genreData);
        JPanel durationPane = new JPanel();
        JLabel duration = new JLabel("Duration: ");
        durationData = new JTextArea("_____________________");
        durationPane.add(duration);
        durationPane.add(durationData);
        JPanel agePane = new JPanel();
        JLabel age = new JLabel("Age restrictions: ");
        ageData = new JTextArea("_____________________");
        agePane.add(age);
        agePane.add(ageData);
        movieDetails.add(titlePane);
        movieDetails.add(premierePane);
        movieDetails.add(directorPane);
        movieDetails.add(genrePane);
        movieDetails.add(durationPane);
        movieDetails.add(agePane);
        add(details);
        add(movieDetails);

        JPanel buttonPane = new JPanel();
        JButton show = new JButton("Show seances");
        JButton add = new JButton("Add new movie");
        JButton edit = new JButton("Edit");
        JButton remove = new JButton("Remove");
        buttonPane.add(show);
        buttonPane.add(edit);
        buttonPane.add(remove);
        buttonPane.add(add);
        add(buttonPane);

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMovie();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addMovie();
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMovie();

            }
        });

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMovieSeances();
            }
        });
    }

    private void editMovie() {
        Movie movie = movieController.getSelectedMovie();
        editMovieFrame.setMovie(movie);
        movieController.showEditedMovie(movie);
        editMovieFrame.setVisible(true);
    }

    private void addMovie() {

        addMovieFrame.setVisible(true);
    }

    private void removeMovie() {
        movieController.removeMovie();
    }

    private void showMovieSeances(){
        Movie movie = movieController.getSelectedMovie();
        showMovieSeances.setMovie(movie);
        movieController.fillTheTableForSelectedMovie(movie);
        showMovieSeances.setVisible(true);
    }

    public JTextArea getTitleData() {
        return titleData;
    }

    public void setTitleData(JTextArea titleData) {
        this.titleData = titleData;
    }

    public JTextArea getPremiereData() {
        return premiereData;
    }

    public void setPremiereData(JTextArea premiereData) {
        this.premiereData = premiereData;
    }

    public JTextArea getDirectorData() {
        return directorData;
    }

    public void setDirectorData(JTextArea directorData) {
        this.directorData = directorData;
    }

    public JTextArea getGenreData() {
        return genreData;
    }

    public void setGenreData(JTextArea genreData) {
        this.genreData = genreData;
    }

    public JTextArea getDurationData() {
        return durationData;
    }

    public void setDurationData(JTextArea durationData) {
        this.durationData = durationData;
    }

    public JTextArea getAgeData() {
        return ageData;
    }

    public void setAgeData(JTextArea ageData) {
        this.ageData = ageData;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
