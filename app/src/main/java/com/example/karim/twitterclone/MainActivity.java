package com.example.karim.twitterclone;

import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img;
    private TextView text_massege;
    private Button signin,signup;
    private EditText et_username,et_pass;
    private Cursor c ;
    private DBMS dm;
    private int i = 0 ;
    private CurerntUser curerntUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);
        text_massege = findViewById(R.id.text_msg);
        et_pass=findViewById(R.id.password);
        et_username=findViewById(R.id.username);
        Calendar c =Calendar.getInstance();
        int time = c.get(Calendar.MINUTE);
        int i =c.get(Calendar.HOUR_OF_DAY);
        dm=new DBMS(this);
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
                if (chick_Password(dm.serch(et_username.getText().toString()))) {
                    setCurrentuser(dm.serch(et_username.getText().toString()));
                    finish();
                    Intent intent_user = new Intent(this,user_Activity.class);
                    startActivity(intent_user);

                }
                else
                    if (i==0)
                     Toast.makeText(this,"You mush have Account",Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(this, "password failed", Toast.LENGTH_SHORT).show();
                        i=0;
                    }


                break;
        }
    }

    private void setCurrentuser(Cursor c) {
        while (c.moveToNext())
            curerntUser =new CurerntUser(c.getInt(0),
                    c.getString(1),c.getString(2));

    }

    public boolean chick_Password(Cursor c){
        while (c.moveToNext()) {
            Log.i("Caick pass", c.getString(3));
            i=1;
            return  (c.getString(3).equals(et_pass.getText().toString()));
        }
        return false;

    }
}
