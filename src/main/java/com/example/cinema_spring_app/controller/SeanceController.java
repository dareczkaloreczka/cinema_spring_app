package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.CinemaHall;
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
import java.util.List;

@Component
public class SeanceController {


    private final SeanceRepository seanceRepository;
    private final TableSeancePanel tableSeancePanel;
    private final DetailsSeancePanel detailsSeancePanel;
    private final AddSeanceFrame addSeanceFrame;
    private final EditSeanceFrame editSeanceFrame;
    private final SeanceObservable seanceObservable;

    @Lazy
    public SeanceController(SeanceRepository seanceRepository, TableSeancePanel tableSeancePanel, DetailsSeancePanel detailsSeancePanel, AddSeanceFrame addSeanceFrame, EditSeanceFrame editSeanceFrame, SeanceObservable seanceObservable) {
        this.seanceRepository = seanceRepository;
        this.tableSeancePanel = tableSeancePanel;
        this.detailsSeancePanel = detailsSeancePanel;
        this.addSeanceFrame = addSeanceFrame;
        this.editSeanceFrame = editSeanceFrame;
        this.seanceObservable = seanceObservable;
    }

    public void fillTheTable() {
        List<Seance> seancesList = seanceRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "date"),
                new Sort.Order(Sort.Direction.ASC, "time")));
        for (Seance s : seancesList) {
            String[] seanceData = {String.valueOf(s.getId()), s.getMovie().getTitle(),String.valueOf(s.getDate()), String.valueOf(s.getTime())};
            tableSeancePanel.fillTheRow(seanceData);
        }
    }

    public Seance getSelectedSeance() {

        return seanceRepository.findOne(tableSeancePanel.getSelectedIndex());
    }
    public void showSelectedSeance(Seance seance) {
        detailsSeancePanel.setSeanceData(seance);
    }

    public void addSeance() {
        Seance seance = new Seance();
        addSeanceFrame.setFieldsForSeance(seance);
        seanceRepository.save(seance);
        updateView();

    }
    public void removeSeance(Seance seance){
        seanceRepository.delete(seance);
        updateView();
    }

    public void editSeance(Seance seance){
        seanceRepository.updateMovie(seance.getId(), (Movie) editSeanceFrame.getSeanceData()[0]);
        seanceRepository.updateDate(seance.getId(), (Date)(editSeanceFrame.getSeanceData()[1]));
        seanceRepository.updateTime(seance.getId(), (Time) (editSeanceFrame.getSeanceData()[2]));
        seanceRepository.updateHall(seance.getId(), (CinemaHall) (editSeanceFrame.getSeanceData()[3]));
    }

    public void showEditedSeance(Seance seance) {
        editSeanceFrame.showEditedSeance(seance);
    }

    public void updateView(){
       seanceObservable.addObserver(tableSeancePanel);
       seanceObservable.setSeancesDB(seanceRepository.findAll());
    }

}
