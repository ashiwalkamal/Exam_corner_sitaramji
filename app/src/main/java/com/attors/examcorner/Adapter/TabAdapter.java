package com.attors.examcorner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.attors.examcorner.Activity.PaidCourse;
import com.attors.examcorner.Modal.TabModal;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.TabitemBinding;

import java.util.List;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.Myview> {
    LayoutInflater layoutInflater;
    Context context;
    List<TabModal> tabModal;

    public TabAdapter(Context context, List<TabModal> tabModal) {
        this.context = context;
        this.tabModal = tabModal;
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater ==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        TabitemBinding tabitemBinding= DataBindingUtil.inflate(layoutInflater, R.layout.tabitem,parent,false);
        return new Myview(tabitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Myview holder, int position) {
        final TabModal tabModal1=tabModal.get(position);
        holder.tabitemBinding.img.setImageResource(tabModal.get(position).getImg());
        holder.tabitemBinding.texts.setText(tabModal.get(position).getText());

        if (position==0){

            holder.tabitemBinding.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, PaidCourse.class));
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return tabModal.size();
    }

    public class Myview extends RecyclerView.ViewHolder {
       private final TabitemBinding tabitemBinding;
        public Myview(TabitemBinding tabitemBinding) {
            super(tabitemBinding.getRoot());
            this.tabitemBinding = tabitemBinding;
        }
    }
}
