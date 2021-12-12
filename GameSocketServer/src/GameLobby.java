import java.io.Serializable;
import java.util.ArrayList;

public class GameLobby implements Serializable {
    private long lobbyID;
    private Player host;
    private ArrayList<Player> playerList;

    public static ArrayList<GameLobby> lobbyList;

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
