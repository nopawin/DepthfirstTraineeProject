package com.earthsilen.depthfirsttraineeproject.ImageViewPagerAdapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.ImageList;
import com.earthsilen.depthfirsttraineeproject.R;

import java.util.ArrayList;
import java.util.List;


public class ImageViewPagerAdapterNewsDetails extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    //private ArrayList<String> images = new ArrayList<String>();
    private List<ImageList> images;
    //private ArrayList<String> imagesShow = new ArrayList<String>();
    //private List<String> images = new ArrayList<>();

    public ImageViewPagerAdapterNewsDetails(Context context, List<ImageList> images ) {
        this.context = context;
        this.images = images;
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
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_image_view_pager, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //imageView.set(images[position]);
        Glide.with(context).load("http://122.155.13.198/MOT_DK/download/"+images.get(position).getAttachFileName()).into(imageView);

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
}
