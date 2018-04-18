package com.earthsilen.depthfirsttraineeproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateList extends AppCompatActivity {

    Button create, cancel;
    EditText dateChoose;
    int mHour;
    int mMinute;
    int mYear;
    int mMonth;
    int mDay;
    String date_time = "";
    int val;
    String fromDateSendApi, toDateSendApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        initView();


        dateChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();

                val = 1;
            }
        });


    }


    private void initView() {
        create = (Button) findViewById(R.id.btn_create);
        cancel = (Button) findViewById(R.id.btn_cancel);

        dateChoose = (EditText) findViewById(R.id.edt_date);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();

                val = 1;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void datePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //*************Call Time Picker Here ********************
                        timePicker();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void timePicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        if (val == 1) {
                            dateChoose.setText(date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute));
                            fromDateSendApi = date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute);
                            Log.d("fromd", fromDateSendApi);
                        }


                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();


    }
}
