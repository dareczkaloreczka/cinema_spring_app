package com.example.cinema_spring_app.controller;

import com.example.cinema_spring_app.model.Seance;
import com.example.cinema_spring_app.model.repo.SeanceRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Observable;

@Component
public class SeanceObservable extends Observable {

    private final SeanceRepository seanceRepository;
    private List<Seance> seancesDB;

    public SeanceObservable(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
        seancesDB = seanceRepository.findAll();
    }

    public List<Seance> getSeancesDB() {
        return seancesDB;
    }

    public void setSeancesDB(List<Seance> seancesDB) {
        this.seancesDB = seancesDB;
        setChanged();
        notifyObservers();
    }
}
