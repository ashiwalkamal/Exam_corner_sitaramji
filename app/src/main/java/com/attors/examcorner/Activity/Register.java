package com.attors.examcorner.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.Modal.Validation_custome;
import com.attors.examcorner.Network.Apis;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivityRegisterBinding;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity  implements View.OnClickListener {
    ActivityRegisterBinding registerBinding;
    ArrayList<Validation_custome> fileds;
    String tocken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        //tocken = FirebaseInstanceId.getInstance().getToken();

        click();
    }
    public void click(){
        registerBinding.register.setOnClickListener(this);
        registerBinding.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.register){

            fileds= new ArrayList<>();
            fileds.add(new Validation_custome("text",registerBinding.name));
            fileds.add(new Validation_custome("text",registerBinding.email));
            fileds.add(new Validation_custome("text",registerBinding.password));
            fileds.add(new Validation_custome("text",registerBinding.username));
            fileds.add(new Validation_custome("text",registerBinding.phone));

            if (Utlity.validation(this, fileds)){

                if (Utlity.is_online(this)) {
                  //  signup();
                }
                else
                    Utlity.show_toast(this, getResources().getString(R.string.nointernet));
            }

        }
        else if (v.getId()==R.id.back){
            startActivity(new Intent(this,Onboard.class));
        }
    }

    public void signup() {

        Utlity.show_progress(Register.this);
        MultipartBody.Builder builder = new MultipartBody.Builder();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        builder.setType(MultipartBody.FORM);


        //builder.addFormDataPart("user_id", Utlity.get_user(this).getId());
        builder.addFormDataPart("", registerBinding.name.getText().toString());
        builder.addFormDataPart("", registerBinding.email.getText().toString());
        builder.addFormDataPart("", registerBinding.phone.getText().toString());
        builder.addFormDataPart("", registerBinding.password.getText().toString());
        builder.addFormDataPart("", registerBinding.username.getText().toString());
        builder.addFormDataPart("", tocken);


        MultipartBody body = builder.build();

        final Request request = new Request.Builder()
                .url(Apis.register)
                .post(body)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.retryOnConnectionFailure();
        okHttpClient.connectTimeoutMillis();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Utlity.dismiss_dilog(Register.this);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, final @NonNull Response response) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        JSONObject object = null;
                        try {
                            Utlity.dismiss_dilog(Register.this);
                            object = new JSONObject(response.body().string());
                            //Log.d("responce>>>>",response.body().string());
                            if (object.getString("success").equalsIgnoreCase("1")) {
                                Utlity.user_db(Register.this,object.getJSONObject("").toString());
                                startActivity(new Intent(Register.this, Help.class));
                                finish();
                            } else {
                                Utlity.show_toast(Register.this, object.getString("text"));
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
