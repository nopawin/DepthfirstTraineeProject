package com.earthsilen.depthfirsttraineeproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;


import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerContacts.SectionsPageAdapterContacts;
import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerContacts.TraineeContacts;
import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerContacts.WebContacts;
import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews.DFNews;
import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews.SectionsPageAdapterNews;
import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews.TraineeNews;

public class Contacts extends Fragment {

    View v;
    private static final String TAG = "News";
    private SectionsPageAdapterNews mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_news, container, false);

        //mSectionsPageAdapter = new SectionsPageAdapterContacts(getFragmentManager());
        mViewPager = (ViewPager) v.findViewById(R.id.containernews);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabsnews);
        tabLayout.setupWithViewPager(mViewPager);


        return v;

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapterContacts adapter = new SectionsPageAdapterContacts(getFragmentManager());
        adapter.addFragment(new WebContacts(), "Web");
        adapter.addFragment(new TraineeContacts(), "Trainee");
        viewPager.setAdapter(adapter);
    }
}

