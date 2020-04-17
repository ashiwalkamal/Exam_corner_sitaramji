package com.attors.examcorner.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.attors.examcorner.Fragment.Post;
import com.attors.examcorner.Fragment.Test;


public class Viewadapter extends FragmentPagerAdapter {

    public Viewadapter(FragmentManager fm) {
        super(fm);

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        if (position ==0){
            fragment=new Post();

        }
        else if (position==1){
            fragment=new Test();

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
            title="Post";
        }
        else if (position==1){
            title="Test";
        }
        return title;
    }
}
