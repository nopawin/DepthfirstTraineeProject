package com.earthsilen.depthfirsttraineeproject.DetailFromContact;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.BitmapConfiguration.CircleTransform;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.WebTeamModels;
import com.earthsilen.depthfirsttraineeproject.R;
import com.jackandphantom.blurimage.BlurImage;

import java.io.InputStream;

public class ContactDetail extends AppCompatActivity {

    ImageView imgCover, imgPro, imgMap;
    TextView name, surname, department, phoneNumber, email, location;
    WebTeamModels webTeamModels;
    Bitmap imageCoverShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        onGetData();
        initView();
        setView();
        setMap();
    }

    private void initView(){
        //Binding image view
        imgCover = (ImageView) findViewById(R.id.img_cover);
        imgPro = (ImageView) findViewById(R.id.img_pro_pic);
        imgMap = (ImageView) findViewById(R.id.img_map);

        //Binding text view
        name = (TextView) findViewById(R.id.txt_name);
        surname = (TextView) findViewById(R.id.txt_surname);
        department = (TextView) findViewById(R.id.txt_department);
        phoneNumber = (TextView) findViewById(R.id.txt_phone_number);
        email = (TextView) findViewById(R.id.txt_email);
        location = (TextView) findViewById(R.id.txt_location);
    }

    private void setView(){
        //Set image view

//        BlurImage.with(getApplicationContext()).load(webTeamModels.getImgurl()).intensity(5).Async(true).into(imgCover);
        new getImageFromURL().execute(webTeamModels.getImgurl());
        Glide.with(this).load(webTeamModels.getImgurl()).transform(new CircleTransform(ContactDetail.this)).into(imgPro);


        //Set text view
        name.setTextSize(20);
        name.setText(webTeamModels.getName());

//        surname.setTextSize(16);
//        surname.setText(webTeamModels.getName());

        department.setTextSize(20);
        department.setText(webTeamModels.getRank());
    }

    private void setMap(){
        //Set map
        String GSMApi = "https://maps.google.com/maps/api/staticmap?center=14.4726547,100.12154169999997&markers=icon:http://tinyurl.com/2ftvtt6%7C14.4726547,100.12154169999997&zoom=16&size=680x380&sensor=false&scale=1";
        Glide.with(getApplicationContext()).load(GSMApi).into(imgMap);
    }

    private void onGetData(){
        Intent i = getIntent();
        webTeamModels = (WebTeamModels) i.getSerializableExtra("contactdetails");
    }

    public class getImageFromURL extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            BlurImage.with(getApplicationContext()).load(bitmap).intensity(5).Async(true).into(imgCover);
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urlDisplay = url[0];
            imageCoverShow = null;
            try{
                InputStream srt = new java.net.URL(urlDisplay).openStream();
                imageCoverShow = BitmapFactory.decodeStream(srt);
            } catch(Exception e){
                e.printStackTrace();
            }
            return imageCoverShow;
        }


    }
}
