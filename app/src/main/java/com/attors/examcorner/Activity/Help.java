package com.attors.examcorner.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.Modal.Validation_custome;
import com.attors.examcorner.Network.Apis;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivityHelpBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Help extends Fragment implements View.OnClickListener {
    ActivityHelpBinding helpBinding;
    String issue []={"Select issue","App Crashing","Exam Guidance","Study Help","Purchase Help","Other"};
    ArrayList<Validation_custome> fileds;
    String tocken;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        helpBinding=DataBindingUtil.inflate(inflater,R.layout.activity_help,container,false);

        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,issue);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        helpBinding.issue.setAdapter(aa);
        help();

        return helpBinding.getRoot();
    }

    public void help(){
        helpBinding.submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.submit){

            fileds= new ArrayList<>();
            fileds.add(new Validation_custome("text",helpBinding.name));
            fileds.add(new Validation_custome("text",helpBinding.emails));
            fileds.add(new Validation_custome("text",helpBinding.number));
            fileds.add(new Validation_custome("text",helpBinding.about));

            if (Utlity.validation(getActivity(), fileds)){

                if (Utlity.is_online(getActivity())) {
                    //  helps();
                }
                else
                    Utlity.show_toast(getActivity(), getResources().getString(R.string.nointernet));
            }

        }

    }

    public void helps() {

        Utlity.show_progress(getActivity());
        MultipartBody.Builder builder = new MultipartBody.Builder();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        builder.setType(MultipartBody.FORM);


        //builder.addFormDataPart("user_id", Utlity.get_user(this).getId());
        builder.addFormDataPart("", helpBinding.name.getText().toString());
        builder.addFormDataPart("", helpBinding.emails.getText().toString());
        builder.addFormDataPart("", helpBinding.number.getText().toString());
        builder.addFormDataPart("", helpBinding.about.getText().toString());
        builder.addFormDataPart("", tocken);


        MultipartBody body = builder.build();

        final Request request = new Request.Builder()
                .url(Apis.help)
                .post(body)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.retryOnConnectionFailure();
        okHttpClient.connectTimeoutMillis();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Utlity.dismiss_dilog(getActivity());
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, final @NonNull Response response) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        JSONObject object = null;
                        try {
                            Utlity.dismiss_dilog(getActivity());
                            object = new JSONObject(response.body().string());
                            //Log.d("responce>>>>",response.body().string());
                            if (object.getString("success").equalsIgnoreCase("1")) {
                               // Utlity.user_db(Register.this,object.getJSONObject("").toString());
                               // startActivity(new Intent(Register.this, Help.class));
                                getActivity().finish();
                            } else {
                                Utlity.show_toast(getActivity(), object.getString("text"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        });
    }

}
