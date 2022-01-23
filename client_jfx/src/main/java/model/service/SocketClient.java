package model.service;

import controller.GameLobbyViewController;
import com.google.gson.Gson;
import model.game.logic.GamePlay;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class SocketClient implements Runnable {
    private GameLobbyViewController gameLobbyViewController;
    private GamePlay gamePlay;     //todo: oder in ModelView!
    private final String hostname = "localhost";
    private final int port = 3333;

    private final Socket socket;
    private final OutputStreamWriter dataOut;
    private final DataInputStream dataIn;
    private Gson gson;


    public SocketClient(GameLobbyViewController gameLobbyViewController) throws IOException {
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
                System.out.println("Message empfangen: " + msg);
                message = gson.fromJson(msg, Message.class);
                deliverMsg(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(Message message) {
        try {
            String outgoingMsg = gson.toJson(message);
            dataOut.write(outgoingMsg);
            dataOut.flush();
            System.out.println("Nachricht gesendet: " + outgoingMsg);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //TODO Aufsplitten
    private void deliverMsg(Message message) throws IOException {
        if (message.getMessageType() != MessageType.TANK_MOVE) {
            gameLobbyViewController.receiveLobbyMessages(message);
        } else {
            gamePlay.receiveMessage(message);
        }
    }

    public void connectMsg() throws IOException {

        //DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
        this.gson = new Gson();
        Message loginMessage = new Message();
        loginMessage.setMessageType(MessageType.LOGIN);
        loginMessage.setPlayerId(0);
        loginMessage.setPlayerPublicName("");
        loginMessage.setPlayerImage("default");
        loginMessage.setPlayerIsRdy(false);
        loginMessage.setPayload("JAVA");
        String outgoingMsg = gson.toJson(loginMessage);

        System.out.println("Nachricht gesendet: " + outgoingMsg);
    }

}

