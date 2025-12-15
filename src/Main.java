
import app.MovieFinder;
import app.TextEditor;
import ioc.IoCContainer;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IoCContainer container = new IoCContainer();
        container.loadFromConfig("src/config/ioc-config.xml");

        try (Scanner scanner = new Scanner(System.in)) {
            OUTER:
            while (true) {
                System.out.println("\nMain Application Menu:");
                System.out.println("1. Movie Application");
                System.out.println("2. Spell Checker Application");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> runMovieApp(container, scanner);
                    case 2 -> runSpellCheckerApp(container, scanner);
                    case 3 -> {
                        System.out.println("Exiting Main Application...");
                        break OUTER;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void runMovieApp(IoCContainer container, Scanner scanner) {
        MovieFinder movieFinder = container.resolve(MovieFinder.class);

        OUTER:
        while (true) {
            System.out.println("\nMovie Application Menu:");
            System.out.println("1. Add a new movie");
            System.out.println("2. Find movies by director");
            System.out.println("3. See all listed movies");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 ->                     {
                        System.out.print("Enter director's name: ");
                        String director = scanner.nextLine();
                        System.out.print("Enter movie name: ");
                        String movieName = scanner.nextLine();
                        movieFinder.addMovie(director, movieName);
                    }
                case 2 ->                     {
                        System.out.print("Enter director's name: ");
                        String director = scanner.nextLine();
                        System.out.println(movieFinder.findMovieByDirector(director));
                    }
                case 3 -> {
                    Map<String, List<String>> allMovies = movieFinder.getAllMovies();
                    if (allMovies.isEmpty()) {
                        System.out.println("No movies found.");
                    } else {
                        allMovies.forEach((director, movies) -> {
                            System.out.println("Director: " + director);
                            System.out.println("Movies: " + String.join(", ", movies));
                        });
                    }
                }
                case 4 -> {
                    break OUTER;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void runSpellCheckerApp(IoCContainer container, Scanner scanner) {
        TextEditor textEditor = container.resolve(TextEditor.class);

        OUTER:
        while (true) {
            System.out.println("\nSpell Checker Menu:");
            System.out.println("1. Check text for spelling errors");
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter a text to check: ");
                    String text = scanner.nextLine();
                    textEditor.checkText(text);
                }
                case 2 -> {
                    break OUTER;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
