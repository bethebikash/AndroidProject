package com.bhattaraibikash.erepair.activities.booking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.MainActivity;
import com.bhattaraibikash.erepair.bll.BookingBLL;
import com.bhattaraibikash.erepair.models.Booking;
import com.bhattaraibikash.erepair.strictmode.StrictModeClass;
import com.bhattaraibikash.erepair.url.Url;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private EditText etProblem, etDate, etTime, etLocation;
    private Button btnConfirmBook;
    private Button btnTime1, btnTime2, btnTime3, btnTime4, btnTime5, btnTime6;
    private String serviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getSupportActionBar().setTitle("Service Book");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etProblem = findViewById(R.id.etProblem);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etDate.setKeyListener(null);
        etTime.setKeyListener(null);
        etLocation = findViewById(R.id.etLocation);
        btnConfirmBook = findViewById(R.id.btnConfirmBook);
        btnTime1 = findViewById(R.id.btnTime1);
        btnTime2 = findViewById(R.id.btnTime2);
        btnTime3 = findViewById(R.id.btnTime3);
        btnTime4 = findViewById(R.id.btnTime4);
        btnTime5 = findViewById(R.id.btnTime5);
        btnTime6 = findViewById(R.id.btnTime6);


        Bundle extra = getIntent().getExtras();
        if (!extra.isEmpty()) {
            serviceId = extra.getString("_id");
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });

        btnTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTime.setText(R.string.time0608);
            }
        });

        btnTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTime.setText(R.string.time0810);
            }
        });

        btnTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTime.setText(R.string.time1012);
            }
        });

        btnTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTime.setText(R.string.time1202);
            }
        });

        btnTime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTime.setText(R.string.time0204);
            }
        });
        btnTime6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTime.setText(R.string.time0406);
            }
        });

        btnConfirmBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    serviceBook();
                }
            }
        });
    }

    private boolean validate() {
        boolean status;
        if (etProblem.getText().toString().isEmpty()) {
            etProblem.setError("This field is Required");
            etProblem.requestFocus();
            status = false;
        } else if (etDate.getText().toString().isEmpty()) {
            etDate.setError("Date is Required");
            etDate.requestFocus();
            status = false;
        } else if (etTime.getText().toString().isEmpty()) {
            etTime.setError("Time is Required");
            etTime.requestFocus();
            status = false;
        } else if (etLocation.getText().toString().isEmpty()) {
            etLocation.setError("Location is Required");
            etLocation.requestFocus();
            status = false;
        } else {
            status = true;
        }

        return status;
    }

    private void loadDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                etDate.setText(date);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    private void serviceBook() {
        String problem = etProblem.getText().toString();
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        String location = etLocation.getText().toString();

        Booking booking = new Booking(problem, date, time, location, serviceId);
        BookingBLL bookingBLL = new BookingBLL();

        StrictModeClass.StrictMode();
        if (bookingBLL.booking(Url.token, booking)) {
            Toast.makeText(this, "Booking Successful.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BookingActivity.this, MainActivity.class);
            intent.putExtra("from", "Booking");
            startActivity(intent);
        } else {
            Toast.makeText(this, "Booking Failed.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
