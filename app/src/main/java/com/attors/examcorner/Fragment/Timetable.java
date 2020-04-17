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
import com.attors.examcorner.databinding.TestfragBinding;
import com.attors.examcorner.databinding.TimetableBinding;

public class Timetable extends Fragment {
    TimetableBinding timetableBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        timetableBinding= DataBindingUtil.inflate(inflater,R.layout.timetable,container,false);

        return timetableBinding.getRoot();

    }
}
