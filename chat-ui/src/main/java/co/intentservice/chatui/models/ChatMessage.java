package co.intentservice.chatui.models;

import android.text.format.DateFormat;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import co.intentservice.chatui.ChatView;


/**
 * Chat Message model used when ChatMessages are required, either to be sent or received,
 * all messages that are to be shown in the chat-ui must be contained in this model.
 */
public class ChatMessage implements Serializable {

    private String message;
    private long timestamp;
    private Type type;
    private String sender;
    private boolean sent = false;

    public ChatMessage(String message, long timestamp, Type type) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public ChatMessage(String message, long timestamp, Type type, String sender) {
        this(message, timestamp, type);
        this.sender = sender;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getFormattedTime() {

        Date timestampDate = new Date(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestampDate);
        int dayTimestamp = calendar.get(Calendar.DAY_OF_MONTH);
        int monthTimestamp = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Date currentDate = new Date(System.currentTimeMillis());
        calendar.setTime(currentDate);
        int dayCurrent = calendar.get(Calendar.DAY_OF_MONTH);
        int monthCurrent = calendar.get(Calendar.MONTH);
        int yearCurrent = calendar.get(Calendar.YEAR);

        if (dayTimestamp == dayCurrent && monthTimestamp == monthCurrent && year == yearCurrent) {
            return DateFormat.format("HH:mm", timestamp).toString();
        } else {
            return DateFormat.format("dd/MM - HH:mm", timestamp).toString();
        }
    }

    public String getSender() {
        return sender;
    }

    public void setMessageSent(ChatView chatView) {
        sent = true;
        chatView.updateAllMessages();
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSent() {
        return sent;
    }

    public enum Type implements Serializable {
        SENT, RECEIVED, POSITION_SENT, POSITION_RECEIVED
    }
}
