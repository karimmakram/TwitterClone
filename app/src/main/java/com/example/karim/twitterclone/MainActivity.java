package com.example.karim.twitterclone;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img;
    private TextView text_massege;
    private Button signin,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);
        text_massege = findViewById(R.id.text_msg);
        Calendar c =Calendar.getInstance();
        int time = c.get(Calendar.MINUTE);
        int i =c.get(Calendar.HOUR_OF_DAY);
        Log.i("Hour",time+"");
        Log.i("Hour",i+"");
        if (time>=0 && time < 30){
            text_massege.setText("Morning Mood");
            img.setImageDrawable(getResources().getDrawable(R.mipmap.morning_mood));
        }
        else
        {
            img.setImageDrawable(getResources().getDrawable(R.mipmap.night_mood));
            text_massege.setText("Night Mood");
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.signup):
                Intent intent = new Intent(this,SignUp.class);
                startActivity(intent);
                finish();
                break;
            case (R.id.signin):
                Toast.makeText(this,"NOT have Data",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
