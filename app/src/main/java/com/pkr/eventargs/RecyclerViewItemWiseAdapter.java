package com.pkr.eventargs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Boolean ifChecked;

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
    public void onBindViewHolder(final RecyclerViewItemWiseAdapter.ViewHolder holder, int position) {
        final Model model = new Model(items.get(position));
        holder.itemName.setText(items.get(position));
        holder.shopImageItem.setImageResource(itemsImage[position]);
        holder.eventHandlerName.setText(itemsEV.get(position));
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (model.getChecked()){
//                    Log.e("Shop : ", holder.eventHandlerName.getText().toString() + " is unchecked " + "within " + holder.itemName.getText().toString());
                    Toast.makeText(context, "shop : " + holder.eventHandlerName.getText().toString() + "\nunder : " + holder.itemName.getText().toString() + " is unchecked", Toast.LENGTH_SHORT).show();
                    model.setChecked(false);
                    listOfItemsInTemplate.countOfChecked--;
                    listOfItemsInTemplate.optionsTopBar(listOfItemsInTemplate.countOfChecked);
                }
                else if (!model.getChecked()){
//                    Log.e("Shop : ", holder.eventHandlerName.getText().toString() + " is checked "  + "within " + holder.itemName.getText().toString());
                    Toast.makeText(context, "shop : " + holder.eventHandlerName.getText().toString() + "\nunder : " + holder.itemName.getText().toString() + " is checked", Toast.LENGTH_SHORT).show();
                    model.setChecked(true);
                    listOfItemsInTemplate.countOfChecked++;
                    listOfItemsInTemplate.optionsTopBar(listOfItemsInTemplate.countOfChecked);
                }
            }
        });
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
        CheckBox check;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_wise_name);
            shopImageItem = itemView.findViewById(R.id.shop_image_item);
            eventHandlerName = itemView.findViewById(R.id.shop_title_item);
            itemsAvailableItem = itemView.findViewById(R.id.items_available_item);
            check = itemView.findViewById(R.id.item_check);
            context = itemView.getContext();

        }
    }
}
