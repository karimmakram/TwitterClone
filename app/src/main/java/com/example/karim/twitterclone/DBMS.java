package com.example.karim.twitterclone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBMS {
    private SQLiteDatabase db;

    private static final String DB_name = "TwitterClone";
    private static final int DB_version = 4;
    private static final String table_name = "users";


    public static final String row_id = "id";
    public static final String row_name = "name";
    public static final String row_username = "username";
    public static final String row_pass = "password";

    private static final String table_name_follow = "Following";


    public static final String row_f_id = "id";
    public static final String row_f_follower = "Follower";
    public static final String row_f_follow = "Follow";


    private static final String DATABASE_CREATE_follow =
            "CREATE TABLE IF NOT EXISTS "+table_name_follow+"("+
                   row_f_id+" integer primary key autoincrement,"+
                    row_f_follower+" text not null,"+
                    row_f_follow+" text not null,"+
                    "FOREIGN KEY ("+row_f_follower+")"+" REFERENCES "+table_name+"( "+row_username+"));";


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



///////////////////////////////////////new table ////////////////////////////////////////////////////////////////
public Boolean insertFollow(String follower , String follow){
    String query = "insert into "+table_name_follow
            +" ("+row_f_follower+"," +row_f_follow + ") "+
            "values ("+
            "'"+follower+"', "
            + "'"+follow + "'" +" );" ;
    Log.i("INSERT()",query);
    try {
        db.execSQL(query);
        return true ;
    }
    catch (Exception e){
        return false ;
    }

}

    public Cursor getALLFollowing(){
        Cursor c = db.rawQuery("Select * From "+table_name_follow,null );
        return  c ;
    }

    public Cursor getFollowing(String username){
        Cursor c = db.rawQuery("Select * From "+table_name_follow +" where "+row_f_follower +"= '"+ username+"'",null );
        return  c ;
    }

    public boolean unFollow(String username,String Follow) {
        try {
            String qurey = "delete From " + table_name_follow + " where " + row_f_follower + "= '" + username + "' and " + row_f_follow + "= '" + Follow + "';";
            db.execSQL(qurey);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

////////////////////////// Table Post ///////////////////////////

    private static final String table_name_post = "Post";

    public static final String row_post_id = "id";
    public static final String row_post_username = "username";
    public static final String row_post_des = "postDes";
    private static final String DATABASE_CREATE_Post =
            "CREATE TABLE IF NOT EXISTS "+table_name_post+"("+
                    row_post_id+" integer primary key autoincrement,"+
                    row_post_username+" text not null,"+
                    row_post_des+" text not null,"+
                    "FOREIGN KEY ("+row_post_username+")"+" REFERENCES "+table_name+"( "+row_username+"));";


    public Boolean add_post(String username , String des){
        String query = "insert into "+table_name_post
                +" ("+row_post_username+"," +row_post_des + ") "+
                "values ("+
                "'"+username+"', "
                + "'"+des + "'" +" );" ;
        Log.i("INSERT()",query);
        try {
            db.execSQL(query);
            return true ;
        }
        catch (Exception e){
            return false ;
        }

    }
    public Cursor getALLpost(){
        Cursor c = db.rawQuery("Select * From "+table_name_post,null );
        return  c ;
    }
    public Cursor get_posts(String username){
        Cursor c = db.rawQuery("Select * From "+table_name_post +" where "+row_post_username +"= '"+ username+"'",null );
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
            db.execSQL(DATABASE_CREATE_follow);
            db.execSQL(DATABASE_CREATE_Post);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_CREATE_Post);
            Log.i("DBMS ",DATABASE_CREATE_Post);
        }
    }

}
