import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameSocketServer extends Server {
    /**
     * Constructs a simple server with all possible configurations
     *
     * @param port                    The port to listen on
     * @param autoRegisterEveryClient Whether a client that connects should be registered to send it
     *                                broadcast and direct messages later
     * @param keepConnectionAlive     Whether the connection should be kept alive using a ping package.
     *                                The transmission interval can be set using <code>setPingInterval(int seconds)</code>.
     * @param muted                   Whether the mute mode should be activated on startup
     */
    public GameSocketServer(int port, boolean autoRegisterEveryClient, boolean keepConnectionAlive, boolean muted) {
        super(port, autoRegisterEveryClient, keepConnectionAlive, muted);
    }

    @Override
    public void preStart() {
        this.registerMethod("TIME_REQUEST", new Executable() {
            @Override
            public void run(Datapackage pack, Socket socket) {
                String serverTime = new SimpleDateFormat("dd.MM.yyy HH:mm:ss").format(new Date());
                sendReply(socket, serverTime);
            }
        });
    }

    public static void main(String[] args) {

    }
}
