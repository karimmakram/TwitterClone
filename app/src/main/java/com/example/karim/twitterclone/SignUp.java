package com.example.karim.twitterclone;

import android.content.Intent;
import android.database.Cursor;
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

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button signin,signup;
    private ImageView img;
    private TextView text_massege;
    private EditText et_name,et_username,et_pass,et_confirm_pass;
    private DBMS dm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        signin=findViewById(R.id.resignin);
        signup=findViewById(R.id.resignup);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
        text_massege = findViewById(R.id.text_msg);
        img=findViewById(R.id.image);
        dm=new DBMS(this);
        et_name=findViewById(R.id.Name);
        et_username=findViewById(R.id.username);
        et_pass = findViewById(R.id.password);
        et_confirm_pass = findViewById(R.id.confirm_password);


        Calendar c =Calendar.getInstance();
        int time = c.get(Calendar.MINUTE);
        int i =c.get(Calendar.HOUR_OF_DAY);
        if (i>=0 && i < 24){
            img.setImageDrawable(getResources().getDrawable(R.mipmap.morning_mood));
            text_massege.setText("Morning Mood");
        }
        else
        {
            img.setImageDrawable(getResources().getDrawable(R.mipmap.night_mood));
            text_massege.setText("Night Mood");
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.resignin):
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case (R.id.resignup):
                if (et_name.getText().toString().equals("")|| et_username.getText().toString().equals("")||
                et_pass.getText().toString().equals(""))
                {
                    Toast.makeText(this,"You must write name and userName and pass",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (et_pass.getText().toString().equals(et_confirm_pass.getText().toString())) {
                        if(dm.insert(et_name.getText().toString(), et_username.getText().toString(), et_pass.getText().toString()))
                        {
                            show(dm.getALL());
                            Toast.makeText(this, "userAdd", Toast.LENGTH_SHORT).show();
                            et_name.setText("");
                            et_username.setText("");
                            et_pass.setText("");
                            et_confirm_pass.setText("");
                        }
                        else {
                            Toast.makeText(this, "username Exits", Toast.LENGTH_SHORT).show();
                        }
                        }
                     else {
                        Toast.makeText(this, "Confirm password first", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
        }
    }
    public void show(Cursor c){
        while (c.moveToNext())
            Log.i(c.getString(0),c.getString(1)+"     "+c.getString(2));
    }
}
