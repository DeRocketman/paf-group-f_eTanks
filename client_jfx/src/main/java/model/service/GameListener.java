package model.service;

import controller.GameLobbyViewController;
import model.game.logic.GameLobby;
import model.game.logic.GamePlay;
import model.game.logic.Player;
import org.boon.core.Sys;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        System.out.println(serverIP + " " + serverPort);
    }

    public GameListener(String serverIP, int serverPort, GamePlay gamePlay) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.gamePlay = gamePlay;

    }

    public void run() {
        try {
            System.out.println("HIER GEHTS IN SOCKET RUN");
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
                    if (message.getMsgState() == MessageState.SHOW_LOBBYLIST) {

                    }
                    if (message.getMsgState() == MessageState.HOST_LOBBY) {
                        System.out.println("HOST_LOBBY Message receive");
                    }
                    if (message.getMsgState() == MessageState.CLOSE_LOBBY) {

                    }
                    if (message.getMsgState() == MessageState.JOIN_LOBBY) {
                        System.out.println("JOIN_LOBBY Message receive");
                    }
                    if (message.getMsgState() == MessageState.LEAVE_LOBBY) {

                    }
                    if (message.getMsgState() == MessageState.CHAT_MSG) {

                    }
                    if (message.getMsgState() == MessageState.ACTION) {

                    }
                    if (message.getMsgState() == MessageState.START_GAME) {

                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Error or Exception with Connection.");
        }
    }

    /**
     * This function send messages to the socket-server to host/close or join/leave a Lobby
     * @param state as an order of what should happen
     * @param player who want the action
     * @param lobby  with which one interacts
     * @param messageContent make it possible to send a chat message
     * @throws IOException in case something goes wrong
     */
    public static void doThingsWithLobby(MessageState state, Player player, GameLobby lobby, String messageContent) throws IOException {
        Message msg = new Message();
        msg.setMsgState(state);
        msg.setPlayer(player);
        msg.setLobby(lobby);
        msg.setMsgContent("messageContent");
        System.out.println(msg.getMsgState());
        System.out.println(msg.getMsgContent());
        System.out.println(msg.getLobby().getGameLobbyID());
        System.out.println(msg.getPlayer().getPublicName());
        objectOutputStream.writeObject(msg); //WHY IS THIS NULL?
        objectOutputStream.flush();
    }

    public static void doThingsInGame(MessageState state, Player player) throws IOException {
        Message msg = new Message();
        msg.setMsgState(state);
        msg.setPlayer(player);

        //TODO SEND FURTHER DATA HERE!

        objectOutputStream.writeObject(msg);
        objectOutputStream.flush();
    }

    public String getCurrentTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat timeForm = new SimpleDateFormat("[HH:mm:ss]");
        String timestamp = timeForm.format(date);
        return timestamp;
    }
}
