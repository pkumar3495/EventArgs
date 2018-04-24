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
        public static final String COL_S_DAY = "start_day";
        public static final String COL_S_MONTH = "start_month";
        public static final String COL_S_YEAR = "start_year";
        public static final String COL_E_DAY = "end_day";
        public static final String COL_E_MONTH = "end_month";
        public static final String COL_E_YEAR = "end_year";
        public static final String COL_S_TIME = "start_time";
        public static final String COL_E_TIME = "end_time";
    }

}
