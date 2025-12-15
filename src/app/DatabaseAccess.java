package app;

public class DatabaseAccess {
    private final NetworkCommunicator networkCommunicator;

    public DatabaseAccess(NetworkCommunicator networkCommunicator) {
        this.networkCommunicator = networkCommunicator;
    }

    public String connect() {
        return "Connected to the database via " + networkCommunicator.communicate();
    }
}
