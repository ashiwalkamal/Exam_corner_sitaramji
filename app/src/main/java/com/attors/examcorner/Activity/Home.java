package com.attors.examcorner.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.attors.examcorner.Adapter.Viewadapter;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.HomeBinding;

public class Home extends Fragment {
    HomeBinding homeBinding;
    Viewadapter viewadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        homeBinding=DataBindingUtil.inflate(inflater,R.layout.home,container,false);
        viewadapter = new Viewadapter(getChildFragmentManager());
        homeBinding.viewpagr.setAdapter(viewadapter);
        homeBinding.tablayout.setupWithViewPager(homeBinding.viewpagr);
        //homeBinding.tablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorsky));

        return homeBinding.getRoot();
    }
}
