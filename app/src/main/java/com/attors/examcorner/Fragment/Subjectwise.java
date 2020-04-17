package com.attors.examcorner.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.AllcourseBinding;
import com.attors.examcorner.databinding.GenralBinding;

public class Subjectwise extends Fragment implements View.OnClickListener {
    AllcourseBinding allcourseBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        allcourseBinding= DataBindingUtil.inflate(inflater, R.layout.allcourse,container,false);

        return allcourseBinding.getRoot();
    }

    public void updateclick(){
    }



    @Override
    public void onClick(View v) {

    }
}
