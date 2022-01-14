package model.service;

import controller.GameLobbyViewController;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient implements Runnable {
    private GameLobbyViewController gameLobbyViewController;
    private String hostname;
    private int port;

    private Socket socket;
    private StringBuilder sb = new StringBuilder();
    private OutputStreamWriter outputStreamWriter;
    private BufferedReader br;
    private BufferedWriter bw;
    private Gson gson;


    public SocketClient(String hostname, int port, GameLobbyViewController gameLobbyViewController) throws IOException {
        this.hostname = hostname;
        this.port = port;
        this.gameLobbyViewController = gameLobbyViewController;
        this.socket = new Socket(hostname, port);
    }

    @Override
    public void run() {
        try {
            System.out.println("ES BEGINNT THREAD LÄUFT");
            this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connect();
            while (socket.isConnected()) {
                DataInputStream dataIn = new DataInputStream(this.socket.getInputStream());
                String line;
                String msg;
                Message message = new Message();
                System.out.println("IN isConnected While-Schleife");

                msg = dataIn.readUTF();
                System.out.println("Message empfangen: " + msg);
                message = gson.fromJson(msg, Message.class);
                // todo: Messagehandling hier hin:



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
        //DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
        System.out.println("HIER ERSTE NACHRICHT VERFASSEN");
        this.gson = new Gson();
        Message loginMessage = new Message();
        loginMessage.setMessageType(MessageType.LOGIN);
        loginMessage.setPlayerId(2);
        loginMessage.setPlayerPublicName("PythonPeter");
        loginMessage.setPlayerImage("default");
        loginMessage.setPlayerIsRdy(false);
        String outgoingMsg = gson.toJson(loginMessage);
        outputStreamWriter.write(outgoingMsg);
        outputStreamWriter.flush();
        //dos.writeUTF(outgoingMsg);
        //dos.flush();
        System.out.println("Nachricht gesendet: " + outgoingMsg);
    }

}

