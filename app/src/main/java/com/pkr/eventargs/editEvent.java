package com.pkr.eventargs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.pkr.eventargs.db.EventContract;
import com.pkr.eventargs.db.EventDBHelper;

import java.util.ArrayList;
import java.util.List;

public class editEvent extends AppCompatActivity {

    private String TAG = ".MainActivity";
    private ListView mListView;
    private EventDBHelper mHelper = new EventDBHelper(this);
    private ArrayAdapter<String> mAdapter;
    private List<String> list = new ArrayList<String>();
    public PullRefreshLayout pLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        pLayout = findViewById(R.id.pull_layout);
        pLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        pLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pLayout.setRefreshing(false);
                        updateUI();
                        Toast.makeText(getApplicationContext(), "List updated", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });

        mListView = findViewById(R.id.list_events);
        updateUI();
    }

    public void updateUI() {
        if (mAdapter != null)
            list.clear();

        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.query(EventContract.EventEntry.TABLE,
                new String[]{EventContract.EventEntry._ID, EventContract.EventEntry.COL_EVENT_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_TITLE);
            list.add(cursor.getString(idx));
        }

        //logging the contents of the database
        for (int i=0; i<list.size(); i++)
            Log.e(TAG, i + list.get(i) + "\n");

//        if (mAdapter != null)
//            mAdapter.clear();

        mAdapter = new ArrayAdapter<String>(this,
                R.layout.item_events,
                R.id.event_title,
                list);
        mListView.setAdapter(mAdapter);
    }

    public void deleteEvent(View v){
        View parent = (View) v.getParent();
        TextView eventTitle = parent.findViewById(R.id.event_title);
        String event = String.valueOf(eventTitle.getText());

        Log.e(TAG, "Deleting Event : " + event);

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(EventContract.EventEntry.TABLE,
                EventContract.EventEntry.COL_EVENT_TITLE + " = ?",
                new String[]{event});
        updateUI();
    }
}
