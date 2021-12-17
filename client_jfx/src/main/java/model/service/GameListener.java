package model.service;

public class GameListener extends SocketClient {
    /**
     * Constructs a simple client with all possible configurations
     *
     * @param id The username of signed user as id the server may use to identify this client
    **/
    public GameListener(String id) {
        super("localhost", 9210, 5000, id, "_DEFAULT_GROUP_");
    }
}
