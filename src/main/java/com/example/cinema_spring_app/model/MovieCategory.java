package com.example.cinema_spring_app.model;

import java.util.EnumSet;

public enum MovieCategory {

    NO_RESTRICTIONS,
    OVER_15YO,
    OVER_18YO;

    MovieCategory() {
    }

    public static final EnumSet<MovieCategory> categories = EnumSet.allOf(MovieCategory.class);

    public static EnumSet<MovieCategory> getCategories() {
        return categories;
    }
}
