import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;


public class GameSocketServer {
    public static final int PORT = 9001;
    private static ArrayList<Player> connectedPlayer;

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

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                this.inputStream = this.socket.getInputStream();
                this.objectInputStream = new ObjectInputStream(this.inputStream);
                this.outputStream = this.socket.getOutputStream();
                Player player = new Player();
                player.setObjectOutputStream(new ObjectOutputStream(this.outputStream));
                connectedPlayer.add(player);


                while(this.socket.isConnected()) {
                    Message incomingMsg = (Message) this.objectInputStream.readObject();
                    if(incomingMsg != null) {
                            System.out.println("Message resieved: " + incomingMsg.msgContent + " with Type: " + incomingMsg.getMsgState());
                            player.setId(incomingMsg.getPlayer().getId());
                            player.setPublicName(incomingMsg.getPublicUserName());
                            player.setImage(incomingMsg.getPlayer().getImage());
                            player.setRdy(incomingMsg.getPlayer().isRdy);
                            if (incomingMsg.getMsgState() == MessageState.SHOW_LOBBYLIST) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.HOST_LOBBY) {
                                hostLobby(player, incomingMsg.getLobby());
                            }
                            if (incomingMsg.getMsgState() == MessageState.CLOSE_LOBBY) {

                            }
                            if (incomingMsg.getMsgState() == MessageState.JOIN_LOBBY) {
                                joinLobby(incomingMsg.getLobby(), player);
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

        private Message hostLobby(Player player, GameLobby lobby) throws IOException {
            Message msg = new Message();
            for (GameLobby savedLobby: GameLobby.getLobbyList()) {
                if(savedLobby.getLobbyID() == lobby.getLobbyID()) {
                    msg.setMsgState(MessageState.SOFT_FAIL);
                    msg.setMsgContent(getCurrentTimestamp()+ " SERVER: Lobby mit der ID schon vorhanden");
                    player.getObjectOutputStream().writeObject(msg);
                    break;
                }
            }
            lobby.addPlayer(player);
            GameLobby.addGameLobby(lobby);
            msg.setMsgState(MessageState.HOST_LOBBY);
            msg.setMsgContent(getCurrentTimestamp()+ " SERVER: Lobby erfolgreich erstellt Kommandant " + player.getPublicName());
            player.getObjectOutputStream().writeObject(msg);

            return msg;
        }

        private Message joinLobby(GameLobby lobby, Player player) throws IOException {
            Message msg = new Message();

            for (GameLobby savedLobby: GameLobby.getLobbyList()) {
                if(savedLobby.getLobbyID() == lobby.getLobbyID()){
                    savedLobby.addPlayer(player);
                    msg.setPlayer(player);
                    msg.setMsgState(MessageState.JOIN_LOBBY);
                    msg.setContent(getCurrentTimestamp()+ " SERVER: Achtung der Panzerkommandant " + player.getPublicName() + " hat sich angeschlossen");
                    sendMsg(msg, savedLobby.getPlayerList());
                } else {
                    msg.setMsgState(MessageState.SOFT_FAIL);
                    msg.setMsgContent(getCurrentTimestamp()+ " SERVER: Lobby nicht gefunden");
                    player.getObjectOutputStream().writeObject(msg);
                }
            }
            return msg;
        }

        private void sendMsg(Message msg, ArrayList<Player> recipients) throws IOException {
            for (Player player: recipients) {
                player.getObjectOutputStream().writeObject(msg);
                player.getObjectOutputStream().reset();
            }
        }

        private String getCurrentTimestamp() {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat timeForm = new SimpleDateFormat("[HH:mm:ss]");
            return timeForm.format(date);
        }

    }
}
