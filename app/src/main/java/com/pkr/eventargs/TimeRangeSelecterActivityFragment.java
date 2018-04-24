package com.pkr.eventargs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;

import static com.pkr.eventargs.tab1.addZero;

/**
 * Created by PK on 4/24/2018.
 */

public class TimeRangeSelecterActivityFragment extends Fragment implements TimeRangePickerDialog.OnTimeRangeSelectedListener {
    Button selectTimeRangeButton;
    TextView timeRangeSelectedTextView;
    public static final String TIMERANGEPICKER_TAG = "timerangepicker";
    static String startTime, endTime;

    public TimeRangeSelecterActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_range_selecter, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        selectTimeRangeButton = (Button) getActivity().findViewById(R.id.bSelectTimeRangeFragment);
        timeRangeSelectedTextView = getView().findViewById(R.id.tvSelectedTimeRangeFragment);
        if (savedInstanceState != null) {
            TimeRangePickerDialog tpd = (TimeRangePickerDialog) getActivity().getSupportFragmentManager()
                    .findFragmentByTag(TIMERANGEPICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeRangeSetListener(this);
            }
        }
        timeRangeSelectedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TimeRangePickerDialog timePickerDialog = TimeRangePickerDialog.newInstance(
                        TimeRangeSelecterActivityFragment.this, false);
                timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMERANGEPICKER_TAG);
            }
        });
    }

    @Override
    public void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin) {
        startTime = addZero(startHour) + " : " + addZero(startMin);
        endTime = addZero(endHour) + " : " + addZero(endMin);
        timeRangeSelectedTextView.setText(startTime + "  -  " + endTime);
    }
}
