package com.pkr.eventargs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PK on 4/22/2018.
 */

public class MyEventsAdapterSinglePage extends RecyclerView.Adapter<MyEventsAdapterSinglePage.ViewHolder> {

    ArrayList<String> items;
    Context context;
    View view;
    ViewHolder viewHolder;

    public MyEventsAdapterSinglePage(ArrayList<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyEventsAdapterSinglePage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_events, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyEventsAdapterSinglePage.ViewHolder holder, int position) {
        String[] parts = items.get(position).split("-");
        holder.myEventName.setText(parts[0]);
        holder.myEventDayS.setText(parts[1]);
        holder.myEventMonthS.setText(parts[2]);
        holder.myEventYearS.setText(parts[3]);
        holder.myEventDayE.setText(parts[4]);
        holder.myEventMonthE.setText(parts[5]);
        holder.myEventYearE.setText(parts[6]);
        holder.myEventTimeS.setText(parts[7]);
        holder.myEventTimeE.setText(parts[8]);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView myEventName, myEventDayS, myEventMonthS, myEventYearS, myEventDayE, myEventMonthE, myEventYearE, myEventTimeS, myEventTimeE;

        public ViewHolder(View itemView) {
            super(itemView);

            myEventName = itemView.findViewById(R.id.my_event_name);
            myEventDayS = itemView.findViewById(R.id.my_event_day);
            myEventMonthS = itemView.findViewById(R.id.my_event_month);
            myEventYearS = itemView.findViewById(R.id.my_event_year);
            myEventDayE = itemView.findViewById(R.id.my_event_day_end);
            myEventMonthE = itemView.findViewById(R.id.my_event_month_end);
            myEventYearE = itemView.findViewById(R.id.my_event_year_end);
            myEventTimeS = itemView.findViewById(R.id.start_time_text_my_events);
            myEventTimeE = itemView.findViewById(R.id.end_time_text_my_events);
        }
    }
}
