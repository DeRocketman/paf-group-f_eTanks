package model.service;

import java.io.Serializable;

public class Message implements Serializable {
    public String msgContent;
    public MessageState msgState;

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
}
