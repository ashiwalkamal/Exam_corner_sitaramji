package com.attors.examcorner.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.attors.examcorner.Fragment.General;
import com.attors.examcorner.Fragment.Post;
import com.attors.examcorner.Fragment.Sequrity;
import com.attors.examcorner.Fragment.Test;


public class SettingtabAdapter extends FragmentPagerAdapter {

    public SettingtabAdapter(FragmentManager fm) {
        super(fm);

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        if (position ==0){
            fragment=new General();

        }
        else if (position==1){
            fragment=new Sequrity();

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
            title="General";
        }
        else if (position==1){
            title="Security";
        }
        return title;
    }
}
