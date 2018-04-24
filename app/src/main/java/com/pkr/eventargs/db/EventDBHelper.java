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
                EventContract.EventEntry.COL_EVENT_TITLE + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_S_DAY + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_S_MONTH + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_S_YEAR + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_E_DAY + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_E_MONTH + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_E_YEAR + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_S_TIME + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_E_TIME + " TEXT NOT NULL);";
        String insertTable = "INSERT INTO " + EventContract.EventEntry.TABLE + " (" + EventContract.EventEntry.COL_EVENT_TITLE + ", "
                + EventContract.EventEntry.COL_S_DAY + ", "
                + EventContract.EventEntry.COL_S_MONTH + ", "
                + EventContract.EventEntry.COL_S_YEAR + ", "
                + EventContract.EventEntry.COL_E_DAY + ", "
                + EventContract.EventEntry.COL_E_MONTH + ", "
                + EventContract.EventEntry.COL_E_YEAR + ", "
                + EventContract.EventEntry.COL_S_TIME + ", "
                + EventContract.EventEntry.COL_E_TIME + ") VALUES\n" +
                "('lalalallalal','27','0','2018','28','0','2018','09 : 00','12 : 00')," +
                "('blah','23','3','2018','25','3','2018','12 : 00','15 : 00')," +
                "('yooo','31','9','2018','1','10','2018','14 : 00','17 : 00');";

        db.execSQL(createTable);
        db.execSQL(insertTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EventContract.EventEntry.TABLE);
        onCreate(db);
    }
}
