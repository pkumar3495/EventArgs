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

public class TemplateRecyclerViewAdapterEv extends RecyclerView.Adapter<TemplateRecyclerViewAdapterEv.ViewHolder> {

    ArrayList<String> items;
    int[] itemsImage;
    Context context;
    View view;
    ViewHolder viewHolder;
    Boolean ifChecked;

    public TemplateRecyclerViewAdapterEv(Context context, ArrayList<String> items, int[] itemsImage) {
        this.items = items;
        this.context = context;
        this.itemsImage = itemsImage;
    }

    @Override
    public TemplateRecyclerViewAdapterEv.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.list_view_ev, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TemplateRecyclerViewAdapterEv.ViewHolder holder, int position) {
        final Model model = new Model(items.get(position));
        holder.shopImage.setImageResource(itemsImage[position]);
        holder.shopTitle.setText(items.get(position));
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (model.getChecked()){
//                    Log.e("Shop : ", holder.shopTitle.getText().toString() + " is unchecked");
                    Toast.makeText(context, "EV : " + holder.shopTitle.getText().toString() + " is unchecked", Toast.LENGTH_SHORT).show();
                    model.setChecked(false);
                    listOfItemsInTemplate.countOfChecked--;
                    listOfItemsInTemplate.optionsTopBar(listOfItemsInTemplate.countOfChecked);
                }
                else if (!model.getChecked()){
//                    Log.e("Shop : ", holder.shopTitle.getText().toString() + " is checked");
                    Toast.makeText(context, "EV : " + holder.shopTitle.getText().toString() + " is checked", Toast.LENGTH_SHORT).show();
                    model.setChecked(true);
                    listOfItemsInTemplate.countOfChecked++;
                    listOfItemsInTemplate.optionsTopBar(listOfItemsInTemplate.countOfChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView shopImage;
        TextView shopTitle;
        TextView itemsAvailable;
        CheckBox check;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);

            shopImage = itemView.findViewById(R.id.shop_image);
            shopTitle = itemView.findViewById(R.id.shop_title);
            itemsAvailable = itemView.findViewById(R.id.items_available);
            check = itemView.findViewById(R.id.ev_check);
            context = itemView.getContext();
        }
    }
}
