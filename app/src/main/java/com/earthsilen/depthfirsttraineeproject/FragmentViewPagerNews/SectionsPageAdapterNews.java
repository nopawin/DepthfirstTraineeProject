package com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews.DFNews;
import com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews.TraineeNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 23/2/2561.
 */

public class SectionsPageAdapterNews extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();




    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public SectionsPageAdapterNews(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return DFNews.newInstance();
        else if(position == 1)
            return TraineeNews.newInstance();

        return null;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
