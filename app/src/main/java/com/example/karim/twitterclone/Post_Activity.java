package com.example.karim.twitterclone;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class Post_Activity extends AppCompatActivity {

    private EditText des;
    private Button send ;
    private CurerntUser user;
    private DBMS dbms ;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().hide();

        des = findViewById(R.id.ET_twitte);
        send = findViewById(R.id.send);
        user =new CurerntUser();
        dbms = new DBMS(this);
        show(dbms.getALLpost());
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbms.add_post(user.getUsername(),des.getText().toString()))
                {
                    FancyToast.makeText(Post_Activity.this,user.getName() +" Add post", Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                }
                else
                    FancyToast.makeText(Post_Activity.this,user.getName() +" not add post", Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();


            }
        });
    }

    public  void show(Cursor c){
        while (c.moveToNext())
            Log.i("PostAdd",c.getString(0)+"  "+c.getString(1)+"  "+c.getString(2));
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Intent intent = new Intent(this,user_Activity.class);
        startActivity(intent);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
