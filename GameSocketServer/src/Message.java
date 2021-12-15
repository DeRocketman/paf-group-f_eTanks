import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    public String msgContent;

    private MessageState state;

    private Player player;
    private String content;
    private GameLobby lobby;

    public Message()
    {

    }
    public Message(String type, Player player, String content)
    {
        this.state = MessageState.valueOf(type);
        this.player = player;
        this.content = content;
    }
    public Message(MessageState msgState, String timestamp,Player player, String content)
    {
        this.state = msgState;
        this.player = player;
        this.content = content;
    }

    public MessageState getMsgState() {return state;}
    public void setMsgState(MessageState state) {this.state = state;}

    public String getPublicUserName() {return player.getPublicName();}
    public void setPublicUserName(String nickname) {this.player.setPublicName(nickname);}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String toString()
    {
       return this.player.getPublicName() + "(" + this.state.toString() + "): " + this.content;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public MessageState getState() {
        return state;
    }

    public void setState(MessageState state) {
        this.state = state;
    }

    public GameLobby getLobby() {
        return lobby;
    }

    public void setLobby(GameLobby lobby) {
        this.lobby = lobby;
    }
}
