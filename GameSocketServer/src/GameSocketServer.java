import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;


public class GameSocketServer {
    public static final int PORT = 9001;
    private static ArrayList<Player> playerList;
    private static ArrayList<ObjectOutputStream> writers;

    public static void main(String[] args) throws IOException {
        System.out.println("Server is runnin, well done");
        ServerSocket serverSocket = new ServerSocket(PORT);
        try {

            while (true) {
                // wait for new client connection
                new Handler(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    public static class Handler extends Thread {
        private Socket socket;

        private InputStream inputStream;
        private ObjectInputStream objectInputStream;
        private OutputStream outputStream;
        private ObjectOutputStream objectOutputStream;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                this.inputStream = this.socket.getInputStream();
                this.objectInputStream = new ObjectInputStream(this.inputStream);
                this.outputStream = this.socket.getOutputStream();
                this.objectOutputStream = new ObjectOutputStream(this.outputStream);

                while(this.socket.isConnected()) {
                    Message incomingMsg = (Message) this.objectInputStream.readObject();
                    if(incomingMsg != null) {
                            System.out.println("Message resieved: " + incomingMsg.msgContent + " with Type: " + incomingMsg.getMsgState());
                            if (incomingMsg.getMsgState() == MessageState.SHOW_LOBBYLIST) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.HOST_LOBBY) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.CLOSE_LOBBY) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.JOIN_LOBBY) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.LEAVE_LOBBY) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.CHAT_MSG) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.ACTION) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.START_GAME) {

                            }
                        }
                    }

                } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        public String getCurrentTimestamp() {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat timeForm = new SimpleDateFormat("[HH:mm:ss]");
            String timestamp = timeForm.format(date);
            return timestamp;
        }

    }
}
