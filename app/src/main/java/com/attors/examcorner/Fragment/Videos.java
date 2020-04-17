package com.attors.examcorner.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.attors.examcorner.R;
import com.attors.examcorner.databinding.VideoBinding;

public class Videos extends Fragment {

    VideoBinding videoBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        videoBinding= DataBindingUtil.inflate(inflater, R.layout.video,container,false);
        return videoBinding.getRoot();
    }
}
