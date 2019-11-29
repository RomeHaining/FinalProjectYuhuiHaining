package com.example.finalprojectyuhuihaining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.prolificinteractive.materialcalendarview.CalendarUtils;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class addEventActivity extends AppCompatActivity {
    private static final String TAG = addEventActivity.class.getSimpleName();
    public List<addEvent> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        // toolbar back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // calendar choose
        final EditText editStart = findViewById(R.id.editStart);
        final EditText editEnd = findViewById(R.id.editEnd);
        editStart.setInputType(InputType.TYPE_NULL);
        editEnd.setInputType(InputType.TYPE_NULL);
        editStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = CalendarUtils.getInstance();
                int startDay = CalendarUtils.getDay(calendar);
                int startMonth = CalendarUtils.getMonth(calendar);
                int startYear = CalendarUtils.getYear(calendar);
                DatePickerDialog picker = new DatePickerDialog(addEventActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            editStart.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            editEnd.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                        }
                    }, startYear, startMonth, startDay);
                picker.show();
            }
        });
        editEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = CalendarUtils.getInstance();
                int endDay = CalendarUtils.getDay(calendar);
                int endMonth = CalendarUtils.getMonth(calendar);
                int endYear = CalendarUtils.getYear(calendar);
                DatePickerDialog picker = new DatePickerDialog(addEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editEnd.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        }, endYear, endMonth, endDay);
                picker.show();
            }
        });
        // location autocomplete
        String apiKey = getString(R.string.api_key);
        Places.initialize(getApplicationContext(), apiKey);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG, Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        // save button
        Button otherSave = findViewById(R.id.otherSave);
        TextView title = findViewById(R.id.editTitle);
        TextView location = findViewById(R.id.textView);
        otherSave.setOnClickListener(unused -> {
            addEvent event = new addEvent("other", title.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), location.getText().toString());
            MaterialCalendarView calendarView = findViewById(R.id.calendarView);
            title.setText("");
            editStart.setText("");
            editEnd.setText("");
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
