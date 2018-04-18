package com.earthsilen.depthfirsttraineeproject.ImageZoomFromDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.earthsilen.depthfirsttraineeproject.ClickableViewPager;
import com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter.ImageViewPagerAdapterImageZoom;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.R;
import com.earthsilen.depthfirsttraineeproject.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerZoomableTraineeNewsDetails extends AppCompatActivity {

    ViewPagerFixed viewPager;
    ArrayList<String> images = new ArrayList<String>();
    ClickableViewPager viewPagerClicked;
    ImageList imageList;
    List<ImageList> imageListShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_zoomable_trainee_news_details);

        Bundle extras = getIntent().getExtras();
        images = extras.getStringArrayList("imgshowzoom");

//        Intent i = getIntent();
//        imageList = (ImageList) i.getSerializableExtra("imgshowzoom");

        imageListShow.add(imageList);


        viewPager = (ViewPagerFixed) findViewById(R.id.viewpagerzoom);
        //viewPagerClicked = (ClickableViewPager)findViewById(R.id.viewpagerzoom);

        ImageButton btnAction = (ImageButton)findViewById(R.id.btn_action_train);

        ImageViewPagerAdapterImageZoom imageViewPagerAdapterImageZoom = new ImageViewPagerAdapterImageZoom(this, images,btnAction,viewPager);

        viewPager.setAdapter(imageViewPagerAdapterImageZoom);

    }
}
