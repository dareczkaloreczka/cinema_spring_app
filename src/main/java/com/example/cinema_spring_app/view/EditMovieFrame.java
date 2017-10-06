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
import java.util.Set;

@Component
public class EditMovieFrame extends JDialog {

    public static EditMovieFrame editMovieFrame;

   JTextField titleData;
   JTextField premiereData;
   JTextField directorData;
   JComboBox<MovieGenre> genreBox;
   JTextField durationData;
   JComboBox<MovieCategory> categoryBox;
   private final MovieController movieController;
   private Movie movie;

   public EditMovieFrame(MovieController movieController) throws HeadlessException {
       this.movieController = movieController;
       init(); }

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
       //premiereData = new JTextArea(movie.getYear().toString());
       premiereData = new JTextField(15);
       premierePane.add(premiere);
       premierePane.add(premiereData);
       JPanel directorPane = new JPanel();
       JLabel director = new JLabel("Director: ");
       //directorData = new JTextArea(movie.getDirector());
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
       //durationData = new JTextArea(String.valueOf(movie.getDuration()));
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

             movieController.editMovie(movie);
             movieController.updateView();
               setVisible(false);
           }
       });


   }

   public static EditMovieFrame getEditMovieFrame() {
       return editMovieFrame;
   }

   public static void setEditMovieFrame(EditMovieFrame editMovieFrame) {
       EditMovieFrame.editMovieFrame = editMovieFrame;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public JComboBox<MovieCategory> getCategoryBox() {
        return categoryBox;
    }

    public void setCategoryBox(JComboBox<MovieCategory> categoryBox) {
        this.categoryBox = categoryBox;
    }

    public MovieController getMovieController() {
        return movieController;
    }
}
