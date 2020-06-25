package co.intentservice.chatui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import co.intentservice.chatui.R;
import co.intentservice.chatui.models.ChatMessage;
import co.intentservice.chatui.models.PositionMessage;
import co.intentservice.chatui.viewholders.MessageViewHolder;
import co.intentservice.chatui.views.MessageView;
import co.intentservice.chatui.views.ViewBuilder;
import co.intentservice.chatui.views.ViewBuilderInterface;

/**
 * List Adapter for use in the recycler view to display messages using the Message View Holder
 * <p>
 * Created by Timi
 * Extended by James Lendrem, Samuel Ojo
 */

public class ChatViewListAdapter extends BaseAdapter {

    public final int TEXT_SENT = 0;
    public final int TEXT_RECEIVED = 1;
    public final int POSITION_SENT = 2;
    public final int POSITION_RECEIVED = 3;

    private int backgroundRcv, backgroundSend;
    private int bubbleBackgroundRcv, bubbleBackgroundSend;
    private float bubbleElevation;
    private ViewBuilderInterface viewBuilder = new ViewBuilder();

    ArrayList<ChatMessage> chatMessages;

    Context context;
    LayoutInflater inflater;

    public ChatViewListAdapter(Context context, ViewBuilderInterface viewBuilder, int backgroundRcv, int backgroundSend, int bubbleBackgroundRcv, int bubbleBackgroundSend, float bubbleElevation) {
        this.chatMessages = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.backgroundRcv = backgroundRcv;
        this.backgroundSend = backgroundSend;
        this.bubbleBackgroundRcv = bubbleBackgroundRcv;
        this.bubbleBackgroundSend = bubbleBackgroundSend;
        this.bubbleElevation = bubbleElevation;
        this.viewBuilder = viewBuilder;
    }

    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return chatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessages.get(position).getType().ordinal();
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MessageViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TEXT_SENT:
                    convertView = viewBuilder.buildSentView(context, MessageView.MessageType.TEXT_MESSAGE);
                    break;
                case TEXT_RECEIVED:
                    convertView = viewBuilder.buildRecvView(context, MessageView.MessageType.TEXT_MESSAGE);
                    break;
                case POSITION_SENT:
                    convertView = viewBuilder.buildSentView(context, MessageView.MessageType.POSITION_MESSAGE);
                    convertView.findViewById(R.id.location_layout_clickable).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PositionMessage positionMessage = (PositionMessage) getItem(position);
                            Uri mapsUri = positionMessage.getMapsUri();
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            context.startActivity(mapIntent);
                        }
                    });
                    break;
                case POSITION_RECEIVED:
                    convertView = viewBuilder.buildRecvView(context, MessageView.MessageType.POSITION_MESSAGE);
                    convertView.findViewById(R.id.location_layout_clickable).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PositionMessage positionMessage = (PositionMessage) getItem(position);
                            Uri mapsUri = positionMessage.getMapsUri();
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            context.startActivity(mapIntent);
                        }
                    });
                    break;
            }

            holder = new MessageViewHolder(convertView, backgroundRcv, backgroundSend, bubbleBackgroundRcv, bubbleBackgroundSend);
            convertView.setTag(holder);
        } else {
            holder = (MessageViewHolder) convertView.getTag();
        }

        holder.setMessage(chatMessages.get(position).getMessage());
        holder.setTimestamp(chatMessages.get(position).getFormattedTime());
        holder.setElevation(bubbleElevation);
        holder.setBackground(type);
        String sender = chatMessages.get(position).getSender();
        if (sender != null) {
            holder.setSender(sender);
        }

        boolean isSent = chatMessages.get(position).isSent();
        if (isSent) {
            holder.setMessageAsSent();
        }

        return convertView;
    }

    public void addMessage(ChatMessage message) {
        chatMessages.add(message);
        notifyDataSetChanged();
    }

    public void addMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages.addAll(chatMessages);
        notifyDataSetChanged();
    }

    public void removeMessage(int position) {
        if (this.chatMessages.size() > position) {
            this.chatMessages.remove(position);
        }
    }

    public void clearMessages() {
        this.chatMessages.clear();
        notifyDataSetChanged();
    }

    public void updateAllMessages() {
        notifyDataSetChanged();
    }
}