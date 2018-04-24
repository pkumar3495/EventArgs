package com.pkr.eventargs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkr.eventargs.db.EventContract;
import com.pkr.eventargs.db.EventDBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static com.pkr.eventargs.tab1.addZero;

public class tab2 extends Fragment {

    EventDBHelper mHelper;
    ArrayList<String> months = new ArrayList<>(Arrays.asList("JAN", "FEB", "MAR", "APR", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"));
    private ArrayList<String> list = new ArrayList<String>();
    private RecyclerView mRecyclerView;
    MyEventsAdapterSinglePage adapter;
    Calendar calendar;
    String dateStrCompBuff;
    int dateStrComp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab2, container, false);
//            TextView textView = rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        calendar = Calendar.getInstance();

        dateStrCompBuff = "" + calendar.get(Calendar.YEAR) + addZero(calendar.get(Calendar.MONTH)) + addZero(calendar.get(Calendar.DAY_OF_MONTH));
        dateStrComp = Integer.parseInt(dateStrCompBuff);
//        Log.e("tab1 : ","today : " + dateStrComp);

        mRecyclerView = rootView.findViewById(R.id.upcoming_recycler);
        mHelper = new EventDBHelper(getContext());

        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.query(EventContract.EventEntry.TABLE,
                new String[]{EventContract.EventEntry._ID, EventContract.EventEntry.COL_EVENT_TITLE,
                        EventContract.EventEntry.COL_S_DAY,
                        EventContract.EventEntry.COL_S_MONTH,
                        EventContract.EventEntry.COL_S_YEAR,
                        EventContract.EventEntry.COL_E_DAY,
                        EventContract.EventEntry.COL_E_MONTH,
                        EventContract.EventEntry.COL_E_YEAR,
                        EventContract.EventEntry.COL_S_TIME,
                        EventContract.EventEntry.COL_E_TIME},
                null, null, null, null, null);
        list.clear();
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_TITLE);
            int idx1 = cursor.getColumnIndex(EventContract.EventEntry.COL_S_DAY);
            int idx2 = cursor.getColumnIndex(EventContract.EventEntry.COL_S_MONTH);
            int idx3 = cursor.getColumnIndex(EventContract.EventEntry.COL_S_YEAR);
            int idx4 = cursor.getColumnIndex(EventContract.EventEntry.COL_E_DAY);
            int idx5 = cursor.getColumnIndex(EventContract.EventEntry.COL_E_MONTH);
            int idx6 = cursor.getColumnIndex(EventContract.EventEntry.COL_E_YEAR);
            int idx7 = cursor.getColumnIndex(EventContract.EventEntry.COL_S_TIME);
            int idx8 = cursor.getColumnIndex(EventContract.EventEntry.COL_E_TIME);

            String temp = cursor.getString(idx) + "-"
                    + cursor.getString(idx1) + "-"
                    + months.get(Integer.parseInt(cursor.getString(idx2))) + "-"
                    + cursor.getString(idx3) + "-"
                    + cursor.getString(idx4) + "-"
                    + months.get(Integer.parseInt(cursor.getString(idx5))) + "-"
                    + cursor.getString(idx6) + "-"
                    + cursor.getString(idx7) + "-"
                    + cursor.getString(idx8);
            String startMonth = cursor.getString(idx2);
            String endMonth = cursor.getString(idx5);
            String startDay = cursor.getString(idx1);
            String endDay = cursor.getString(idx4);
            String startDateStr = cursor.getString(idx3) + addZero(Integer.parseInt(startMonth)) + addZero(Integer.parseInt(startDay));
            String endDateStr = cursor.getString(idx6) + addZero(Integer.parseInt(endMonth)) + addZero(Integer.parseInt(endDay));
            int startDateInt = Integer.parseInt(startDateStr);
            int endDateInt = Integer.parseInt(endDateStr);
//            Log.e("tab1 : ", "start date : " + startDateInt);
//            Log.e("tab1 : ", "end date : " + endDateInt);
            if (dateStrComp < startDateInt)
                list.add(temp);
        }
        cursor.close();
        db.close();



        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyEventsAdapterSinglePage(list, getContext());
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }
}
