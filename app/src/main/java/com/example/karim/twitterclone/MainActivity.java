package com.example.karim.twitterclone;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private TextView text_massege;
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
    }
}
