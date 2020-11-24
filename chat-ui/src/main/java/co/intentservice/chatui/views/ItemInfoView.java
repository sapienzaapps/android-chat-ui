package co.intentservice.chatui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import co.intentservice.chatui.R;

public class ItemInfoView extends MessageView {

    private CardView bubble;
    private TextView messageTextView, timestampTextView;

    /**
     * Constructs a new message view.
     * @param context the app context
     */
    public ItemInfoView(Context context) {
        super(context, MessageType.INFO_MESSAGE);
        initializeView(context);
    }

    /**
     * Constructs a new message view with attributes, this constructor is used when we create a
     * message view using XML.
     * @param context the app context
     * @param attrs the attributes given by XML
     */
    public ItemInfoView(Context context, AttributeSet attrs) {
        super(context, attrs, MessageType.INFO_MESSAGE);
    }

    @Override
    public void setMessage(String message) {

        if (messageTextView == null) {
            messageTextView = (TextView) findViewById(R.id.message_text_view);
        }
        if (messageTextView != null) {
            messageTextView.setText(message);
        }
    }

    @Override
    public void setTimestamp(String timestamp) {

        if (timestampTextView == null) {
            timestampTextView = (TextView) findViewById(R.id.timestamp_text_view);
        }
        timestampTextView.setText(timestamp);
    }

    @Override
    public void setBackground(int background) {

        if (bubble == null) {
            this.bubble = (CardView) findViewById(R.id.bubble);
        }
        bubble.setCardBackgroundColor(background);
    }

    @Override
    public void setElevation(float elevation) {

        if (bubble == null) {
            this.bubble = (CardView) findViewById(R.id.bubble);
        }
        bubble.setCardElevation(elevation);
    }

    @Override
    public void setMessageAsSent() {

    }

    @Override
    public void setDefaultMessageStatus() {

    }

    @Override
    public void setSender(String sender) {

    }

    /**
     * Inflates the view so it can be displayed and grabs any child views that we may require
     * later on.
     * @param context   The context that is used to inflate the view.
     */
    private void initializeView(Context context) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.chat_item_info, this);
        this.messageTextView = (TextView) findViewById(R.id.message_text_view);

        this.bubble = (CardView) findViewById(R.id.bubble);
        this.timestampTextView = (TextView) findViewById(R.id.timestamp_text_view);

    }
}
