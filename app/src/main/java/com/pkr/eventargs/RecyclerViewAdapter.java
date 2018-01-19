package com.pkr.eventargs;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Prashant on 11/15/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> category;
    private int[] categoryImage;
    private View view;
    private ViewHolder viewHolder;
    private int lastPosition = -1;

    public RecyclerViewAdapter (Context context, ArrayList<String> category, int[] categoryImage){
        this.context = context;
        this.category = category;
        this.categoryImage = categoryImage;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.cat_single_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(category.get(position));
        holder.image.setImageResource(categoryImage[position]);
        setAnimation(holder.itemView, position);


    }

    @Override
    public int getItemCount() {
        return category.size();
    }

//    public void updateItems(List<Data> newItems) {
//        mDataList.clear();
//        mDataList.addAll(newItems);
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CardView card;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.single_item_card);
            textView = itemView.findViewById(R.id.single_item_text);
            image = itemView.findViewById(R.id.category_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    String name = textView.getText().toString();
                    Toast.makeText(context, "Position : " + pos + " Name : " + name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setAnimation(View viewToAnimate, int position){
        if (position > lastPosition) {
            lastPosition = position;
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
            viewToAnimate.startAnimation(animation);
        }
    }
}
