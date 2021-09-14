package com.example.chatappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatappfirebase.Adapters.MessageAdapter;
import com.example.chatappfirebase.Model.Chats;
import com.example.chatappfirebase.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {
    String friendid, message, myid;
    CircleImageView circleImageView;
    TextView usernameToolbar;
    Toolbar toolbar;

    FirebaseUser firebaseUser;
    EditText et_message;
    Button send;

    List<Chats> chatsList;
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        myid = firebaseUser.getUid(); // who is logged in

        //Toolbar
        toolbar = findViewById(R.id.toolbar_message);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerview_messages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        circleImageView = findViewById(R.id.profile_image_toolbar_message);
        usernameToolbar = findViewById(R.id.username_ontoolbar_message);

        friendid = getIntent().getStringExtra("friendid");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(friendid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);

                usernameToolbar.setText(users.getUsername());

                if (users.getImageURL().equals("default")) {
                    circleImageView.setImageResource(R.drawable.user);
                } else {
                    Glide.with(getApplicationContext()).load(users.getImageURL()).into(circleImageView);
                }

                readMessages(myid, friendid, users.getImageURL());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // EditText message
        et_message = findViewById(R.id.edit_message_text);
        et_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().length() > 0) {
                    send.setEnabled(true);
                } else {
                    send.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = et_message.getText().toString();

                if (!text.startsWith(" ")) {
                    et_message.getText().insert(0, " ");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // send button
        send = findViewById(R.id.send_messsage_btn);
        send.setOnClickListener(v -> {
            message = et_message.getText().toString().trim();
            sendMessage(myid, friendid, message);
            et_message.setText(" ");
        });

    }

    private void readMessages(String myid, String friendid, String imageURL) {
        chatsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatsList.clear();

                for (DataSnapshot ds: snapshot.getChildren()) {
                    Chats chats = ds.getValue(Chats.class);

                    if (chats.getSender().equals(myid) && chats.getReciever().equals(friendid) ||
                            chats.getSender().equals(friendid) && chats.getReciever().equals(myid)) {
                        chatsList.add(chats);
                    }

                    messageAdapter = new MessageAdapter(MessageActivity.this, chatsList, imageURL);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(final String myid, final String friendid, final String message) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", myid);
        hashMap.put("reciever", friendid);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);

    }
}