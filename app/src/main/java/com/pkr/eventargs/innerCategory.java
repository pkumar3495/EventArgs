package com.pkr.eventargs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class innerCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Spinner bspinner = findViewById(R.id.spinnerBirthday);
        Spinner mspinner = findViewById(R.id.spinnerMarriage);
        Spinner pspinner = findViewById(R.id.spinnerParties);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Types, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bspinner.setAdapter(adapter);
        mspinner.setAdapter(adapter);
        pspinner.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
