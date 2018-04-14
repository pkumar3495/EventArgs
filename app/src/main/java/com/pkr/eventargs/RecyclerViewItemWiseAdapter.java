package com.pkr.eventargs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PK on 4/14/2018.
 */

public class RecyclerViewItemWiseAdapter extends RecyclerView.Adapter<RecyclerViewItemWiseAdapter.ViewHolder> {

    ArrayList<String> items;
    ArrayList<String> itemsEV;
    int[] itemsImage;
    Context context;
    View view;
    ViewHolder viewHolder;

    public RecyclerViewItemWiseAdapter(Context context, ArrayList<String> items, ArrayList<String> itemsEV, int[] itemsImage) {
        this.items = items;
        this.itemsEV = itemsEV;
        this.itemsImage = itemsImage;
        this.context = context;
    }

    @Override
    public RecyclerViewItemWiseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_wise_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewItemWiseAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(items.get(position));
        holder.shopImageItem.setImageResource(itemsImage[position]);
        holder.eventHandlerName.setText(itemsEV.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        ImageView shopImageItem;
        TextView eventHandlerName;
        TextView itemsAvailableItem;

        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_wise_name);
            shopImageItem = itemView.findViewById(R.id.shop_image_item);
            eventHandlerName = itemView.findViewById(R.id.shop_title_item);
            itemsAvailableItem = itemView.findViewById(R.id.items_available_item);

        }
    }
}
