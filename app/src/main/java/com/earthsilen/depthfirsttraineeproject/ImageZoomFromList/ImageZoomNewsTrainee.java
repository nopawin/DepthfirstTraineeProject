package com.earthsilen.depthfirsttraineeproject.ImageZoomFromList;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeNewsModels;
import com.earthsilen.depthfirsttraineeproject.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class ImageZoomNewsTrainee extends AppCompatActivity {

    String imgurl;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    ImageList imageList;

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
        setContentView(R.layout.activity_image_zoom_news_trainee);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },PERMISSION_REQUEST_CODE);

        Intent i = getIntent();
        imageList = (ImageList) i.getSerializableExtra("imgPathZ");

        PhotoView photoView = (PhotoView)findViewById(R.id.imagezoomtrainews);

        imgurl = "http://122.155.13.198/MOT_DK/download/" + imageList.getAttachFileName();
        Glide.with(this).load(imgurl).into(photoView);



        final ImageButton imageButton = (ImageButton)findViewById(R.id.btn_action_load_zoom);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(ImageZoomNewsTrainee.this, imageButton);
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
            Toast.makeText(ImageZoomNewsTrainee.this, "Downloading...", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(ImageZoomNewsTrainee.this, "Image saved", Toast.LENGTH_SHORT).show();
        }

    }

}
