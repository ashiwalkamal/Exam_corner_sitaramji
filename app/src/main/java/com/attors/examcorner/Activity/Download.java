package com.attors.examcorner.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attors.examcorner.Adapter.Viewadapter;
import com.attors.examcorner.Adapter.Viewadapter1;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivityDownloadBinding;

public class Download extends Fragment {
    ActivityDownloadBinding downloadBinding;
    Viewadapter1 viewadapter1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        downloadBinding=DataBindingUtil.inflate(inflater,R.layout.activity_download,container,false);

        viewadapter1 = new Viewadapter1(getChildFragmentManager());
        downloadBinding.viewpagr1.setAdapter(viewadapter1);
        downloadBinding.tablayout1.setupWithViewPager(downloadBinding.viewpagr1);

        return downloadBinding.getRoot();
    }
}
