package com.example.chatappfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity {
    MaterialEditText et_username, et_password, et_email;
    Button register;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbarreg);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Register");
//        setSupportActionBar(toolbar);

        et_username = findViewById(R.id.reg_username);
        et_email = findViewById(R.id.reg_email);
        et_password = findViewById(R.id.reg_password);
        register = findViewById(R.id.register);

    }
}