package com.example.karim.twitterclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        progressDialog = new ProgressDialog(this);
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
        Intent intent = new Intent(this,user_Activity.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Loginout){
            progressDialog.setTitle("Logout");
            progressDialog.show();
            Intent intent = new Intent(Post_Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        else
        {
            if (item.getItemId() == R.id.user)
            {
                progressDialog.setTitle("other user");
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                else
                    progressDialog.show();

                Intent intent = new Intent(Post_Activity.this,user_Activity.class);
                startActivity(intent);
                return true;
            }
        }

        return false;
    }
}
