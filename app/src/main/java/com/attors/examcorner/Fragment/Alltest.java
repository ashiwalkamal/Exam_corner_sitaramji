package com.attors.examcorner.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.attors.examcorner.Adapter.PostAdapter;
import com.attors.examcorner.Adapter.Testadpter;
import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.Modal.PostModal;
import com.attors.examcorner.Modal.Testmiodal;
import com.attors.examcorner.Network.Apis;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.AlltestBinding;
import com.attors.examcorner.databinding.ComboBinding;
import com.attors.examcorner.databinding.GenralBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Alltest extends Fragment implements View.OnClickListener {
    AlltestBinding alltestBinding;
    private Testadpter testadpter;
    private List<Testmiodal> testmiodals;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        alltestBinding= DataBindingUtil.inflate(inflater, R.layout.alltest,container,false);

        testmiodals=new ArrayList<>();
        testmiodals.add(new Testmiodal(R.drawable.ic_boy,"Up police is previous question paper","2 free tests", "0 premium tests"));
        testmiodals.add(new Testmiodal(R.drawable.ic_boy,"upsc () beo","1 free tests","3 premium tests"));

        alltestBinding.postdetail.setHasFixedSize(true);
        alltestBinding.postdetail.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        testadpter= new Testadpter(getActivity(),testmiodals);
        alltestBinding.postdetail.setAdapter(testadpter);

        return alltestBinding.getRoot();
    }

    public void updateclick(){
    }



    @Override
    public void onClick(View v) {


    }
}
