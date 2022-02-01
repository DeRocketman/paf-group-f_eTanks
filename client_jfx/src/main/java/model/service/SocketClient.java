package model.service;
import viewmodel.GameViewModel;

import controller.GameLobbyViewController;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient implements Runnable {
    private final GameLobbyViewController gameLobbyViewController;

    private GameViewModel gameViewModel;

    private final Socket socket;
    private final OutputStreamWriter dataOut;
    private final DataInputStream dataIn;
    private Gson gson;

    public SocketClient(GameLobbyViewController gameLobbyViewController) throws IOException {
        String hostname = "localhost";
        int port = 3333;
        this.gameLobbyViewController = gameLobbyViewController;
        this.socket = new Socket(hostname, port);
        this.dataOut = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
        this.dataIn = new DataInputStream(this.socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            connectMsg();
            while (socket.isConnected()) {
                Message message = new Message();

                String msg;
                msg = dataIn.readUTF();
               // System.out.println("Message empfangen: " + msg);
                message = gson.fromJson(msg, Message.class);
                deliverMsg(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(Message message) {
        try {
            String outgoingMsg = gson.toJson(message) + "\n";
            dataOut.write(outgoingMsg);
            dataOut.flush();
           // System.out.println("Nachricht gesendet: " + outgoingMsg);
        } catch (IOException e) {
          //  System.out.println(e.getMessage());
        }
    }

    //TODO Für eine richtige Verteilung der eingehenden Nachrichten sind hier weitere Messagtypen nachzutragen!
    private void deliverMsg(Message message) throws IOException {

        if (message.getMessageType() == MessageType.TANK_MOVE || message.getMessageType() == MessageType.FIRE_MAIN) {
            gameViewModel.receiveMessage(message);
        } else {
            gameLobbyViewController.receiveLobbyMessages(message);
        }
    }

    public void connectMsg() throws IOException {
        this.gson = new Gson();
        Message loginMessage = new Message();
        loginMessage.setMessageType(MessageType.CONNECT);
        loginMessage.setPlayerId(0);
        loginMessage.setPlayerPublicName("");
        loginMessage.setPlayerImage("default");
        loginMessage.setPlayerIsRdy(false);
        loginMessage.setPayload("JAVA");
        String outgoingMsg = gson.toJson(loginMessage);

       // System.out.println("Nachricht gesendet: " + outgoingMsg);
    }
    public void closeConnection() throws IOException {
        this.socket.close();
    }

    public void setGameViewModel(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }
}

