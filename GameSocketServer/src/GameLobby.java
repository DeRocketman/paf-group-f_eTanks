import java.io.Serializable;
import java.util.ArrayList;

public class GameLobby implements Serializable {
    private long lobbyID;
    private Player host;
    private int seatCounter;
    private ArrayList<Player> playerList;

    public static ArrayList<GameLobby> lobbyList;

    public GameLobby() {
        System.out.println("GameLobby Erstellt");
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        setSeatCounter(getSeatCounter()+1);
    }

    public void removePlayer(Player player) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getId() == player.getId()) {
                playerList.remove(i);
                break;
            }
        }
    }

    public long getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(long lobbyID) {
        this.lobbyID = lobbyID;
    }

    public Player getHost() {
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public int getSeatCounter() {
        return seatCounter;
    }

    public void setSeatCounter(int seatCounter) {
        this.seatCounter = seatCounter;
    }

    public static ArrayList<GameLobby> getLobbyList() {
        return lobbyList;
    }

    public static void setLobbyList(ArrayList<GameLobby> lobbyList) {
        GameLobby.lobbyList = lobbyList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public static ArrayList<GameLobby> getGameLobbyList() {
        return lobbyList;
    }

    public static void addGameLobby(GameLobby lobby) {
        lobbyList.add(lobby);
    }

    public static void removeGameLobby(GameLobby lobby) {
        lobbyList.remove(lobby);
    }

}
