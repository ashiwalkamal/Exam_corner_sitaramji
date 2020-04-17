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

import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.Modal.Validation_custome;
import com.attors.examcorner.Network.Apis;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.PostfragBinding;
import com.attors.examcorner.databinding.SequrityBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Sequrity extends Fragment implements View.OnClickListener {
    SequrityBinding sequrityBinding;
    ArrayList<Validation_custome> fileds;
    String pass,conpass;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sequrityBinding= DataBindingUtil.inflate(inflater, R.layout.sequrity,container,false);

        click();
        return sequrityBinding.getRoot();
    }

    public void click(){
        sequrityBinding.change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        pass =sequrityBinding.newpass.getText().toString().trim();
        conpass =sequrityBinding.confirmpass.getText().toString().trim();
        if (v.getId()==R.id.change){

            fileds= new ArrayList<>();
            fileds.add(new Validation_custome("text",sequrityBinding.currentpass));
            fileds.add(new Validation_custome("text",sequrityBinding.newpass));

            if (!conpass.equals(pass)) {
                sequrityBinding.confirmpass.setError("enter match pass");

            }
            else{

                if (Utlity.validation(getActivity(), fileds)){

                    if (Utlity.is_online(getActivity())) {
                        //forgetPassword();
                    }
                    else
                        Utlity.show_toast(getActivity(), getResources().getString(R.string.nointernet));
                }

            }
        }

    }

    private void forgetPassword(final String sendphone, String newpassword) {
        HashMap<String, String> keys = new HashMap<>();
        keys.put("phn_number", sendphone);
        keys.put("password", newpassword);

        Request result= Utlity.post(
                getActivity(),keys, Apis.forgot);
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
                                Utlity.show_toast(getActivity(),object.getString("text"));

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
