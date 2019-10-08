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
        show(dbms.getALLFollowing());
        show_u(dbms.getALL());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView =(CheckedTextView) view;
                if (checkedTextView.isChecked()) {
                    FancyToast.makeText(user_Activity.this, "You Now Follow " + arrayList.get(position), Toast.LENGTH_SHORT,
                            FancyToast.INFO, false).show();
                    dbms.insertFollow(curerntUser.getUsername(),arrayList.get(position));

                }
                else
                {
                    if (dbms.unFollow(curerntUser.getUsername(),arrayList.get(position)))
                    FancyToast.makeText(user_Activity.this,arrayList.get(position)+" Un Follow Now" ,Toast.LENGTH_SHORT,
                            FancyToast.INFO,false).show();


                }


            }
        });

        listView.setAdapter(Adapter);
        Checkeditem(dbms.getFollowing(curerntUser.getUsername()));
    }

    private void getalluser(Cursor c) {
        while (c.moveToNext()) {
            if (c.getString(2).equals(curerntUser.getUsername())) {
                Log.i("araaaaa",curerntUser.getUsername());

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
            Log.i("Follow",c.getString(0)+"  " +c.getString(1) + "     " + c.getString(2));

        }
        Log.i("Follow","no one");
    }

    public void show_u(Cursor c){
        while (c.moveToNext()) {
            Log.i("user",c.getString(0)+"  " +c.getString(1) + "     " + c.getString(2));

        }
        Log.i("Follow","no one");
    }

    public void Checkeditem(Cursor c){
        while (c.moveToNext())
        {
            for (String Tuser:arrayList)
                if (c.getString(2).equals(Tuser))
                    listView.setItemChecked(arrayList.indexOf(Tuser),true);
        }
    }
}
