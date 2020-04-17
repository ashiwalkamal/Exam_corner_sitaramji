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
import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.Modal.PostModal;
import com.attors.examcorner.Network.Apis;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.GenralBinding;
import com.attors.examcorner.databinding.PostfragBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class General extends Fragment implements View.OnClickListener {
    GenralBinding genralBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        genralBinding= DataBindingUtil.inflate(inflater, R.layout.genral,container,false);

       /* genralBinding.names.setText(Utlity.get_user(getActivity()).getName());
        genralBinding.numbers.setText(Utlity.get_user(getActivity()).getPhn_number());
        genralBinding.emailss.setText(Utlity.get_user(getActivity()).getEmail());
        genralBinding.usenames.setText(Utlity.get_user(getActivity()).getPassword());


        */

       updateclick();

        return genralBinding.getRoot();
    }

    public void updateclick(){
        genralBinding.save.setOnClickListener(this);
    }

    private void update() {

        Utlity.show_progress(getActivity());
        MultipartBody.Builder builder = new MultipartBody.Builder();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        builder.setType(MultipartBody.FORM);

        //builder.addFormDataPart("id", Utlity.get_user(getActivity()).getId());
        builder.addFormDataPart("name",genralBinding.names.getText().toString());

        MultipartBody body = builder.build();

        final Request request = new Request.Builder()
                .url(Apis.update)
                .post(body)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.retryOnConnectionFailure();
        okHttpClient.connectTimeoutMillis();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Utlity.dismiss_dilog(getActivity());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Utlity.dismiss_dilog(getActivity());
                            String apidata = response.body().string();
                            Log.d("responce>>>>>",apidata);
                            JSONObject object = new JSONObject(apidata);
                            if (object.getInt("")== 1){
                                Utlity.show_toast(getActivity(),object.getString(""));
                                Utlity.user_db(getActivity(),object.getJSONObject("").toString());

                            }
                            else {
                                Utlity.show_toast(getActivity(),object.getString(""));
                            }
                        } catch (IOException e){
                            e.printStackTrace();
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.save){
            if (Utlity.is_online(getActivity())){
                //update();

            }
            else {
                Utlity.show_toast(getActivity(),getResources().getString(R.string.nointernet));
            }
        }
    }
}
