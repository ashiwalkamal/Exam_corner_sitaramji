package com.attors.examcorner.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.attors.examcorner.Adapter.SettingtabAdapter;
import com.attors.examcorner.Adapter.Viewadapter1;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.SettingBinding;
import com.attors.examcorner.databinding.TestfragBinding;

public class Setting extends Fragment {
    SettingBinding settingBinding;
    SettingtabAdapter settingtabAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        settingBinding= DataBindingUtil.inflate(inflater,R.layout.setting,container,false);

        settingtabAdapter = new SettingtabAdapter(getChildFragmentManager());
        settingBinding.settingpager.setAdapter(settingtabAdapter);
        settingBinding.sttingtab.setupWithViewPager(settingBinding.settingpager);


        return settingBinding.getRoot();

    }
}
