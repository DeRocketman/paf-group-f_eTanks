import java.io.*;
import java.net.Socket;

public class Handler extends Thread {
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
                        System.out.println("Server: message with type connect");
                        Message msg = new Message();

                    }
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
