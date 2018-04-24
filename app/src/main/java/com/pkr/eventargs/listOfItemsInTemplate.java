package com.pkr.eventargs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.pkr.eventargs.db.EventContract;
import com.pkr.eventargs.db.EventDBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class listOfItemsInTemplate extends AppCompatActivity implements DateRangePickerFragment.OnDateRangeSelectedListener {

    Button proceed_but, add_but;
    EditText dialog_add_text, EventName;

    Dialog myDialog;
    Dialog locationDialog;
    TextView locationText, startDate, startTime;
    ImageView locationImage, locationDetectImage, optionsCreateCancel, optionCreateAdd;
    String address;
    double latitude, longitude;
    int[] shopImage = {R.drawable.sample, R.drawable.sample, R.drawable.sample, R.drawable.sample};
    ArrayList<String> itemsShop = new ArrayList<>(Arrays.asList("Shop1", "shop2", "Shop3", "Shop4"));
    ArrayList<String> itemsEventHandlers = new ArrayList<>(Arrays.asList("EV1", "EV2", "EV3", "EV4"));
    ArrayList<String> items = new ArrayList<>(Arrays.asList("item1", "item2", "item3", "item4"));
    Boolean selectionStarted = false;

    LinearLayout recyclerContainer;
    static LinearLayout checkedLayout;
    static LinearLayout fullContentLayout;
    EventDBHelper mHelper;

    String sday, smonth, syear, eday, emonth, eyear;

    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    LocationManager locationManager;

    RecyclerView recyclerViewShop;
    RecyclerView recyclerViewEventHandler;
    RecyclerView recyclerViewItem;
    RecyclerView.LayoutManager layoutManagerShop;
    RecyclerView.LayoutManager layoutManagerEventHandler;
    RecyclerView.LayoutManager layoutManagerItem;
    TemplateRecyclerViewAdapter recyclerViewAdapterShop;
    TemplateRecyclerViewAdapterEv recyclerViewAdapterEventHandler;
    RecyclerViewItemWiseAdapter recyclerViewAdapterItem;

    static int countOfChecked = 0;

    static Context context;

    Dialog dialog;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items_in_template);

        mHelper = new EventDBHelper(this);

        context = getApplicationContext();

        checkedLayout = findViewById(R.id.checked_layout);
        fullContentLayout = findViewById(R.id.full_content_layout);

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
        dialog = new Dialog(this);
        locationDialog = new Dialog(this);

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
        recyclerViewAdapterEventHandler = new TemplateRecyclerViewAdapterEv(this, itemsEventHandlers, shopImage);
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

        recyclerContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (convertDpToPixels(180) * items.size()) + convertDpToPixels(50)));
    }

    public static int convertDpToPixels(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        Log.e("Results", "px : " + px);
        return Math.round(px);
    }

    public void dialogCancel(View view) {
        Log.e("dialog", "Cancel clicked !");
        myDialog.dismiss();
    }

    public void dialogAdd(View view) {
        Log.e("dialog", "Add clicked !");
//        items.add(dialog_add_text.getText().toString());
//        itemsAdapter.notifyDataSetChanged();
        Log.e("Add Button", "Text : " + String.valueOf(dialog_add_text.getText()));
        myDialog.dismiss();
    }

    public void locationDialogOK(View view) {
        locationDialog.dismiss();
    }

//    public void location(View view){
//        try {
//            Intent intent =
//                    new PlaceAutocomplete
//                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
//                            .build(this);
//            startActivityForResult(intent, 1);
//        } catch (GooglePlayServicesRepairableException e) {
//            // TODO: Handle the error.
//        } catch (GooglePlayServicesNotAvailableException e) {
//            // TODO: Handle the error.
//        }
//    }

    public static void optionsTopBar(int count) {
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (count != 0) {
            checkedLayout.setVisibility(View.VISIBLE);
            checkedLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.show_options));
//            layoutParams.setMargins(0, 0, 0, 50);
        } else {
            checkedLayout.setVisibility(View.GONE);
            checkedLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.hide_options));
//            layoutParams.setMargins(0, 0, 0, 0);
        }
//        fullContentLayout.setLayoutParams(layoutParams);
    }


    public void optionsClick(View view) {
        switch (view.getId()) {
            case R.id.create_new_event:
                dialog.setContentView(R.layout.create_new_event_dialog);
                EventName = dialog.findViewById(R.id.event_name_in_dialog);
                startDate = dialog.findViewById(R.id.start_date);
                startTime = dialog.findViewById(R.id.tvSelectedTimeRangeFragment);
                dialog.setCancelable(true);
                dialog.show();
                TextView add = dialog.findViewById(R.id.create_option_add_but);
                TextView cancel = dialog.findViewById(R.id.create_option_cancel_but);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!EventName.getText().toString().equals("") && !startDate.getText().toString().equals("Select date") && !startTime.getText().toString().equals("Select time")) {
//                        Toast.makeText(getApplicationContext(), EventName.getText().toString() + " is added !", Toast.LENGTH_SHORT).show();
                            StyleableToast.makeText(listOfItemsInTemplate.this, EventName.getText().toString() + " is added to My events !", R.style.mytoast).show();
                            SQLiteDatabase db = mHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put(EventContract.EventEntry.COL_EVENT_TITLE, EventName.getText().toString());
                            values.put(EventContract.EventEntry.COL_S_DAY, sday);
                            values.put(EventContract.EventEntry.COL_S_MONTH, smonth);
                            values.put(EventContract.EventEntry.COL_S_YEAR, syear);
                            values.put(EventContract.EventEntry.COL_E_DAY, eday);
                            values.put(EventContract.EventEntry.COL_E_MONTH, emonth);
                            values.put(EventContract.EventEntry.COL_E_YEAR, eyear);
                            values.put(EventContract.EventEntry.COL_S_TIME, TimeRangeSelecterActivityFragment.startTime);
                            values.put(EventContract.EventEntry.COL_E_TIME, TimeRangeSelecterActivityFragment.endTime);
                            Log.e("Blah", "start : " + TimeRangeSelecterActivityFragment.startTime + " end : " + TimeRangeSelecterActivityFragment.endTime);
                            db.insertWithOnConflict(EventContract.EventEntry.TABLE,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE);
                            db.close();
                            dialog.dismiss();
                        }
                        if (EventName.getText().toString().equals("")) {
                            EventName.setError("Provide a name");
                        }
                        if (startDate.getText().toString().equals("Select date")) {
                            startDate.setError("Provide Date");
                        }
                        if (startTime.getText().toString().equals("Select time")){
                            startTime.setError("Provide Time");
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                startDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        new DatePickerDialog(listOfItemsInTemplate.this, date, myCalendar
//                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        DateRangePickerFragment dateRangePickerFragment = DateRangePickerFragment.newInstance(listOfItemsInTemplate.this, false);
                        dateRangePickerFragment.show(getFragmentManager(), "datePicker");
                    }
                });
                break;
            case R.id.add_to_existing:
                break;
        }
    }

    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
//        Log.d("range : ","from: "+startDay+"-"+startMonth+"-"+startYear+" to : "+endDay+"-"+endMonth+"-"+endYear );
//        Toast.makeText(listOfItemsInTemplate.this, "from: "+startDay+"-"+startMonth+"-"+startYear+" to : "+endDay+"-"+endMonth+"-"+endYear, Toast.LENGTH_SHORT).show();
        sday = String.valueOf(startDay);
        smonth = String.valueOf(startMonth);
        syear = String.valueOf(startYear);
        eday = String.valueOf(endDay);
        emonth = String.valueOf(endMonth);
        eyear = String.valueOf(endYear);
        startDate.setText(sday + "/" + smonth + "/" + syear + "  -  " + eday + "/" + emonth + "/" + eyear);
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
