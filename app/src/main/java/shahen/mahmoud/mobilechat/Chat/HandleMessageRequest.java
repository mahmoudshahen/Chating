package shahen.mahmoud.mobilechat.Chat;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by harty on 7/30/2017.
 */

public class HandleMessageRequest {
    private static HandleMessageRequest handleMessageRequest = new HandleMessageRequest();
    private int messageNumber;
    private HandleMessageRequest() {
    }

    public static HandleMessageRequest getInstance() {
        return handleMessageRequest;
    }

    public void sendMessage(final DatabaseReference databaseReference, final String url, final Message message) {

        Log.v("size", String.valueOf(messageNumber));
        message.setId(String.valueOf(messageNumber));
        databaseReference.child(url).child(message.getId()).setValue(message);
        databaseReference.child("number").setValue(messageNumber+1);


    }

    public void getMessage(DatabaseReference databaseReference, String url, final Activity activity) {
        databaseReference.child(url).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (activity instanceof ChatActivity) {
                    ((ChatActivity) activity).setMessageInList(dataSnapshot.getValue(Message.class));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void setListenerMessageNumber(DatabaseReference databaseReference) {
        databaseReference.child("number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageNumber = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
