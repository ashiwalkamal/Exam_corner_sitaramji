package com.attors.examcorner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.attors.examcorner.Modal.PostModal;
import com.attors.examcorner.Modal.Testmiodal;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.PostitemBinding;
import com.attors.examcorner.databinding.TestitemBinding;

import java.util.List;

public class Testadpter extends RecyclerView.Adapter<Testadpter.Myview> {
    LayoutInflater layoutInflater;
    Context context;
    List<Testmiodal> testmiodals;

    public Testadpter(Context context, List<Testmiodal> testmiodal) {
        this.context = context;
        this.testmiodals = testmiodal;
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater ==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        TestitemBinding testitemBinding= DataBindingUtil.inflate(layoutInflater, R.layout.testitem,parent,false);
        return new Myview(testitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Myview holder, int position) {
        final Testmiodal tabModal1=testmiodals.get(position);
        holder.testitemBinding.img1.setImageResource(testmiodals.get(position).getImg());
        holder.testitemBinding.service.setText(testmiodals.get(position).getText1());
        holder.testitemBinding.premium1.setText(testmiodals.get(position).getText2());
        holder.testitemBinding.premium2.setText(testmiodals.get(position).getText3());

    }

    @Override
    public int getItemCount() {
        return testmiodals.size();
    }

    public class Myview extends RecyclerView.ViewHolder {
       private final TestitemBinding testitemBinding;
        public Myview(TestitemBinding testitemBinding) {
            super(testitemBinding.getRoot());
            this.testitemBinding = testitemBinding;
        }
    }
}
