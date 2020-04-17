package com.attors.examcorner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.attors.examcorner.Modal.PostModal;
import com.attors.examcorner.Modal.TabModal;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.PostitemBinding;
import com.attors.examcorner.databinding.TabitemBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Myview> {
    LayoutInflater layoutInflater;
    Context context;
    List<PostModal> postModals;

    public PostAdapter(Context context, List<PostModal> postModals) {
        this.context = context;
        this.postModals = postModals;
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater ==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PostitemBinding postitemBinding= DataBindingUtil.inflate(layoutInflater, R.layout.postitem,parent,false);
        return new Myview(postitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Myview holder, int position) {
        final PostModal tabModal1=postModals.get(position);
        holder.postitemBinding.img.setImageResource(postModals.get(position).getImg());
        holder.postitemBinding.title.setText(postModals.get(position).getText1());
        holder.postitemBinding.descprition.setText(postModals.get(position).getText2());

    }

    @Override
    public int getItemCount() {
        return postModals.size();
    }

    public class Myview extends RecyclerView.ViewHolder {
       private final PostitemBinding postitemBinding;
        public Myview(PostitemBinding postitemBinding) {
            super(postitemBinding.getRoot());
            this.postitemBinding = postitemBinding;
        }
    }
}
