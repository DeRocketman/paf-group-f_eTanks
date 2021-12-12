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
                        if(incomingMsg.getMsgType() == MessageType.CONNECT) {
                            System.out.println("Server: message with type Connect");
                            Message msg = new Message();
                            msg.setTimestamp(getCurrentTimestamp());
                            if(playerList.size() == 4) {

                            }
                        }
                    }

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
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
