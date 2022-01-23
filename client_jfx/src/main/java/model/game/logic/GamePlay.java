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

    private ObservableList<Player> players;
    private GameStatistic gameStatistic;
    private long gameNumber;
    // private ObservableList<GameStatistic> gameStatistics;

    public void receiveMessage(Message msg){
        if(msg.getMessageType() == MessageType.TANK_MOVE){
            msg.getPayload();
            msg.getPlayerId();
        } else if(msg.getMessageType() == MessageType.FIRE_MAIN){

        }
    }

    public void sendMessage(Message msg){
        this.socketClient.sendMsg(msg);
    }

    public GamePlay(ObservableList<Player> players) {
        this.players = players;

        for (Player player : players) {
            System.out.println(player.getPublicName());
        }
    }

    public GamePlay () {
        //Später von GameLobby übernehmen
        this.gameNumber = 1000;
    }

    public GamePlay(SocketClient socketClient) {
        this.socketClient = socketClient;

    }
    public void createGameStatistic() {
        System.out.println("Etankap - create game stats");
        //int playerCount = players.size();
       /* for (int i = 0; i < playerCount; i++) {
            GameStatistic userGameStatistic = new GameStatistic();
            this.gameStatistics.add(userGameStatistic);
        }*/
        //this.gameStatistic = new GameStatistic(this.gameNumber, this.eTankApplication.getSignedUser().getId(), false, 0, 0,0,0,0,0, this.eTankApplication.getSignedUser().getPublicName());
        this.gameStatistic = new GameStatistic(this.gameNumber, 500, false, 0, 0,0,0,0,0, "publicName");

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
