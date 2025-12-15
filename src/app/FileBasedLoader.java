package app;

public class FileBasedLoader implements FileLoader {
    @Override
    public String loadData() {
        // A large set of common English words for the dictionary
        return """
            hello world java spell checker dictionary program programming
            language developer code compile execute software hardware
            algorithm data structure object-oriented encapsulation inheritance
            polymorphism abstraction variable constant function method class
            interface abstract concrete implementation dependency injection
            container configuration xml json yaml framework application
            system process thread memory cpu storage disk file database
            network communication protocol internet server client request response
            debugging testing deployment integration continuous delivery
            optimization performance scalability security authentication authorization
            encryption decryption key certificate ssl tls
            user interface ux ui accessibility usability responsive mobile
            desktop web application cloud infrastructure architecture
            design pattern singleton factory observer mvc mvvm
            strategy command adapter facade bridge composite decorator
            iterator visitor prototype builder lazy initialization eager
            concurrent parallel distributed asynchronous event-driven
            exception error handling try catch finally throw throws
            logging monitoring metrics observability
            refactoring clean code solid dry yagni kiss
            agile scrum kanban devops sdlc version control git
            repository branch merge commit pull push stash rebase
            conflict resolve review approve release
            """;
    }
}
