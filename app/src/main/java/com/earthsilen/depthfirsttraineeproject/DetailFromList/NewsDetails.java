package com.earthsilen.depthfirsttraineeproject.DetailFromList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.ClickableViewPager;
import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews.TraineeNews;
import com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter.ImageViewPagerAdapterNewsDetails;
import com.earthsilen.depthfirsttraineeproject.ImageZoomFromDetails.ViewPagerZoomableDepthFirstNewsDetails;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.News;
import com.earthsilen.depthfirsttraineeproject.R;
import com.itsronald.widget.ViewPagerIndicator;


import android.view.ViewGroup.LayoutParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsDetails extends AppCompatActivity {

    String imgurl, newsDetails;
    TextView showDetails;
    ImageView imageShow;
    String url = "http://www.lovedesigner.net/api/get_posts?include=id,title,thumbnail";
    ProgressDialog dialog;
    int arrayPos;
    ClickableViewPager viewPager;
    //    String image1 = "http://www.lovedesigner.net/wp-content/uploads/2018/03/lost-in-translation-220-1200-1200-675-675-crop-000000-195x110.jpg";
//    String image2 = "http://www.lovedesigner.net/wp-content/uploads/2018/02/Man-of-Steel-2013-Movie1-e1371563991499-195x110.jpg";
//    String image3 = "http://www.lovedesigner.net/wp-content/uploads/2018/02/Secondhand-Lions-195x110.jpg";
//    String[] images;
    ArrayList<String> images = new ArrayList<String>();
    //ArrayList<String> images1 = new ArrayList<>();
    Data data;
    List<ImageList> imageListShowZoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depthfirst_news_details);

        Intent i = getIntent();
        data = (Data) i.getSerializableExtra("test");

        for (int a = 0; a < data.getImageList().size(); a++) {
            images.add(data.getImageList().get(a).getAttachFileName());
        }

//        showDetails.setText(depthfirstNewsModels.getDesc());

        //images = data.getImageList();
        initView();

//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading....");
//        dialog.show();

//        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String string) {
//                initView();
//                getInfoFromAPI(string);
//                setView();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
////                dialog.dismiss();
//            }
//        });
//
//        RequestQueue rQueue = Volley.newRequestQueue(NewsDetails.this);
//        rQueue.add(request);

//        Bundle extras = getIntent().getExtras();
//        arrayPos = extras.getInt("arraypos");


//        Intent i = getIntent();
//        DepthfirstNewsModels depthfirstNewsModels = (DepthfirstNewsModels) i.getSerializableExtra("test");
//
//        showDetails.setText(depthfirstNewsModels.getDesc());
//        //Glide.with(this).load(depthfirstNewsModels.getImgurl()).into(imageShow);
//        images = depthfirstNewsModels.getImgurl();


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
                //Bundle img = new Bundle();
                Intent lookzoom = new Intent(NewsDetails.this, ViewPagerZoomableDepthFirstNewsDetails.class);
//                for (int i = 0; i < data.getImageList().size(); i++){
//                    ImageList imageAdd = new ImageList();
//                    images.add(imageAdd.getAttachFileName());
//                }

//                imageListShowZoom = data.getImageList();

//                for (int i = 0; i < data.getImageList().size(); i++){
//                    images.add(data.getImageList().get(i).getAttachFileName());
//                }
                lookzoom.putStringArrayListExtra("imgshowzoom", images);

                //lookzoom.putExtras(img);
                startActivity(lookzoom);
            }
        });

//        showDetails.setMovementMethod(new ScrollingMovementMethod());
    }

//    private void getInfoFromAPI(String jsonString) {
//        try {
//            JSONObject a = new JSONObject(jsonString);
//            JSONArray b = a.optJSONArray("posts");
//            JSONObject c = b.optJSONObject(arrayPos);
//            imgurl = c.optString("thumbnail");
//            newsDetails = c.optString("title");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
////        dialog.dismiss();
//    }

    private void setView() {
        //DepthfirstNewsModels depthfirstNewsModels = new DepthfirstNewsModels();
//        showDetails.setText(depthfirstNewsModels.getDesc());
//        Glide.with(this).load(imgurl).into(imageShow);

    }
}
