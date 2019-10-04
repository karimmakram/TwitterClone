package com.example.karim.twitterclone;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class user_Activity extends AppCompatActivity {
    CurerntUser curerntUser ;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        curerntUser = new CurerntUser();
        setTitle("User Activity");
        Toast.makeText(this, "Welcome "+curerntUser.getName(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Loginout){
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Logout");
            progressDialog.show();
            Intent intent = new Intent(user_Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
            return false;
        }
}
