package co.intentservice.chatui.models;

import android.net.Uri;

public class PositionMessage extends ChatMessage {

    private Uri mapsUri;

    public PositionMessage(long timestamp, Type type, Uri mapsUri) {
        super("Visualizza posizione", timestamp, type);
        this.mapsUri = mapsUri;
    }

    public PositionMessage(long timestamp, Type type, String sender, Uri mapsUri) {
        super("Visualizza posizione", timestamp, type, sender);
        this.mapsUri = mapsUri;
    }

    public Uri getMapsUri() {
        return mapsUri;
    }
}
