package model.service;

public class Message {
    private MessageType messageType;
    private String gameLobbyNumber;
    private long playerId;
    private String playerPublicName;
    private boolean playerIsRdy;
    private String playerImage;
    private String payload;

    public Message() {
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getGameLobbyNumber() {
        return gameLobbyNumber;
    }

    public void setGameLobbyNumber(String gameLobbyNumber) {
        this.gameLobbyNumber = gameLobbyNumber;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerPublicName() {
        return playerPublicName;
    }

    public void setPlayerPublicName(String playerPublicName) {
        this.playerPublicName = playerPublicName;
    }

    public boolean isPlayerIsRdy() {
        return playerIsRdy;
    }

    public void setPlayerIsRdy(boolean playerIsRdy) {
        this.playerIsRdy = playerIsRdy;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
