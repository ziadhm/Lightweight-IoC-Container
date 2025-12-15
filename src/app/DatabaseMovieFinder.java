package app;

import java.util.*;

public class DatabaseMovieFinder implements MovieFinder {
    @SuppressWarnings("unused")
    private final DatabaseAccess databaseAccess;
    private final Map<String, List<String>> moviesByDirector = new HashMap<>();

    public DatabaseMovieFinder(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    @Override
    public String findMovieByDirector(String director) {
        List<String> movies = moviesByDirector.getOrDefault(director, Collections.emptyList());
        if (movies.isEmpty()) {
            return "No movies found for director: " + director;
        }
        return "Movies directed by " + director + ": " + String.join(", ", movies);
    }

    @SuppressWarnings("unused")
    @Override
    public void addMovie(String director, String movieName) {
        moviesByDirector.computeIfAbsent(director, k -> new ArrayList<>()).add(movieName);
        System.out.println("Added movie: \"" + movieName + "\" for director: " + director);
    }

    @Override
    public Map<String, List<String>> getAllMovies() {
        return moviesByDirector;
    }
}
