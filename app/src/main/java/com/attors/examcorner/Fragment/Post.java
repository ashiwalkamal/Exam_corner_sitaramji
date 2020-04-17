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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.attors.examcorner.Adapter.PostAdapter;
import com.attors.examcorner.Adapter.TabAdapter;
import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.Modal.PostModal;
import com.attors.examcorner.Modal.TabModal;
import com.attors.examcorner.Network.Apis;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.PostfragBinding;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Post extends Fragment {
    PostfragBinding postfragBinding;
    private PostAdapter postAdapter;
    private List<PostModal> postModal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postfragBinding= DataBindingUtil.inflate(inflater, R.layout.postfrag,container,false);

        postModal=new ArrayList<>();
        postModal.add(new PostModal(R.drawable.ic_boy,"Cources","about course all detail available"));
        postModal.add(new PostModal(R.drawable.ic_boy,"Free","about free course deatils"));

        postfragBinding.postdetail.setHasFixedSize(true);
        postfragBinding.postdetail.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        postAdapter= new PostAdapter(getActivity(),postModal);
        postfragBinding.postdetail.setAdapter(postAdapter);

        if (Utlity.is_online(getActivity())){

            //postdeatil();
        }
        else {

            Utlity.show_toast(getActivity(),getResources().getString(R.string.nointernet));
        }

            return postfragBinding.getRoot();
    }

    private void postdeatil() {
        HashMap<String, String> keys = new HashMap<>();
       // keys.put("id",Utlity.get_user(getActivity()).getId());

        Request result= Utlity.post(getActivity(), keys, Apis.postdetail);
        OkHttpClient okHttpClient= new OkHttpClient();
        okHttpClient.newCall(result).enqueue(new Callback() {
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
                            if (object.getString("success").equalsIgnoreCase("1")){
                                postModal = Utlity.gson.fromJson(object.getJSONArray("detail").toString(), new TypeToken<List<PostModal>>() {}.getType());
                                postfragBinding.postdetail.setHasFixedSize(true);
                                postfragBinding.postdetail.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                                postAdapter= new PostAdapter(getActivity(),postModal);
                                postfragBinding.postdetail.setAdapter(postAdapter);

                            }
                            else {
                                Utlity.show_toast(getActivity(),object.getString("text"));
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

}
