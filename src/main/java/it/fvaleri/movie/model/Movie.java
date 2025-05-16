/*
 * Copyright 2023 Federico Valeri.
 * Licensed under the Apache License 2.0 (see LICENSE file).
 */
package it.fvaleri.movie.model;

public record Movie(String title, int criticsScore, int audienceScore) {
    public Movie() {
        this("na", -1, -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this.title.equals(((Movie) o).title())) {
            return true;
        }
        return false;
    }
}
