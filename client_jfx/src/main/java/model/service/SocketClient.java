package model.service;
import org.boon.core.Sys;
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

    /**
     * The constructor of the class SocketClient
     *
     * @param gameLobbyViewController
     * @throws IOException
     */
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
        } catch (UTFDataFormatException e){
            System.out.println("Keine UTF-8 Nachricht");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a message in the OutputStream
     *
     * @param message
     */
    public void sendMsg(Message message) {
        try {
            String outgoingMsg = gson.toJson(message) + "\n";
            dataOut.write(outgoingMsg);
            dataOut.flush();
            System.out.println("Nachricht gesendet: " + outgoingMsg);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delivers the message to the gameViewModel oder gameLobbyViewController
     * depending on the message type
     *
     * @param message           the sent message
     * @throws IOException
     */
    private void deliverMsg(Message message) throws IOException {
        if (message.getMessageType() == MessageType.TANK_MOVE || message.getMessageType() == MessageType.FIRE_MAIN) {
            gameViewModel.receiveMessage(message);
        } else {
            gameLobbyViewController.receiveLobbyMessages(message);
        }
    }

    /**
     * Sends a connect-Message to the Server
     *
     * @throws IOException
     */
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
    }

    /**
     * Closes the socket
     *
     * @throws IOException
     */
    public void closeConnection() throws IOException {
        this.socket.close();
    }

    public void setGameViewModel(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }
}

