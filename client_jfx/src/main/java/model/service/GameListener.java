package model.service;

import controller.GameLobbyViewController;
import model.game.logic.GameLobby;
import model.game.logic.GamePlay;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameListener implements Runnable {
    private Socket socket;
    public String serverIP;
    public int serverPort;
    public GameLobbyViewController lobbyViewController;
    public GamePlay gamePlay;
    private static ObjectOutputStream objectOutputStream;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;

    public GameListener(String serverIP, int serverPort, GameLobbyViewController lobbyViewController) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.lobbyViewController = lobbyViewController;
    }

    public GameListener(String serverIP, int serverPort, GamePlay gamePlay) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.gamePlay = gamePlay;
    }

    public void run() {
        try {
            socket = new Socket(serverIP, serverPort);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            System.out.println("[Client] Could not connect to server: " + e.getMessage());
        }

        try {
            while (socket.isConnected()) {
                Message message = (Message) objectInputStream.readObject();

                if(message != null) {
                    System.out.println("Message resieved: " + message.msgContent + " with Type: " + message.getMsgState());
                    if (message.getMsgState() == MessageState.PLAYER) {
                        //usw
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
