package co.intentservice.chatui.sample;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;
import co.intentservice.chatui.models.PositionMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        chatView.addMessage(new ChatMessage("Message received", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
        chatView.addMessage(new ChatMessage("A message with a sender name",
                System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Ryan Java"));
        double lat = 42.0912146;
        double lon = 12.5280263;
        final PositionMessage test = new PositionMessage(System.currentTimeMillis(),ChatMessage.Type.POSITION_SENT, Uri.parse("geo:0,0?q="+lat+","+lon+"(User+Name)"));
        chatView.addMessage(test);
        lat = 42.0913146;
        lon = 12.9282263;
        chatView.addMessage(new PositionMessage(System.currentTimeMillis(),ChatMessage.Type.POSITION_RECEIVED, Uri.parse("geo:0,0?q="+lat+","+lon+"(User+Name)")));
        //chatView.addMessage(new ChatMessage("Message sent", System.currentTimeMillis(), ChatMessage.Type.SENT));
        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                return true;
            }
        });

        chatView.setTypingListener(new ChatView.TypingListener() {
            @Override
            public void userStartedTyping() {

            }

            @Override
            public void userStoppedTyping() {

            }
        });

        chatView.setOnLocationSendListener(new ChatView.OnLocationSendListener() {
            @Override
            public void sendLocation(ChatView.OnLocationAcquiredListener callbackForSend) {
                Log.e("MainActivity","sendLocation() called");
            }
        });

        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                return true;
            }
        });

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                test.setMessageSent(chatView);
            }
        },4000);
    }
}
