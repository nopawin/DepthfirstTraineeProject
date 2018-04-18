package com.earthsilen.depthfirsttraineeproject.ImageZoomFromList;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter.ImageViewPagerAdapterImageZoom;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.R;
import com.earthsilen.depthfirsttraineeproject.SaveImageHelper;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import dmax.dialog.SpotsDialog;


public class ImageZoom extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 1000;
    String url = "http://www.lovedesigner.net/api/get_posts?include=id,title,thumbnail";
    ImageView imageView;
    int imgPosition;
    String imgurl;
    ViewPager viewPager;
    ArrayList<String> images = new ArrayList<String>();
    ImageList imageList;
    AlertDialog dialog1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom);
//        Bundle extras = getIntent().getExtras();
//        imgPosition = extras.getInt("imgPathPos");

//        initView();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },PERMISSION_REQUEST_CODE);



        Intent i = getIntent();
        imageList = (ImageList) i.getSerializableExtra("imgPathZ");

//        images = depthfirstNewsModels.getImgurl();
//
//        viewPager = (ViewPager) findViewById(viewPagerImageZoom);
//
//        ImageViewPagerAdapterImageZoom viewPagerAdapter = new ImageViewPagerAdapterImageZoom(this, images);
//
//        viewPager.setAdapter(viewPagerAdapter);


        PhotoView photoView = (PhotoView) findViewById(R.id.imagezoomdethnews);

        imgurl = "http://122.155.13.198/MOT_DK/download/" + imageList.getAttachFileName();

        Glide.with(this).load(imgurl).into(photoView);

//        Button loadImg = (Button) findViewById(R.id.loadimg);

        final ImageButton imageButton = (ImageButton)findViewById(R.id.btn_action_load_zoom);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(ImageZoom.this, imageButton);
                dropDownMenu.getMenuInflater().inflate(R.menu.load_img, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.save_img:
                                new DownloadFileFromURL().execute(imgurl);
                                return true;

                        }
                        //Toast.makeText(context, "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu.show();

            }

        });
    }



    //Inner class
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//        System.out.println("Starting download");
            Toast.makeText(ImageZoom.this, "Downloading...", Toast.LENGTH_SHORT).show();

        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {

                File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                            + "/Depthfirst");

                Boolean success = true;
                if(!root.exists()){
                   success = root.mkdirs();
                }
//            System.out.println("Downloading");
                if(success) {
                    File imageFile = new File(root, UUID.randomUUID().toString() + ".jpg");
                    URL url = new URL(f_url[0]);

                    URLConnection conection = url.openConnection();
                    conection.connect();
                    // getting file length
                    int lenghtOfFile = conection.getContentLength();

                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);

                    // Output stream to write file

                    OutputStream output = new FileOutputStream(imageFile);
                    byte data[] = new byte[1024];

                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        total += count;

                        // writing data to file
                        output.write(data, 0, count);

                    }

                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();

                }
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }



        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            //System.out.println("Downloaded");
            Toast.makeText(ImageZoom.this, "Image saved", Toast.LENGTH_SHORT).show();
        }

    }

}
