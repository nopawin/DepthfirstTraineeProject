package com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.earthsilen.depthfirsttraineeproject.ClickableViewPager;
import com.earthsilen.depthfirsttraineeproject.ImageZoomFromList.ImageZoom;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.R;
import com.earthsilen.depthfirsttraineeproject.ViewPagerFixed;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ImageViewPagerAdapterImageZoom extends PagerAdapter  {

    private Context context;
    private LayoutInflater layoutInflater;
   // private List<ImageList> images;
    ImageButton loadImg;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    private PopupMenu mPopupMenu;
    private ViewPagerFixed viewPagerFixed;
    private int posForLoad;

    //private ArrayList<String> imagesShow = new ArrayList<String>();
    private List<String> images = new ArrayList<>();

    public ImageViewPagerAdapterImageZoom(Context context, List<String> images, ImageButton loadImg, ViewPagerFixed viewPagerFixed) {
        this.context = context;
        this.images = images;
        this.loadImg = loadImg;
        this.viewPagerFixed = viewPagerFixed;


    }


    @Override
    public int getCount() {
        return images.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_image_view_pager_zoomable, null);
        //View view1 = layoutInflater.inflate(R.layout.activity_view_pager_zoomable_depth_first_news_details, null);
        //ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //imageView.set(images[position]);

//        PhotoView photoView = (PhotoView)view.getContext();
        PhotoView photoView = new PhotoView(container.getContext());
        photoView = (PhotoView) view.findViewById(R.id.imageView1);
        //Glide.with(context).load(depthfirstNewsModels.getImgurl()).into(photoView);
        Glide.with(context).load("http://122.155.13.198/MOT_DK/download/" + images.get(position)).into(photoView);

        //final ImageButton loadImageBut = (ImageButton)view1.findViewById(R.id.btn_action);

        viewPagerFixed.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageViewPagerAdapterImageZoom.this.posForLoad = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        loadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(context, loadImg);
                dropDownMenu.getMenuInflater().inflate(R.menu.load_img, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.save_img:
                                new DownloadFileFromURL().execute("http://122.155.13.198/MOT_DK/download/" + images.get(posForLoad));
                                Log.d("raiwa", "http://122.155.13.198/MOT_DK/download/" + images.get(posForLoad));
                                Log.d("raiwa", String.valueOf(posForLoad));
                                return true;

                        }
                        //Toast.makeText(context, "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu.show();

            }
        });


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }




    //Inner class
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//        System.out.println("Starting download");
            Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show();

        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {

                File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                        + "/Depthfirst");

                Boolean success = true;
                if (!root.exists()) {
                    success = root.mkdirs();
                }
//            System.out.println("Downloading");
                if (success) {
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
         **/
        @Override
        protected void onPostExecute(String file_url) {
            //System.out.println("Downloaded");
            Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show();
        }

    }


}
