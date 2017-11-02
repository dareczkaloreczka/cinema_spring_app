package com.example.cinema_spring_app.model;

import org.hibernate.annotations.Proxy;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table
public class Movie {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private Date year;
    @Column
    private int duration;
    @Column
    private String director;
    @Column
    private MovieGenre genre;
    @Column
    private MovieCategory ageCategory;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE,
            mappedBy = "movie", orphanRemoval = true)
    @Column(nullable = false)
    private List<Seance> seances;


    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
        this.seances.stream().forEach(s -> s.setMovie(this));
    }

    public void addSeance (Seance seance) {
        this.seances.add(seance);
        seance.setMovie(this);
    }
    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public MovieCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(MovieCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (duration != movie.duration) return false;
        if (!title.equals(movie.title)) return false;
        if (!year.equals(movie.year)) return false;
        return director.equals(movie.director);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + duration;
        result = 31 * result + director.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return title;
    }
}
