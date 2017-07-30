package shahen.mahmoud.mobilechat.Chat;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shahen.mahmoud.mobilechat.Adapters.ChatAdapter;
import shahen.mahmoud.mobilechat.Authentication.LoginActivity;
import shahen.mahmoud.mobilechat.R;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.rv_messages)
    RecyclerView messagesRecyclerView;
    @BindView(R.id.et_message)
    EditText messageEditText;
    @BindView(R.id.fab_add)
    FloatingActionButton addFAB;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Message> messagesList;
    ChatAdapter chatAdapter;
    HandleMessageRequest handleMessageRequest;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        handleMessageRequest = HandleMessageRequest.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        user = firebaseAuth.getCurrentUser();
        addFAB.setOnClickListener(this);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messagesList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messagesList, user);
        messagesRecyclerView.setAdapter(chatAdapter);
        handleMessageRequest.setListenerMessageNumber(databaseReference);
        handleMessageRequest.getMessage(databaseReference, getString(R.string.groupChatKey), this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == addFAB.getId()) {
            if (!messageEditText.getText().toString().isEmpty()) {
                Message message = new Message();
                message.setOwner(user.getEmail());
                message.setMessage(messageEditText.getText().toString());
                handleMessageRequest.sendMessage(databaseReference, getString(R.string.groupChatKey), message);
                messageEditText.setText("");
            }
        }
    }

    public void setMessageInList(Message message) {
        messagesList.add(message);
        chatAdapter.notifyDataSetChanged();
        messagesRecyclerView.scrollToPosition(messagesList.size()-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mi_sign_out:
                firebaseAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return true;
    }
}
