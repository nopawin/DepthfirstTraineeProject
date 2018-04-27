package com.earthsilen.depthfirsttraineeproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    TextView changeAPI;
    ImageButton okBtn;
    private static final String MY_PREFS = "my_prefs";
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        initView();
        String APIPathValue = shared.getString("newAPIPath", null);
        if (APIPathValue != null) {

            Log.d("apipath", APIPathValue);
        }



    }


    private void initView(){
        shared = getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);
        changeAPI = (TextView)findViewById(R.id.txt_change_api);
        okBtn = (ImageButton)findViewById(R.id.imgbtn_ok);


        changeAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickChngeAPI();

            }
        });


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void OnClickChngeAPI(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(Setting.this);
        final EditText edittext = new EditText(Setting.this);

        dialog.setTitle("Please fill your new API Path");

        dialog.setView(edittext);

        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                //Editable YouEditTextValue = edittext.getText();
                //OR
                String NewAPIPath = edittext.getText().toString();
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("newAPIPath", NewAPIPath);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Changed", Toast.LENGTH_SHORT).show();

            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        dialog.show();

    }
}
