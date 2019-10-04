package com.example.karim.twitterclone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBMS {
    private SQLiteDatabase db;

    private static final String DB_name = "TwitterClone";
    private static final int DB_version = 1 ;
    private static final String table_name = "users";


    public static final String row_id = "id";
    public static final String row_name = "name";
    public static final String row_username = "username";
    public static final String row_pass = "password";


    DBMS(Context context){
        CustomSQLiteOpenHalper customSQLiteOpenHalper =new CustomSQLiteOpenHalper(context);
        db = customSQLiteOpenHalper.getWritableDatabase();

    }
    public Boolean insert(String name , String usrename,String password){
        String query = "insert into "+table_name
                +" ("+row_name+"," +row_username +","+row_pass+ ") "+
                "values ("+
                "'"+name+"', "
                + "'"+usrename + "'," +"'"+password+"'"+" );" ;
        Log.i("INSERT()",query);
        try {
            db.execSQL(query);
            return true ;
        }
        catch (Exception e){
            return false ;
        }

    }
    public boolean delete(String username){
        try {
            String query = "delete from "+table_name+
                    " Where "+row_username + " = '" + username +"' ;" ;
            db.execSQL(query);
            return true ;
        }
        catch (Exception e){
            return false;
        }
    }
    public Cursor serch(String username){
        Cursor c = db.rawQuery("select * from "+table_name +" WHERE "+row_username + " = '"+ username +"';",null);
        return c ;
    }

    public Cursor getALL(){
        Cursor c = db.rawQuery("Select * From "+table_name,null );
        return  c ;
    }

    private class CustomSQLiteOpenHalper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHalper(Context c){
            super(c,DB_name,null,DB_version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String new_table = "create table "+table_name+
                    " ("+row_id + " integer primary key autoincrement,"+
                    row_name + " text not null,"+
                    row_username + " text unique,"+
                    row_pass + " text not null);";
            db.execSQL(new_table);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
