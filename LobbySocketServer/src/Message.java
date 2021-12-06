import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private MessageType msgType;
    private String timestamp;
    private String publicUserName;
    private String content;

    public Message()
    {

    }
    public Message(String type, String timestamp, String publicUserName, String content)
    {
        this.msgType = MessageType.valueOf(type);
        this.timestamp = timestamp;
        this.publicUserName = publicUserName;
        this.content = content;
    }
    public Message(MessageType msgType, String timestamp, String publicUserName, String content)
    {
        this.msgType = msgType;
        this.timestamp = timestamp;
        this.publicUserName = publicUserName;
        this.content = content;
    }

    public MessageType getMsgType() {return msgType;}
    public void setMsgType(MessageType msgType) {this.msgType = msgType;}
    public String getTimestamp() {return timestamp;}
    public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
    public String getPublicUserName() {return publicUserName;}
    public void setPublicUserName(String nickname) {this.publicUserName = nickname;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String toString()
    {
        return this.timestamp + " " + this.publicUserName + "(" + this.msgType.toString() + "): " + this.content;
    }
}
