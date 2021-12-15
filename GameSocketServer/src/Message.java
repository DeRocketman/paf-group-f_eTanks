import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    public String msgContent;

    private MessageState state;
    private String timestamp;
    private String publicUserName;
    private String content;

    public Message()
    {

    }
    public Message(String type, String timestamp, String publicUserName, String content)
    {
        this.state = MessageState.valueOf(type);
        this.timestamp = timestamp;
        this.publicUserName = publicUserName;
        this.content = content;
    }
    public Message(MessageState msgState, String timestamp, String publicUserName, String content)
    {
        this.state = msgState;
        this.timestamp = timestamp;
        this.publicUserName = publicUserName;
        this.content = content;
    }

    public MessageState getMsgState() {return state;}
    public void setMsgType(MessageState state) {this.state = state;}
    public String getTimestamp() {return timestamp;}
    public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
    public String getPublicUserName() {return publicUserName;}
    public void setPublicUserName(String nickname) {this.publicUserName = nickname;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String toString()
    {
        return this.timestamp + " " + this.publicUserName + "(" + this.state.toString() + "): " + this.content;
    }
}
