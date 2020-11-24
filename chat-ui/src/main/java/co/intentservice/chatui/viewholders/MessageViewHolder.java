package co.intentservice.chatui.viewholders;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;

import co.intentservice.chatui.R;
import co.intentservice.chatui.views.MessageView;

/**
 * View Holder for the Chat UI. Interfaces with the Received and Sent views and sets them up
 * with any messages required.
 * <p>
 * Original Code by Timi
 * Extended by James Lendrem, Michael Obi, Samuel Ojo
 */

public class MessageViewHolder {

    public final int STATUS_SENT = 0;
    public final int STATUS_RECEIVED = 1;
    public final int POSITION_SENT = 2;
    public final int POSITION_RECEIVED = 3;
    public final int INFO = 4;

    View row;
    Context context;

    private MessageView messageView;
    private int backgroundRcv, backgroundSend, backgroundInfo;
    private int bubbleBackgroundRcv, bubbleBackgroundSend, bubbleBackgroundInfo;

    public MessageViewHolder(View convertView, int backgroundRcv, int backgroundSend, int backgroundInfo, int bubbleBackgroundRcv, int bubbleBackgroundSend, int bubbleBackgroundInfo) {
        row = convertView;
        context = row.getContext();
        messageView = (MessageView) convertView;
        this.backgroundRcv = backgroundRcv;
        this.backgroundSend = backgroundSend;
        this.backgroundInfo = backgroundInfo;
        this.bubbleBackgroundSend = bubbleBackgroundSend;
        this.bubbleBackgroundRcv = bubbleBackgroundRcv;
        this.bubbleBackgroundInfo = bubbleBackgroundInfo;
    }

    public void setMessageAsSent() {
        messageView.setMessageAsSent();
    }

    public void setDefaultMessageStatus() {
        messageView.setDefaultMessageStatus();
    }

    public void setMessage(String message) {

        messageView.setMessage(message);

    }

    public void setTimestamp(String timestamp) {

        messageView.setTimestamp(timestamp);

    }

    public void setElevation(float elevation) {

        messageView.setElevation(elevation);

    }

    public void setSender(String sender) {
        messageView.setSender(sender);
    }

    public void setBackground(int messageType) {

        int chatMessageBackground = ContextCompat.getColor(context, R.color.cardview_light_background);
        int bubbleBackground = ContextCompat.getColor(context, R.color.cardview_light_background);

        switch (messageType) {
            case STATUS_RECEIVED:
            case POSITION_RECEIVED:
                chatMessageBackground = backgroundRcv;
                bubbleBackground = bubbleBackgroundRcv;
                break;
            case STATUS_SENT:
            case POSITION_SENT:
                chatMessageBackground = backgroundSend;
                bubbleBackground = bubbleBackgroundSend;
                break;
            case INFO:
                chatMessageBackground = backgroundInfo;
                bubbleBackground = bubbleBackgroundInfo;
        }

        messageView.setBackgroundColor(chatMessageBackground);
        messageView.setBackground(bubbleBackground);

    }

}
