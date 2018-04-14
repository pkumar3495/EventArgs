package com.pkr.eventargs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PK on 4/14/2018.
 */

public class TemplateRecyclerViewAdapter extends RecyclerView.Adapter<TemplateRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> items;
    int[] itemsImage;
    Context context;
    View view;
    ViewHolder viewHolder;

    public TemplateRecyclerViewAdapter(Context context, ArrayList<String> items, int[] itemsImage) {
        this.items = items;
        this.context = context;
        this.itemsImage = itemsImage;
    }

    @Override
    public TemplateRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TemplateRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.shopImage.setImageResource(itemsImage[position]);
        holder.shopTitle.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView shopImage;
        TextView shopTitle;
        TextView itemsAvailable;

        public ViewHolder(View itemView) {
            super(itemView);

            shopImage = itemView.findViewById(R.id.shop_image);
            shopTitle = itemView.findViewById(R.id.shop_title);
            itemsAvailable = itemView.findViewById(R.id.items_available);

        }
    }
}
