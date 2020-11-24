package co.intentservice.chatui.models;

import java.io.Serializable;

public class InfoMessage extends ChatMessage {

    public InfoMessage(String message, long timestamp) {
        super(message, timestamp, Type.INFO);
    }

    @Override
    public String getSender() {
        return null;
    }

    @Override
    public void setSender(String sender) {

    }
}
