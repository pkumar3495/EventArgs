package com.pkr.eventargs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.mancj.slideup.SlideUp;
import com.pkr.eventargs.db.EventDBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    com.github.clans.fab.FloatingActionButton menu1;
    com.github.clans.fab.FloatingActionButton add;
    String TAG = ".MainActivity";
    Context context = MainActivity.this;
    DatePicker datePicker;

    EditText searchEditText;
    Button searchBut;

    public static SlideUp slideUp;
    public static View dim;
    public static View slideView;

    static String[] values1;
    static LinearLayout typesLayout;
    static com.github.clans.fab.FloatingActionMenu menu;

    ArrayList<String> category = new ArrayList<>(Arrays.asList("Social", "Tech", "Trips", "Cultural", "Something", "Educational", "Live the design", "What's up yo"));
    int categoryImage[] = {R.drawable.social, R.drawable.tech, R.drawable.trip, R.drawable.cultural, R.drawable.birthday, R.drawable.birthday, R.drawable.birthday, R.drawable.birthday};

    EventDBHelper mHelper = new EventDBHelper(this);
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    static ListView typeList;

    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideView = findViewById(R.id.slideView);

//        slideView = findViewById(R.id.slideView);
//        dim = findViewById(R.id.dim);
        typesLayout = findViewById(R.id.types_layout);
        menu = findViewById(R.id.menu);

        typeList = findViewById(R.id.type_list);

        values1 = new String[]{
                "Kid's birthday",
                "Adult's birthday"
        };

        recyclerView = findViewById(R.id.category_list_view);

        datePicker = findViewById(R.id.date_picker);
        datePicker = new DatePicker(this);

        searchEditText = findViewById(R.id.search_String);
//        searchBut = findViewById(R.id.search_But);

        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int itemPosition = i;
                String itemValue = (String) typeList.getItemAtPosition(i);
//                Toast.makeText(getApplicationContext(),
//                        "Position : " + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();
                startActivity(new Intent(getApplicationContext(), listOfItemsInTemplate.class));
            }
        });

        menu1 = findViewById(R.id.menu_item1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MyEvents.class);
                startActivity(intent);
            }
        });

        add = findViewById(R.id.menu_item2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View v = inflater.inflate(R.layout.add_layout, null);
                final EditText name = findViewById(R.id.name);
//                final EditText type = findViewById(R.id.type);
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Add event")
                        .setView(v)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "" + datePicker.getDayOfMonth(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

//        searchBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                String searchString = searchEditText.getText().toString();
//                Toast.makeText(getApplicationContext(), "Searching for : " + searchString, Toast.LENGTH_SHORT).show();
//            }
//        });
        runAnimation(recyclerView, 0);
    }

    private void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_up_from_bottom);
        }

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(this, category, categoryImage);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void aboutPage(View view) {
        startActivity(new Intent(MainActivity.this, about.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.About) {
            Intent intent = new Intent(this, about.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if (menu.getVisibility() != View.VISIBLE) {
//            slideUp.animateOut();
            typesLayout.setBackgroundColor(getResources().getColor(R.color.listBackgroundDefault));
            slideView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.hide_options_dummy));
            slideView.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
            Arrays.fill(values1, null);
        } else {
            if (exit)
                finish(); //finish activity
            else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);
            }
        }
    }

    public void closebut(View view) {
//        slideUp.animateOut();
        typesLayout.setBackgroundColor(getResources().getColor(R.color.listBackgroundDefault));
        slideView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.hide_options_dummy));
        slideView.setVisibility(View.GONE);
        menu.setVisibility(View.VISIBLE);
        Arrays.fill(values1, null);
    }
}
