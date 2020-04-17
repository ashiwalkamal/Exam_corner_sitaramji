package com.attors.examcorner.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.attors.examcorner.Fragment.Pdfs;
import com.attors.examcorner.Fragment.Post;
import com.attors.examcorner.Fragment.Test;
import com.attors.examcorner.Fragment.Videos;


public class Viewadapter1 extends FragmentPagerAdapter {

    public Viewadapter1(FragmentManager fm) {
        super(fm);

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        if (position ==0){
            fragment=new Videos();

        }
        else if (position==1){
            fragment=new Pdfs();

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title =null;
        if (position==0){
            title="Videos";
        }
        else if (position==1){
            title="PDFs";
        }
        return title;
    }
}
