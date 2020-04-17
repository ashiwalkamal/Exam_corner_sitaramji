package com.attors.examcorner.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.attors.examcorner.Adapter.TabAdapter;
import com.attors.examcorner.Modal.TabModal;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.TestfragBinding;

import java.util.ArrayList;
import java.util.List;

public class Test extends Fragment {
    TestfragBinding testfragBinding;
    private TabAdapter tabAdapter;
    private List<TabModal> tabModals;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        testfragBinding= DataBindingUtil.inflate(inflater,R.layout.testfrag,container,false);

        tabModals=new ArrayList<>();
        tabModals.add(new TabModal(R.drawable.ic_notification,"Paid Courses"));
        tabModals.add(new TabModal(R.drawable.ic_notification,"Free Courses"));
        tabModals.add(new TabModal(R.drawable.ic_notification,"Test Series"));
        tabModals.add(new TabModal(R.drawable.ic_notification,"Study Material"));
        tabModals.add(new TabModal(R.drawable.ic_notification,"Current Affairs"));
        tabModals.add(new TabModal(R.drawable.ic_notification,"Daily Quiz"));
        tabModals.add(new TabModal(R.drawable.ic_notification,"Job Alerts"));
        tabModals.add(new TabModal(R.drawable.ic_notification,"Exampur Notes"));

        testfragBinding.tabs.setHasFixedSize(true);
        testfragBinding.tabs.setLayoutManager(new GridLayoutManager(getActivity(),2, RecyclerView.VERTICAL,false));
        tabAdapter= new TabAdapter(getActivity(),tabModals);
        testfragBinding.tabs.setAdapter(tabAdapter);
        return testfragBinding.getRoot();

    }
}
