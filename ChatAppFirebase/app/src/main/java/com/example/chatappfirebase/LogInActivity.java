package com.example.chatappfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LogInActivity extends AppCompatActivity {
    MaterialEditText et_email, et_password;
    Button login;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        toolbar = findViewById(R.id.toolbarlogin);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_email = findViewById(R.id.log_email);
        et_password = findViewById(R.id.log_password);
        login = findViewById(R.id.login_account);
    }
}