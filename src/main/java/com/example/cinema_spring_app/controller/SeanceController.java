package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Movie;
import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.repo.HallRepository;
import com.example.cinema_spring_app.model.repo.MovieRepository;
import com.example.cinema_spring_app.model.repo.SeanceRepository;
import com.example.cinema_spring_app.view.AddSeanceFrame;
import com.example.cinema_spring_app.view.DetailsSeancePanel;
import com.example.cinema_spring_app.view.EditSeanceFrame;
import com.example.cinema_spring_app.view.TableSeancePanel;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class SeanceController {


    private final SeanceRepository seanceRepository;
    private final TableSeancePanel tableSeancePanel;
    private final DetailsSeancePanel detailsSeancePanel;
    private final MovieRepository movieRepository;
    private final AddSeanceFrame addSeanceFrame;
    private final EditSeanceFrame editSeanceFrame;
    private final SeanceObservable seanceObservable;
    private final HallRepository hallRepository;

    @Lazy
    public SeanceController(SeanceRepository seanceRepository, TableSeancePanel tableSeancePanel, DetailsSeancePanel detailsSeancePanel, MovieRepository movieRepository, AddSeanceFrame addSeanceFrame, EditSeanceFrame editSeanceFrame, SeanceObservable seanceObservable, HallRepository hallRepository) {
        this.seanceRepository = seanceRepository;
        this.tableSeancePanel = tableSeancePanel;
        this.detailsSeancePanel = detailsSeancePanel;
        this.movieRepository = movieRepository;
        this.addSeanceFrame = addSeanceFrame;
        this.editSeanceFrame = editSeanceFrame;
        this.seanceObservable = seanceObservable;
        this.hallRepository = hallRepository;
    }

    public void fillTheTable() {
        List<Seance> seancesList = seanceRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "date"),
                new Sort.Order(Sort.Direction.ASC, "time")));
        for (Seance s : seancesList) {
            String[] seanceData = {String.valueOf(s.getId()), s.getMovie().getTitle(),String.valueOf(s.getDate()), String.valueOf(s.getTime())};
            tableSeancePanel.getModel().addRow(seanceData);
        }
    }

    public Seance getSelectedSeance() {
        Seance seance = null;
        if (tableSeancePanel.getSeanceTable().getSelectedRow() > -1) {
            int id = Integer.parseInt((String) tableSeancePanel.getSeanceTable().getModel().getValueAt(tableSeancePanel.getSeanceTable().getSelectedRow(), 0));
            seance = seanceRepository.findOne(id);
        }
        return seance;
    }
    public void showSelectedSeance() {
        Seance seance = getSelectedSeance();
        detailsSeancePanel.getMovieData().setText(seance.getMovie().getTitle());
        detailsSeancePanel.getDateData().setText(String.valueOf(seance.getDate()));
        detailsSeancePanel.getTimeData().setText(String.valueOf(seance.getTime()));
        detailsSeancePanel.getHallData().setText(String.valueOf(seance.getHall().getId()));
    }

    public void addSeance() {
        Seance seance = new Seance();
        seance.setMovie((Movie) addSeanceFrame.getMovieData().getSelectedItem());
        seance.setDate(Date.valueOf(LocalDate.of(
                (Integer) addSeanceFrame.getYear().getSelectedItem(),
                (Integer) addSeanceFrame.getMonth().getSelectedItem(),
                (Integer) addSeanceFrame.getDay().getSelectedItem())));
        seance.setTime(Time.valueOf(LocalTime.of(
                (Integer) addSeanceFrame.getHour().getSelectedItem(),
                (Integer) addSeanceFrame.getMinute().getSelectedItem())));
        seance.setHall(hallRepository.findOne((Integer)addSeanceFrame.getHallData().getSelectedItem()));
        seanceRepository.save(seance);
        updateView();

    }
    public void removeSeance(){
        Seance seance = getSelectedSeance();
        seanceRepository.delete(seance);
        updateView();
    }

    public void editSeance(Seance seance){

        seanceRepository.updateMovie(seance.getId(), (Movie) addSeanceFrame.getMovieData().getSelectedItem());
        seanceRepository.updateDate(seance.getId(),Date.valueOf(LocalDate.of(
                (Integer) addSeanceFrame.getYear().getSelectedItem(),
                (Integer) addSeanceFrame.getMonth().getSelectedItem(),
                (Integer) addSeanceFrame.getDay().getSelectedItem())));
        seanceRepository.updateTime(seance.getId(),Time.valueOf(LocalTime.of(
                (Integer) addSeanceFrame.getHour().getSelectedItem(),
                (Integer) addSeanceFrame.getMinute().getSelectedItem())));
        seanceRepository.updateHall(seance.getId(), hallRepository.findOne((Integer)editSeanceFrame.getHallData().getSelectedItem()));
    }

    public void showEditedSeance(Seance seance) {
        editSeanceFrame.getMovieData().setSelectedItem(seance.getMovie());
        editSeanceFrame.getDay().setSelectedItem(seance.getDate().toLocalDate().getDayOfMonth());
        editSeanceFrame.getMonth().setSelectedItem(seance.getDate().toLocalDate().getMonth());
        editSeanceFrame.getYear().setSelectedItem(seance.getDate().toLocalDate().getYear());
        editSeanceFrame.getHour().setSelectedItem(seance.getTime().toLocalTime().getHour());
        editSeanceFrame.getMinute().setSelectedItem(seance.getTime().toLocalTime().getMinute());
        editSeanceFrame.getHallData().setSelectedItem(seance.getHall().getId());
    }

    public void updateView(){
       seanceObservable.addObserver(tableSeancePanel);
       seanceObservable.setSeancesDB(seanceRepository.findAll());
    }

}
