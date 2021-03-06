package com.earthsilen.depthfirsttraineeproject.ImageZoomFromDetails;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.earthsilen.depthfirsttraineeproject.ClickableViewPager;
import com.earthsilen.depthfirsttraineeproject.DetailFromList.NewsDetails;
import com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter.ImageViewPagerAdapterImageZoom;
import com.earthsilen.depthfirsttraineeproject.ItemClickListener;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.R;
import com.earthsilen.depthfirsttraineeproject.ViewPagerFixed;
import com.gw.swipeback.SwipeBackLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerZoomableDepthFirstNewsDetails extends AppCompatActivity {

    ViewPagerFixed viewPager;
    ClickableViewPager viewPagerClicked;
    ArrayList<String> images = new ArrayList<String>();
    ImageList imageList;
    List<ImageList> imageListShow;

    Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_zoomable_depth_first_news_details);

        onSwipeBackToFinishActivity();
        backToFinishActivity();
        Bundle extras = getIntent().getExtras();
        images = extras.getStringArrayList("imgshowzoom");

//        Intent i = getIntent();
//        imageList = (ImageList) i.getSerializableExtra("imgshowzoom");



        //imageListShow.add(imageList);


        viewPager = (ViewPagerFixed) findViewById(R.id.viewpagerzoom);
        //viewPagerClicked = (ClickableViewPager)findViewById(R.id.viewpagerzoom);

        ImageButton actionBtn = (ImageButton)findViewById(R.id.btn_action);

        ImageViewPagerAdapterImageZoom imageViewPagerAdapterImageZoom = new ImageViewPagerAdapterImageZoom(this,images ,actionBtn, viewPager);

        viewPager.setAdapter(imageViewPagerAdapterImageZoom);



        //Log.d("raiwa2",String.valueOf(viewPager.getCurrentItem()));










    }

    private void onSwipeBackToFinishActivity(){
        SwipeBackLayout mSwipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipeBackLayout);
        mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_LEFT);
        mSwipeBackLayout.setMaskAlpha(125);
        mSwipeBackLayout.setSwipeBackFactor(0.5f);
        mSwipeBackLayout.setSwipeBackListener(new SwipeBackLayout.OnSwipeBackListener() {
            @Override
            public void onViewPositionChanged(View mView, float swipeBackFraction, float SWIPE_BACK_FACTOR) {
                finish();
            }

            @Override
            public void onViewSwipeFinished(View mView, boolean isEnd) {

            }
        });

    }

    private void backToFinishActivity(){
        TextView backBtn = (TextView)findViewById(R.id.txt_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
