package com.pkr.eventargs;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class listOfItemsInTemplate extends AppCompatActivity {

    Button proceed_but, add_but;
    EditText dialog_add_text;

    Dialog myDialog;
    Dialog locationDialog;
    TextView locationText;
    ImageView locationImage, locationDetectImage;
    String address;
    double latitude, longitude;
    int[] shopImage = {R.drawable.sample, R.drawable.sample, R.drawable.sample, R.drawable.sample};
    ArrayList<String> itemsShop = new ArrayList<>(Arrays.asList("Shop1", "shop2", "Shop3", "Shop4"));
    ArrayList<String> itemsEventHandlers = new ArrayList<>(Arrays.asList("EV1", "EV2", "EV3", "EV4"));
    ArrayList<String> items = new ArrayList<>(Arrays.asList("item1", "item2", "item3", "item4"));

    LinearLayout recyclerContainer;

    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    LocationManager locationManager;

    RecyclerView recyclerViewShop;
    RecyclerView recyclerViewEventHandler;
    RecyclerView recyclerViewItem;
    RecyclerView.LayoutManager layoutManagerShop;
    RecyclerView.LayoutManager layoutManagerEventHandler;
    RecyclerView.LayoutManager layoutManagerItem;
    TemplateRecyclerViewAdapter recyclerViewAdapterShop;
    TemplateRecyclerViewAdapter recyclerViewAdapterEventHandler;
    RecyclerViewItemWiseAdapter recyclerViewAdapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items_in_template);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        recyclerViewShop = findViewById(R.id.items_to_buy_listView_top_results);

        recyclerViewEventHandler = findViewById(R.id.items_to_buy_listView_top_event_handlers);

        recyclerViewItem = findViewById(R.id.item_wise_recycler_view);

        recyclerContainer = findViewById(R.id.recycler_container_layout);
//        locationText = findViewById(R.id.searched_address);
//        locationImage = findViewById(R.id.location_icon);
//        locationDetectImage = findViewById(R.id.location_detect_icon);

//        itemsToBuyListView = findViewById(R.id.items_to_buy_listView);
//        itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view, R.id.template_list_item, items);
//        itemsToBuyListView.setAdapter(itemsAdapter);

        myDialog = new Dialog(this);
        locationDialog = new Dialog(this);

//        add_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDialog.setContentView(R.layout.add_dialog);
//                dialog_add_text = myDialog.findViewById(R.id.add_item_edit_text);
//                myDialog.setCancelable(false);
////                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                myDialog.show();
//            }
//        });

//        SwipeDismissListViewTouchListener touchListener =
//                new SwipeDismissListViewTouchListener(
//                        itemsToBuyListView,
//                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
//                            @Override
//                            public boolean canDismiss(int position) {
//                                return true;
//                            }
//
//                            @Override
//                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
//                                for (int position : reverseSortedPositions) {
//
//                                    items.remove(position);
//                                }
//
//                            }
//                        });
//        itemsToBuyListView.setOnTouchListener(touchListener);

        runAnimationShop(recyclerViewShop, 0);
        runAnimationEventHandler(recyclerViewEventHandler, 0);
        runAnimationItem(recyclerViewItem, 0);
    }

    private void runAnimationShop(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_up_from_bottom);
        }

        layoutManagerShop = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerShop);
        recyclerViewAdapterShop = new TemplateRecyclerViewAdapter(this, itemsShop, shopImage);
        recyclerView.setAdapter(recyclerViewAdapterShop);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void runAnimationEventHandler(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_up_from_bottom);
        }

        layoutManagerEventHandler = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerEventHandler);
        recyclerViewAdapterEventHandler = new TemplateRecyclerViewAdapter(this, itemsEventHandlers, shopImage);
        recyclerView.setAdapter(recyclerViewAdapterEventHandler);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void runAnimationItem(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_up_from_bottom);
        }

        layoutManagerItem = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerItem);
        recyclerViewAdapterItem = new RecyclerViewItemWiseAdapter(this, items, itemsShop, shopImage);
        recyclerView.setAdapter(recyclerViewAdapterItem);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

        recyclerContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, convertDpToPixels(150) * items.size()));
    }

    public static int convertDpToPixels(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        Log.e("Results", "px : " + px );
        return Math.round(px);
    }

    public void dialogCancel(View view){
        Log.e("dialog", "Cancel clicked !");
        myDialog.dismiss();
    }
    public void dialogAdd(View view){
        Log.e("dialog", "Add clicked !");
//        items.add(dialog_add_text.getText().toString());
//        itemsAdapter.notifyDataSetChanged();
        Log.e("Add Button", "Text : " + String.valueOf(dialog_add_text.getText()));
        myDialog.dismiss();
    }

    public void locationDialogOK(View view){
        locationDialog.dismiss();
    }

    public void location(View view){
        try {
            Intent intent =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                // retrive the data by using getPlace() method.
//                Place place = PlaceAutocomplete.getPlace(this, data);
////                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
//
//                locationText.setText(place.getName() + ",\n" +
//                                place.getAddress() + "\n" + place.getPhoneNumber());
//
//                LatLng latLng = place.getLatLng();
//                latitude = latLng.latitude;
//                longitude = latLng.longitude;
//
//                locationText.setBackgroundColor(getResources().getColor(R.color.locationEntered));
//
//                locationImage.setBackgroundColor(getResources().getColor(R.color.locationEntered));
//
//                locationDetectImage.setBackgroundColor(getResources().getColor(R.color.locationEntered));
//
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this, data);
//                // TODO: Handle the error.
//                Log.e("Tag", status.getStatusMessage());
//
//            } else if (resultCode == RESULT_CANCELED) {
//                // The user canceled the operation.
//            }
//        }
//    }
//    public void detectMyLocation(View view){
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSION_ACCESS_COARSE_LOCATION);
//        }else {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
////                    TextView textView = findViewById(R.id.searched_address);
//                    Log.e("Location","Changed");
//                    latitude = location.getLatitude();
//                    longitude = location.getLongitude();
////                    ((TextView) findViewById(R.id.searched_address))
////                            .setText(location.getLatitude() + " , " + location.getLongitude());
//                    try{
//                        Geocoder geo = new Geocoder(listOfItemsInTemplate.this.getApplicationContext(), Locale.getDefault());
//                        List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
//                        if (addresses.isEmpty()){
//                            textView.setText("Waiting for location");
//                        }
//                        else{
//                            if (addresses.size()>0){
//                                address = addresses.get(0).getFeatureName() + ", "
//                                        + addresses.get(0).getLocality() + ", "
//                                        +addresses.get(0).getAdminArea() + ", "
//                                        +addresses.get(0).getCountryName();
//                                textView.setText(address);
//
//                                locationText.setBackgroundColor(getResources().getColor(R.color.locationEntered));
//
//                                locationImage.setBackgroundColor(getResources().getColor(R.color.locationEntered));
//
//                                locationDetectImage.setBackgroundColor(getResources().getColor(R.color.locationEntered));
//
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onStatusChanged(String s, int i, Bundle bundle) {
//                    Log.d("Latitude","status");
//                }
//
//                @Override
//                public void onProviderEnabled(String s) {
//                    Log.d("Latitude","enable");
//                }
//
//                @Override
//                public void onProviderDisabled(String s) {
//                    Log.d("Latitude","disable");
//                }
//            });
//
//        }
//    }
}
