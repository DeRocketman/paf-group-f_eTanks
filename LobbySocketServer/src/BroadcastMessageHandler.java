import java.io.BufferedReader;

public class BroadcastMessageHandler extends MessageHandler {

    public BroadcastMessageHandler(BufferedReader reader) {
        super(reader);
    }

    @Override
    public void onMessage(String message) {
        super.onMessage(message);
        //LobbySocketServer.broadcast(message);
    }
}
