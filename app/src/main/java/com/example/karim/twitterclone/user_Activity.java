package com.example.karim.twitterclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class user_Activity extends AppCompatActivity {
    CurerntUser curerntUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        curerntUser = new CurerntUser();
        Toast.makeText(this, "Welcome "+curerntUser.getName(), Toast.LENGTH_SHORT).show();
    }
}
