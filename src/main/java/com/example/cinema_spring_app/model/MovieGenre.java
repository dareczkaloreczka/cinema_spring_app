package com.example.cinema_spring_app.model;

import java.util.EnumSet;

public enum MovieGenre {

    DRAMA,
    COMEDY,
    HORROR,
    THRILLER,
    ROMANTIC_COMEDY,
    ACTION,
    BLACK_COMEDY,
    ANIMATION,
    FAMILY_MOVIE;

    MovieGenre() {
    }

    public static final EnumSet<MovieGenre> genres = EnumSet.allOf(MovieGenre.class);

    public static EnumSet<MovieGenre> getGenres() {
        return genres;
    }
}
