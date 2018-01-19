package com.pkr.eventargs.db;

import android.provider.BaseColumns;

/**
 * Created by Prashant on 1/18/2018.
 */

public class EventContract {

    public static final String DB_NAME = "com.pkr.eventargs.db";
    public static final int DB_VERSION = 1;

    public class EventEntry implements BaseColumns{
        public static final String TABLE = "eventsList";
        public static final String COL_EVENT_TITLE = "event";
    }

}
