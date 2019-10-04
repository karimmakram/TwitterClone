package com.example.karim.twitterclone;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.IDNA;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

public class user_Activity extends AppCompatActivity {
    CurerntUser curerntUser ;
    ProgressDialog progressDialog ;
    private ListView listView;
    private ArrayAdapter Adapter;
    private ArrayList<String> arrayList;
    private DBMS dbms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        curerntUser = new CurerntUser();
        dbms = new DBMS(this);
        setTitle("User Activity");
        arrayList = new ArrayList<>();
        FancyToast.makeText(this, "Welcome "+curerntUser.getName(), Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
        listView =findViewById(R.id.list);
//        show(dbms.getALL());
        getalluser(dbms.getALL());
        Adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_checked,arrayList);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView =(CheckedTextView) view;
                if (checkedTextView.isChecked())
                    FancyToast.makeText(user_Activity.this,"You Now Follow " +arrayList.get(position),Toast.LENGTH_SHORT,
                            FancyToast.INFO,false).show();
                else
                    FancyToast.makeText(user_Activity.this,arrayList.get(0)+" Un Follow Now" ,Toast.LENGTH_SHORT,
                            FancyToast.INFO,false).show();


            }
        });

        listView.setAdapter(Adapter);
    }

    private void getalluser(Cursor c) {
        while (c.moveToNext()) {
            if (c.getString(2).equals(curerntUser.getUsername())) {

            } else {
                arrayList.add(c.getString(2));
                Log.i(c.getString(1),c.getString(2));
            }

        }
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
    public void show(Cursor c){
        while (c.moveToNext()) {
            arrayList.add(c.getString(2).toString());
            Log.i(c.getString(0), c.getString(1) + "     " + c.getString(2));

        }
    }
}
