package app;

import java.util.List; // Import for List
import java.util.Map;  // Import for Map

public interface MovieFinder {
    String findMovieByDirector(String director);
    void addMovie(String director, String movieName);
    Map<String, List<String>> getAllMovies(); // Method to return all movies
}
