/*
 * Copyright 2023 Federico Valeri.
 * Licensed under the Apache License 2.0 (see LICENSE file).
 */
package it.fvaleri.movie;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.fvaleri.movie.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileCache {
    private ObjectMapper mapper;

    public FileCache() {
        this.mapper = new ObjectMapper();
    }

    public Set<Movie> read(Path filePath) throws IOException {
        Set<Movie> result = new HashSet<>();
        if (filePath != null && filePath.toFile().isFile()) {
            result.addAll(Arrays.asList(mapper.readValue(filePath.toFile(), Movie[].class)));
        }
        return result;
    }

    public void write(Set<Movie> movies, Path filePath) throws IOException {
        if (movies == null || movies.isEmpty()) {
            return;
        }
        Files.createDirectories(filePath.getParent());
        mapper.writeValue(filePath.toFile(), movies);
    }
}
