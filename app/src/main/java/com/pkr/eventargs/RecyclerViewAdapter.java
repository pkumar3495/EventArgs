package com.pkr.eventargs;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.mancj.slideup.SlideUp;

import java.util.ArrayList;
import java.util.Arrays;

import static com.pkr.eventargs.MainActivity.adapter;
import static com.pkr.eventargs.MainActivity.typeList;
import static com.pkr.eventargs.MainActivity.values1;

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
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(category.get(position));
        holder.image.setImageResource(categoryImage[position]);
    }

    @Override
    public int getItemCount() {
        return category.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CardView card;
        ImageView image;

        public ViewHolder(final View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.single_item_card);
            textView = itemView.findViewById(R.id.single_item_text);
            image = itemView.findViewById(R.id.category_image);

            MainActivity.slideUp = new SlideUp(MainActivity.slideView);
            MainActivity.slideUp.hideImmediately();

            MainActivity.slideUp.setSlideListener(new SlideUp.SlideListener() {
                @Override
                public void onSlideDown(float v) {
                    MainActivity.dim.setAlpha(1 - (v / 100));
                }

                @Override
                public void onVisibilityChanged(int i) {
                    if (i == View.GONE) {
                        MainActivity.menu.setVisibility(View.VISIBLE);
                        MainActivity.typesLayout.setBackgroundColor(itemView.getResources().getColor(R.color.listBackgroundDefault));
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (MainActivity.menu.getVisibility() == View.VISIBLE) {
                        Arrays.fill(values1, null);
                        switch (textView.getText().toString()){
                            case "Social" : values1 = new String[] {"Kid's birthday", "Adult's birthday", "Chutiya's birthday"};break;
                            case "Tech" : values1 = new String[] {"Workshop", "Session", "Ted Talks", "Chutiyapa meeting"};break;
                            case "Trips" : values1 = new String[] {"Picnic", "Road trip", "Site seeing", "Bird watching", "Chai pine", "Badminton khelne", "Road trip", "Site seeing", "Bird watching", "Chai pine", "Badminton khelne", "Road trip", "Site seeing", "Bird watching", "Chai pine", "Badminton khelne"};break;
                            case "Cultural" : values1 = new String[] {"Cultura", "Idk cultural", "something cultural"};break;
                        }

                        adapter = new ArrayAdapter<String>(itemView.getContext(), R.layout.sub_type_item, R.id.type_single, values1);
                        typeList.setAdapter(adapter);
                        finishTypeCreation(itemView);
                    }
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
    public void finishTypeCreation (View v) {
        MainActivity.slideUp.animateIn();
        MainActivity.menu.setVisibility(View.GONE);
        MainActivity.typesLayout.setBackgroundColor(v.getResources().getColor(R.color.listBackground));
    }
}
