package com.pkr.eventargs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Prashant on 1/18/2018.
 */

public class EventDBHelper extends SQLiteOpenHelper {
    public EventDBHelper(Context context) {
        super(context, EventContract.DB_NAME, null, EventContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + EventContract.EventEntry.TABLE + " ( " +
                EventContract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventContract.EventEntry.COL_EVENT_TITLE + " TEXT NOT NULL);";
        String insertTable = "INSERT INTO " + EventContract.EventEntry.TABLE + " (" + EventContract.EventEntry.COL_EVENT_TITLE + ") VALUES\n" +
                "('lalalallalal')," +
                "('blah')," +
                "('yooo');";

        db.execSQL(createTable);
        db.execSQL(insertTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EventContract.EventEntry.TABLE);
        onCreate(db);
    }
}
