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
import com.attors.examcorner.databinding.GenralBinding;
import com.attors.examcorner.databinding.MycourseBinding;

public class Material extends Fragment implements View.OnClickListener {
    MycourseBinding mycourseBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mycourseBinding= DataBindingUtil.inflate(inflater, R.layout.mycourse,container,false);

        return mycourseBinding.getRoot();
    }

    public void updateclick(){
    }



    @Override
    public void onClick(View v) {


    }
}
