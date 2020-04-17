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
import com.attors.examcorner.databinding.PurchaseBinding;
import com.attors.examcorner.databinding.TestfragBinding;

import java.util.ArrayList;
import java.util.List;

public class Purchase extends Fragment {
    PurchaseBinding purchaseBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        purchaseBinding= DataBindingUtil.inflate(inflater,R.layout.purchase,container,false);

        return purchaseBinding.getRoot();

    }
}
