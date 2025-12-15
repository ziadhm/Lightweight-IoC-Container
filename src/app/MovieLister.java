package app;

public class MovieLister {
    private final MovieFinder movieFinder;

    public MovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public void listMoviesByDirector(String director) {
        System.out.println("Listing movies for director: " + director);
        System.out.println(movieFinder.findMovieByDirector(director));
    }

    public MovieFinder getMovieFinder() {
        return movieFinder;
    }
}
