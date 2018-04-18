package com.earthsilen.depthfirsttraineeproject;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews.DFNews;

public class Homepage extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.DepthListNav:
                    News news = new News();
                    /**Bundle m = new Bundle();
                     m.putString("uname","xxxxx");

                     home.setArguments(m);**/
                    FragmentManager managerHome = getSupportFragmentManager();
                    managerHome.beginTransaction().replace(R.id.contentLayout,
                            news)
                            .commit();
                    return true;
                case R.id.DoListNav:
                    Dolist dolist = new Dolist();
                    FragmentManager managerCat = getSupportFragmentManager();
                    managerCat.beginTransaction().replace(R.id.contentLayout,
                            dolist)
                            .commit();
                    return true;
                case R.id.DoLeaveNav:
                    Leave leave = new Leave();
                    FragmentManager managerNear = getSupportFragmentManager();
                    managerNear.beginTransaction().replace(R.id.contentLayout,
                            leave)
                            .commit();
                    return true;
                case R.id.ContactNav:
                    Contacts contacts = new Contacts();
                    /**Bundle pro = new Bundle();
                     pro.putString("uname",pName);
                     pro.putString("ulast",pLastname);
                     pro.putString("uage",pAge);
                     pro.putString("ugender",pGender);
                     pro.putString("fname",faceName);
                     pro.putString("flast",faceLast);
                     pro.putString("fgen",faceGender);
                     pro.putString("fid", faceID);
                     pro.putString("fbirth",faceBirth);
                     contacts.setArguments(pro);**/
                    FragmentManager managerMyTrip = getSupportFragmentManager();
                    managerMyTrip.beginTransaction().replace(R.id.contentLayout,
                            contacts)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //request permission from users
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },PERMISSION_REQUEST_CODE);

        //GET TOKEN KEY FROM WELCOME SCREEN


        News news = new News();


        FragmentManager managerHome = getSupportFragmentManager();
        managerHome.beginTransaction().replace(R.id.contentLayout,
                news)
                .commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Depthfirst");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                Homepage.super.onBackPressed();
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }


}
