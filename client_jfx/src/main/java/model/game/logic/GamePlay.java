package model.game.logic;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.ETankApplication;
import model.data.GameStatistic;
import model.game.elements.*;
import model.service.Message;
import model.service.MessageType;
import model.service.SocketClient;

public class GamePlay {
    ETankApplication eTankApplication;
    private SocketClient socketClient;

    public ObservableList<Player> getPlayers() {
        return players;
    }

    private String gameNumber;
    private ObservableList<Player> players;
    private GameStatistic gameStatistic;
    // private ObservableList<GameStatistic> gameStatistics;

    public void sendMessage(Message msg){
        this.socketClient.sendMsg(msg);
    }

    public GamePlay(ObservableList<Player> players) {
        this.players = players;

        for (Player player : players) {
            System.out.println(player.getPublicName());
        }
    }


    public GamePlay(SocketClient socketClient, String gameNumber) {
        this.socketClient = socketClient;
        this.gameNumber = gameNumber;
    }
    public void createGameStatistic() {

    }


    public int getPlayerListSize(){
        return 4;
    }


    public void getPlayerPosition(){

    }

    public void setPlayerlist(ObservableList<Player> playerlist) {
        this.players = playerlist;
       // System.out.println(playerlist);
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        System.out.println("Etankap - gamePlay");
        this.eTankApplication = eTankApplication;
    }

    public GameStatistic getGameStatistic(){
        return this.gameStatistic;

    }
}
