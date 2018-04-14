package com.pkr.eventargs;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PK on 4/2/2018.
 */

public class savedEventListAdapter extends RecyclerView.Adapter<savedEventListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> items;
    private View view;
    private ViewHolder viewHolder;
    private int lastPosition = -1;

    public savedEventListAdapter(Context context, ArrayList<String> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public savedEventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_events, parent);
        viewHolder = new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(savedEventListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView eventTile;
        Button delete;

        public ViewHolder(View itemView) {
            super(itemView);
            eventTile = itemView.findViewById(R.id.event_title);
            delete = itemView.findViewById(R.id.task_delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
