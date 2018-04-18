package com.earthsilen.depthfirsttraineeproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.earthsilen.depthfirsttraineeproject.Models.WebTeamModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button skip, login;
    EditText uLogin, pLogin;
    //String username, password;
    String url;
    ProgressDialog dialog;
    private static final String MY_PREFS = "my_prefs";
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initButton();
        shared = getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);


    }

    public void initView() {
        uLogin = (EditText) findViewById(R.id.ulogin);
        pLogin = (EditText) findViewById(R.id.plogin);
        login = (Button) findViewById(R.id.btn_login);
    }

    public void initButton() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFunc();
            }
        });
    }

    public void loginFunc() {

//        String username = uLogin.getText().toString();
//        String password = pLogin.getText().toString();
        String url = String.format("http://sarabun.dol.go.th/json/documentService?cmd=login");
        //Connect to API
        //GET API
        //GET API from server
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
        Log.d("test", url);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                Log.d("TEST", string);
                checkLogin(string);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }) {

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String username = uLogin.getText().toString();
                String password = pLogin.getText().toString();
                params.put("login", username);
                params.put("password", password);

                return params;
            }

            ;
        };
        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(request);
    }

    public void checkLogin(String jsonString) {
        try {
            //JSONObject data = new JSONObject(jsonString);
//            ArrayList al = new ArrayList();
//
//            for(int i = 0; i < data.length(); ++i) {

            JSONObject c = new JSONObject(jsonString);
            Boolean loginStatus = c.getBoolean("status");
//                String loginToken = c.getString("token");
//            String notiMessage = c.getString("message");
////            SharedPreferences.Editor editor = shared.edit();
////            editor.putString("tokenKey", loginToken);
//            editor.commit();
            if (loginStatus == true) {
                String loginToken = c.getString("token");
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("tokenKey", loginToken);
                editor.commit();

                Intent s = new Intent(Login.this, Homepage.class);
                startActivity(s);
            } else if (loginStatus == false) {
                //Toast.makeText(this, "Your username or password is incorrect", Toast.LENGTH_SHORT).show();
                String notiMessage = c.getString("message");
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Warning");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setCancelable(true);
                dialog.setMessage(notiMessage);
//                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            moveTaskToBack(true);
//                            android.os.Process.killProcess(android.os.Process.myPid());
//                            System.exit(1);
//                            Login.super.onBackPressed();
//                        }
//                    });

                dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
            //insert in to
            //c.getString("ID")
            //c.getString("TOURISTNAME")
            //c.getString("TOURTYPE")
            //c.getString("DESCRIPTION")
//                mydb.insertToDB(c.getString("place_name"),c.getString("place_image"),c.getString("description"),c.getString("latitude"),
//                        c.getString("longtitude"), c.getString("open_time"), c.getString("close_time"), c.getString("phone"),
//                        c.getString("category"), c.getString("suggest_duration"));


            //}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        dialog.dismiss();

    }


}
