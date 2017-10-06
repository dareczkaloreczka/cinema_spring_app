package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.repo.MovieRepository;
import com.example.cinema_spring_app.model.repo.SeanceRepository;
import com.example.cinema_spring_app.view.DetailsSeancePanel;
import com.example.cinema_spring_app.view.TableSeancePanel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class SeanceController {


    private final SeanceRepository seanceRepository;
    private final TableSeancePanel tableSeancePanel;
    private final DetailsSeancePanel detailsSeancePanel;
    private final MovieRepository movieRepository;
    // private AddSeance addSeance;
  //  private EditSeance editSeance;
   // private SeanceObservable seanceObservable;

    @Lazy
    public SeanceController(SeanceRepository seanceRepository, TableSeancePanel tableSeancePanel, DetailsSeancePanel detailsSeancePanel, MovieRepository movieRepository) {
        this.seanceRepository = seanceRepository;
        this.tableSeancePanel = tableSeancePanel;
        this.detailsSeancePanel = detailsSeancePanel;
        this.movieRepository = movieRepository;
    }

    public void fillTheTable() {
        List<Seance> seancesList = seanceRepository.findAll();
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
//        seanceTools.getHallData().setText(String.valueOf(seance.getHall().getId()));
        detailsSeancePanel.getHallData().setText("tbc");


    }
    public void addSeance() {
        Seance seance = new Seance();
       // seance.setMovie(movieRepository.findByTitle(addSeance.getMovieData().getText()));
       // seance.setDate(Date.valueOf(addSeance.getDateData().getText()));
       // seance.setTime(Time.valueOf(addSeance.getTimeData().getText()));
        // seance.setHall(hallDAO.getHall(addSeance.getHallData().getSelectedItem()));
       // seance.setHall(null);
        seanceRepository.save(seance);
        updateView();

    }
    public void removeSeance(){
        Seance seance = getSelectedSeance();
        seanceRepository.delete(seance);
        updateView();
    }

    public void editSeance(Seance seance){

        //seanceRepository.updateMovie(movieRepository.findByTitle(editSeance.getTitleData().getText()));
       // seanceRepository.updateDate(Date.valueOf(editSeance.getDateData().getText()));
       // seanceRepository.updateTime(Time.valueOf(editSeance.getTimeData().getText()));
       // seanceRepository.updateTicketOption();
        // seanceRepository.updateHall();

    }

    public void updateView(){
       // seanceObservable = new SeanceObservable(null);
       // seanceObservable.addObserver(seancesTable);
       // seanceObservable.setSeancesDB(seanceDAO.getAllTheSeances());
    }

}
