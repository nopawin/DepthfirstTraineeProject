package com.earthsilen.depthfirsttraineeproject.DetailFromContact;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.BitmapConfiguration.CircleTransform;
import com.earthsilen.depthfirsttraineeproject.Homepage;
import com.earthsilen.depthfirsttraineeproject.Login;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeTeamModels;
import com.earthsilen.depthfirsttraineeproject.Models.WebTeamModels;
import com.earthsilen.depthfirsttraineeproject.R;
import com.jackandphantom.blurimage.BlurImage;

import java.io.InputStream;

public class ContactDetail extends AppCompatActivity {

    ImageView imgCover, imgPro, imgMap;
    TextView name, surname, department, phoneNumber, email, location, nameShowTitle;
    WebTeamModels webTeamModels;
    TraineeTeamModels traineeTeamModels;
    Bitmap imageCoverShow;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        onGetDataFromRecyclerView();
        initView();
        setView();
        setMap();
    }

    private void initView() {

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //Binding image view
//        imgCover = (ImageView) findViewById(R.id.img_cover);
        imgPro = (ImageView) findViewById(R.id.img_pro_pic);
        imgMap = (ImageView) findViewById(R.id.img_map);

        //Binding text view
        nameShowTitle = (TextView)findViewById(R.id.txt_name_show);
        name = (TextView) findViewById(R.id.txt_name);
        surname = (TextView) findViewById(R.id.txt_surname);
        department = (TextView) findViewById(R.id.txt_department);
        phoneNumber = (TextView) findViewById(R.id.txt_phone_number);
        email = (TextView) findViewById(R.id.txt_email);
        location = (TextView) findViewById(R.id.txt_location);

    }

    private void setView() {
        //Set image view

//        BlurImage.with(getApplicationContext()).load(webTeamModels.getImgurl()).intensity(5).Async(true).into(imgCover);
//        new getImageFromURL().execute(webTeamModels.getImgurl());
        if(webTeamModels != null) {
            Glide.with(this).load(webTeamModels.getImgurl()).into(imgPro);


            //Set text view

            nameShowTitle.setTextSize(24);
            nameShowTitle.setTextColor(Color.WHITE);
            nameShowTitle.setText(webTeamModels.getName());

            name.setTextSize(20);
            name.setText(webTeamModels.getName());

//        surname.setTextSize(16);
//        surname.setText(webTeamModels.getName());

            department.setTextSize(20);
            department.setText(webTeamModels.getRank());

            onMakePhoneCall();
            onSendEmail();
        } else if(traineeTeamModels != null){
            Glide.with(this).load(traineeTeamModels.getImgurl()).into(imgPro);


            //Set text view
            nameShowTitle.setTextSize(24);
            nameShowTitle.setTextColor(Color.WHITE);
            nameShowTitle.setText(traineeTeamModels.getName());


            name.setTextSize(20);
            name.setText(traineeTeamModels.getName());

//        surname.setTextSize(16);
//        surname.setText(webTeamModels.getName());

            department.setTextSize(20);
            department.setText(traineeTeamModels.getRank());

            onMakePhoneCall();
            onSendEmail();
        }

    }

    private void setMap() {
        //Set map
        String GSMApi = "https://maps.google.com/maps/api/staticmap?center=14.4726547,100.12154169999997&markers=icon:http://tinyurl.com/2ftvtt6%7C14.4726547,100.12154169999997&zoom=16&size=680x380&sensor=false&scale=1";
        Glide.with(getApplicationContext()).load(GSMApi).into(imgMap);
    }

    private void onGetDataFromRecyclerView() {
        Intent i = getIntent();
        if(i.hasExtra("contactdetailsweb")) {
            webTeamModels = (WebTeamModels) i.getSerializableExtra("contactdetailsweb");
        }else if(i.hasExtra("contactdetailstrainee")) {
            traineeTeamModels = (TraineeTeamModels) i.getSerializableExtra("contactdetailstrainee");
        }
    }

//    public class getImageFromURL extends AsyncTask<String, Void, Bitmap> {
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            BlurImage.with(getApplicationContext()).load(bitmap).intensity(5).Async(true).into(imgCover);
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... url) {
//            String urlDisplay = url[0];
//            imageCoverShow = null;
//            try {
//                InputStream srt = new java.net.URL(urlDisplay).openStream();
//                imageCoverShow = BitmapFactory.decodeStream(srt);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return imageCoverShow;
//        }
//
//
//    }

    private void onSendEmail(){
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ContactDetail.this);
                dialog.setTitle("Send an email");
                dialog.setIcon(R.drawable.depth1st);
                dialog.setCancelable(true);
                dialog.setMessage("Are you sure for sending an email?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendEmail("earthsilen@gmail.com");
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.setNeutralButton("Copy to Clipboard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myClip = ClipData.newPlainText("text", "earthsilen@gmail.com");
                        myClipboard.setPrimaryClip(myClip);
                        Toast.makeText(ContactDetail.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();


            }
        });
    }

    private void onMakePhoneCall() {
        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ContactDetail.this);
                dialog.setTitle("Dial");
                dialog.setIcon(R.drawable.depth1st);
                dialog.setCancelable(true);
                dialog.setMessage("Are you sure for making a call?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialContactPhone("0881440366");
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.setNeutralButton("Copy to Clipboard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myClip = ClipData.newPlainText("text", "0881440366");
                        myClipboard.setPrimaryClip(myClip);
                        Toast.makeText(ContactDetail.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();


            }
        });
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    private void sendEmail(final String emailAddress){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto: "+emailAddress));
        startActivity(Intent.createChooser(emailIntent, "Send an email"));
    }
}
