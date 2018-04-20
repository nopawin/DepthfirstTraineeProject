package com.earthsilen.depthfirsttraineeproject.DetailFromList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.ClickableViewPager;
import com.earthsilen.depthfirsttraineeproject.HTTPManager.HttpManagerGooglePlaceDetail;
import com.earthsilen.depthfirsttraineeproject.HTTPManager.HttpManagerGooglePlaceID;
import com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter.ImageViewPagerAdapterNewsDetails;
import com.earthsilen.depthfirsttraineeproject.ImageZoomFromDetails.ViewPagerZoomableDepthFirstNewsDetails;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.GooglePlaceDetailModel.PlaceDetailModel;
import com.earthsilen.depthfirsttraineeproject.Models.GooglePlaceIdModel.PlaceIDModel;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeNewsModels;
import com.earthsilen.depthfirsttraineeproject.R;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class NewsDetailsTrainee extends AppCompatActivity {


    ClickableViewPager viewPager;
    TextView showDetails, showTitle, showTime, showLocation;;
    ImageView imgMapShow;

    ArrayList<String> images = new ArrayList<String>();
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details_trainee);

        Intent i = getIntent();
        data = (Data) i.getSerializableExtra("test");

        for (int a = 0; a < data.getImageList().size(); a++){
            images.add(data.getImageList().get(a).getAttachFileName());
        }

//        showDetails.setText(depthfirstNewsModels.getDesc());
        //images = data.getImgurl();
        initView();
        loadPlaceID();
    }
    private void initView() {
        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showDetails = (TextView) findViewById(R.id.tdetail);
        showTitle = (TextView)findViewById(R.id.txt_title_detail);
        showTime = (TextView) findViewById(R.id.txt_time_detail);
        showLocation = (TextView) findViewById(R.id.txt_location);

        //Binding ImageView
        imgMapShow = (ImageView) findViewById(R.id.img_map);

        //Set map
        String GSMApi = "https://maps.google.com/maps/api/staticmap?center=" + data.getLatitude() + "," + data.getLongitude() +
                "&markers=icon:http://tinyurl.com/2ftvtt6%7C" + data.getLatitude() + "," + data.getLongitude() + "&zoom=16&size=680x380&sensor=false&scale=1";
        Glide.with(getApplicationContext()).load(GSMApi).into(imgMapShow);

        //Set text view with model
        showTitle.setTextColor(Color.BLACK);
        showTitle.setTextSize(17);
        showTitle.setTypeface(showTitle.getTypeface(), Typeface.BOLD);
        showTitle.setText(data.getTopic());

        showTime.setTypeface(showTime.getTypeface(), Typeface.ITALIC);
        showTime.setText(data.getNewsDateLabel());

        showDetails.setText(Html.fromHtml(data.getDetail()));
        //imageShow = (ImageView) findViewById(R.id.imgshow1);

        viewPager = (ClickableViewPager) findViewById(R.id.viewPager);

        ImageViewPagerAdapterNewsDetails viewPagerAdapter = new ImageViewPagerAdapterNewsDetails(this, data.getImageList());

        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);

        wormDotsIndicator.setViewPager(viewPager);

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Bundle img = new Bundle();
                Intent lookzoom = new Intent(NewsDetailsTrainee.this, ViewPagerZoomableDepthFirstNewsDetails.class);
//                for (int i = 0; i < data.getImageList().size(); i++){
//                    ImageList imageAdd = new ImageList();
//                    images.add(imageAdd.getAttachFileName());
//                }
//                img.putStringArrayList("imgshowzoom", images);
//
//                lookzoom.putExtras(img);
                lookzoom.putStringArrayListExtra("imgshowzoom", images);
                startActivity(lookzoom);
            }
        });

//        showDetails.setMovementMethod(new ScrollingMovementMethod());
    }

    private void loadPlaceID() {
        String location = data.getLatitude() + "," + data.getLongitude();
        int radius = 1000;
        String googleAPIKey = "AIzaSyDmsGBWypGKL1YKdCefsVGOghvP8Vudcs8";


        Call<PlaceIDModel> call = HttpManagerGooglePlaceID.getInstance().getService().repos(location, radius, googleAPIKey);
        call.enqueue(new Callback<PlaceIDModel>() {
            @Override
            public void onResponse(Call<PlaceIDModel> call, retrofit2.Response<PlaceIDModel> response) {
                if (response.isSuccessful()) {
                    PlaceIDModel dao = response.body();

                    String placeID = dao.getResults().get(0).getPlaceId();
                    Log.d("placeID", placeID);

                    loadPlaceDetails(placeID);


                } else {
//                    textView.setText(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PlaceIDModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
//                dialogLoadData.dismiss();

            }
        });
    }

    private void loadPlaceDetails(String placeID) {


        String googleAPIKey = "AIzaSyDmsGBWypGKL1YKdCefsVGOghvP8Vudcs8";


        Call<PlaceDetailModel> call = HttpManagerGooglePlaceDetail.getInstance().getService().repos(placeID, googleAPIKey);
        call.enqueue(new Callback<PlaceDetailModel>() {
            @Override
            public void onResponse(Call<PlaceDetailModel> call, retrofit2.Response<PlaceDetailModel> response) {
                if (response.isSuccessful()) {
                    PlaceDetailModel dao = response.body();
                    showLocation.setText(dao.getResult().getFormattedAddress());

                } else {
//                    textView.setText(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PlaceDetailModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
//                dialogLoadData.dismiss();

            }
        });
    }



}
