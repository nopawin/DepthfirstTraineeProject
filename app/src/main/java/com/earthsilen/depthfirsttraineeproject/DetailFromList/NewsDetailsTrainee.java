package com.earthsilen.depthfirsttraineeproject.DetailFromList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.earthsilen.depthfirsttraineeproject.ClickableViewPager;
import com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter.ImageViewPagerAdapterNewsDetails;
import com.earthsilen.depthfirsttraineeproject.ImageZoomFromDetails.ViewPagerZoomableDepthFirstNewsDetails;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeNewsModels;
import com.earthsilen.depthfirsttraineeproject.R;

import java.util.ArrayList;

public class NewsDetailsTrainee extends AppCompatActivity {


    ClickableViewPager viewPager;
    TextView showDetails;

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
        showDetails.setText(Html.fromHtml(data.getDetail()));
        //imageShow = (ImageView) findViewById(R.id.imgshow1);

        viewPager = (ClickableViewPager) findViewById(R.id.viewPager);

        ImageViewPagerAdapterNewsDetails viewPagerAdapter = new ImageViewPagerAdapterNewsDetails(this, data.getImageList());

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


}
