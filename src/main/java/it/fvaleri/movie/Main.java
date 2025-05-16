/*
 * Copyright 2023 Federico Valeri.
 * Licensed under the Apache License 2.0 (see LICENSE file).
 */
package it.fvaleri.movie;

import com.gargoylesoftware.htmlunit.WebClient;
import it.fvaleri.movie.crawler.LookCrawler;
import it.fvaleri.movie.crawler.RottenCrawler;
import it.fvaleri.movie.model.Movie;

import java.util.Set;

public class Main {
    static {
        System.setProperty("java.awt.headless", "true");
        System.setProperty("java.security.egd", "file:/dev/./urandom");
    }

    public static void main(String[] args) {
        try (WebClient client = crateWebClient()) {
            FileCache cache = new FileCache();
            RottenCrawler rottenCrawler = new RottenCrawler(client, cache);
            Set<Movie> movies = rottenCrawler.findCertifiedFreshMovies();
            LookCrawler lookCrawler = new LookCrawler(client, cache, movies);
            String viewURL = lookCrawler.findViewUrl();
            System.out.println(viewURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static WebClient crateWebClient() {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setTimeout(120_000);
        return client;
    }
}
