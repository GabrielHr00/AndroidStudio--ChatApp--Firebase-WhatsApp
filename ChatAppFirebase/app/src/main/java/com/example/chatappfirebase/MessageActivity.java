package com.example.chatappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatappfirebase.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {
    String friendid;
    CircleImageView circleImageView;
    TextView usernameToolbar;
    Toolbar toolbar;

    FirebaseUser firebaseUser;
    MaterialEditText et_message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        et_message = findViewById(R.id.edit_message_text);
        send = findViewById(R.id.send_messsage_btn);

        //Toolbar
        toolbar = findViewById(R.id.toolbar_message);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

                if(users.getImageURL().equals("default")){
                    circleImageView.setImageResource(R.drawable.user);
                }
                else{
                    Glide.with(getApplicationContext()).load(users.getImageURL()).into(circleImageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}