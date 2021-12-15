package model.service;

import model.game.logic.GameLobby;
import model.game.logic.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    public String msgContent;
    public MessageState msgState;
    public Player player;
    public GameLobby lobby;

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public MessageState getMsgState() {
        return msgState;
    }

    public void setMsgState(MessageState msgState) {
        this.msgState = msgState;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setLobby(GameLobby lobby) {
        this.lobby = lobby;
    }

    public Player getPlayer() {
        return player;
    }

    public GameLobby getLobby() {
        return lobby;
    }
}
