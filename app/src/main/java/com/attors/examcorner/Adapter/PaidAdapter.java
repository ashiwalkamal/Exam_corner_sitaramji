package com.attors.examcorner.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.attors.examcorner.Fragment.Subjectwise;
import com.attors.examcorner.Fragment.Alltest;
import com.attors.examcorner.Fragment.Material;


public class PaidAdapter extends FragmentPagerAdapter {

    public PaidAdapter(FragmentManager fm) {
        super(fm);

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        if (position ==0){
            fragment=new Alltest();

        }

        else if (position==1){
            fragment=new Subjectwise();
        }
        else if (position==2){
            fragment=new Material();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title =null;
        if (position==0){
            title="All test";
        }
        else if (position==1){
            title="Subject wise";
        }
        else if (position==2){
            title="Material";
        }
        return title;
    }
}
