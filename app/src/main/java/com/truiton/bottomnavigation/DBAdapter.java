package com.truiton.bottomnavigation;

/**
 * Created by jtibrewal on 25/03/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "credentials.db";
    private static final int DATABASE_VERSION = 5;
    public static final String TABLE_CONTACTS="tableusername";
    private Context context;
    private static final String KEY_ID = "id";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public DBAdapter(Context context) {
        // TODO Auto-generated constructor stub
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
        Log.d("DB", "Class Created");
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
        Log.d("DB", "DB Created");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT, " + USERNAME + " TEXT, "
                + PASSWORD + " TEXT " + " ) ";

        arg0.execSQL(CREATE_CONTACTS_TABLE);
        Log.d("DB", "DB Created: " + CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int oldversion, int newversion) {
        // TODO Auto-generated method stub
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(arg0);

    }
    void insert( String name, String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();    //set write mode
        ContentValues values=new ContentValues();
        values.put(NAME, name);
        values.put(USERNAME, username);
        values.put(PASSWORD, password);
        db.insert(TABLE_CONTACTS, null, values);
        db.close();

    }
    public Cursor getInsertedData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.query(TABLE_CONTACTS, new String[] {KEY_ID,NAME,USERNAME,PASSWORD}, null, null, null, null, null);
    }

    public void clearData() {
        // TODO Auto-generated method stub
        context.deleteDatabase(DATABASE_NAME);
    }

    public boolean checkUser(String uname, String pwd) {
        // TODO Auto-generated method stub
        String sel = "SELECT * FROM " + DBAdapter.TABLE_CONTACTS + " WHERE "
                + DBAdapter.USERNAME + "=? AND "
                + DBAdapter.PASSWORD + "=?";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c = db.rawQuery(sel, new String[] {uname, pwd});

        return c.moveToFirst();
    }

}

