package com.earthsilen.depthfirsttraineeproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LeaveCreate extends AppCompatActivity {

    String leavereason;
    RadioButton rbsick, rbbusiness, rbetc, rbDivide;
    RadioGroup rgOperator;
    Button btntrb, btncancel;
    TextView etcshow;
    Calendar myCalendar;
    int mHour;
    int mMinute;
    int mYear;
    int mMonth;
    int mDay;
    String date_time = "";
    String fromDateSendApi, toDateSendApi;


    int val;
    EditText fromDateEdt, toDateEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_create);
        initButton();
        initTextView();
        radioChooseButton();
        initBtnDoEvent();
        initEdtView();
        myCalendar = Calendar.getInstance();


//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                new DatePickerDialog(LeaveCreate.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//                updateLabel(val);
//
//            }
//
//        };



        fromDateEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                datePicker();

                val = 1;

            }
        });
        toDateEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                new DatePickerDialog(LeaveCreate.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                datePicker();

                val = 2;

            }
        });


    }

    public void initEdtView() {

        fromDateEdt = (EditText) findViewById(R.id.edt_fromdate);
        toDateEdt = (EditText) findViewById(R.id.edt_todate);
    }

    private void initButton() {

        rgOperator = (RadioGroup) findViewById(R.id.rgoperator);
        rbsick = (RadioButton) findViewById(R.id.rbsick);
        rbbusiness = (RadioButton) findViewById(R.id.rbbusiness);
        rbetc = (RadioButton) findViewById(R.id.rbetc);
        btncancel = (Button) findViewById(R.id.btn_cancel);
    }

    private void initBtnDoEvent() {
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTextView() {
        etcshow = (TextView) findViewById(R.id.txt_etcshow);
    }

    private void radioChooseButton() {

        final RadioGroup radio = (RadioGroup) findViewById(R.id.rgoperator);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                // Add logic here

                switch (rgOperator.getCheckedRadioButtonId()) {
                    case R.id.rbsick: // first button

                        leavereason = "Sick";
                        Toast.makeText(LeaveCreate.this, leavereason, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbbusiness: // secondbutton

                        leavereason = "Business";
                        Toast.makeText(LeaveCreate.this, leavereason, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbetc: // secondbutton

                        AlertDialog.Builder dialog = new AlertDialog.Builder(LeaveCreate.this);
                        final EditText edittext = new EditText(LeaveCreate.this);

                        dialog.setTitle("Please fill your other reason");

                        dialog.setView(edittext);

                        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //What ever you want to do with the value
                                //Editable YouEditTextValue = edittext.getText();
                                //OR
                                leavereason = edittext.getText().toString();
                                Toast.makeText(LeaveCreate.this, leavereason, Toast.LENGTH_SHORT).show();
                                etcshow.setTextColor(Color.WHITE);
                                etcshow.setText("Reason: " + leavereason);
                            }
                        });

                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });

                        dialog.show();
                        break;
                }
            }
        });
    }

//    private void updateLabel(int val) {
//
//        String myFormat = "dd/MM/yy"; //In which you need put here
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        if (val == 1) {
//            fromDateEdt.setText(date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute));
//            fromDateSendApi = date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute);
//            Log.d("fromd", fromDateSendApi);
//        }
//        else {
//            toDateEdt.setText(date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute));
//            toDateSendApi = sdf.format(myCalendar.getTime()) + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute);
//            Log.d("tod",toDateSendApi);
//        }
//
//    }

    private void timePicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        if(val == 1){
                            fromDateEdt.setText(date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute));
                            fromDateSendApi = date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute);
                            Log.d("fromd", fromDateSendApi);
                        }else if(val == 2){
                            toDateEdt.setText(date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute));
                            toDateSendApi = date_time + " " + String.valueOf(mHour) + ":" + String.valueOf(mMinute);
                            Log.d("tod",toDateSendApi);
                        }



                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();


    }

    private void datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //*************Call Time Picker Here ********************
                        timePicker();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


}



