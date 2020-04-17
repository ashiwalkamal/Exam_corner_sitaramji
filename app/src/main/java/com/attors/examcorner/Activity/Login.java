package com.attors.examcorner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.Modal.Validation_custome;
import com.attors.examcorner.Network.Apis;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivityLoginBinding;
import com.google.firebase.iid.FirebaseInstanceId;

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

public class Login extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding loginBinding;
    ArrayList<Validation_custome> fileds;

    String email,password;
    String tocken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        //tocken = FirebaseInstanceId.getInstance().getToken();
        shoot();

    }

    public void shoot(){
        loginBinding.logins.setOnClickListener(this);
        loginBinding.back.setOnClickListener(this);
        loginBinding.forgot.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.logins){

            email =loginBinding.email.getText().toString();
            password=loginBinding.password.getText().toString();

                fileds = new ArrayList<>();
                fileds.add(new Validation_custome("text", loginBinding.email));
                fileds.add(new Validation_custome("text", loginBinding.password));

                if (Utlity.validation(this, fileds)) {
                    if (Utlity.is_online(this)) {
                       // dologin(email, password);

                    } else
                        Utlity.show_toast(this, getResources().getString(R.string.nointernet));
                }

        }


        else if (v.getId()==R.id.back){
            startActivity(new Intent(this,MainActivity.class));

        }

        else if (v.getId()==R.id.forgot){
            final Dialog dialog = new Dialog(Login.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialogbox_forgetpassword);
            dialog.getWindow().setLayout(ViewPager.LayoutParams.FILL_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
            final EditText editText_mobile = dialog.findViewById(R.id.emils);
            final EditText editText_forgetPassword = dialog.findViewById(R.id.editText_forget_password);
            Button buttonForgetPassword = dialog.findViewById(R.id.button_forgetPassword);
            buttonForgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String stringeditText_mobile = editText_mobile.getText().toString();
                    String stringForgetPassword = editText_forgetPassword.getText().toString();
                    if (Utlity.is_online(Login.this)) {
                        forgetPassword(stringeditText_mobile,stringForgetPassword);
                    } else {
                        Toast.makeText(Login.this, getResources().getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
        }

    }


    private void dologin(final String email, String password) {
        HashMap<String, String> keys = new HashMap<>();
        keys.put("email", email);
        keys.put("password", password);
        keys.put("firebase_tocken",tocken);

        Request result= Utlity.post(this, keys, Apis.login);
        OkHttpClient okHttpClient= new OkHttpClient();
        okHttpClient.newCall(result).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Utlity.dismiss_dilog(Login.this);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Utlity.dismiss_dilog(Login.this);
                            String apidata = response.body().string();
                            Log.d("responce>>>>>",apidata);
                            JSONObject object = new JSONObject(apidata);
                            if (object.getInt("")== 1){
                                Utlity.user_db(Login.this,object.getJSONObject("").toString());
                               // Utlity.vechile_db1(Login.this,object.getJSONObject("vechial_info").toString());
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finishAffinity();

                            }
                            else {
                                Utlity.show_toast(Login.this,object.getString(""));
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

    private void forgetPassword(final String sendemail, String newpassword) {
        HashMap<String, String> keys = new HashMap<>();
        keys.put("",sendemail);
        keys.put("", newpassword);

        Request result= Utlity.post(this,keys, Apis.forgot);
        OkHttpClient okHttpClient= new OkHttpClient();
        okHttpClient.newCall(result).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Utlity.dismiss_dilog(Login.this);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Utlity.dismiss_dilog(Login.this);
                            String apidata = response.body().string();
                            Log.d("responce>>>>>",apidata);
                            JSONObject object = new JSONObject(apidata);
                            if (object.getString("success").equalsIgnoreCase("1")){
                                Utlity.show_toast(Login.this,object.getString("text"));

                            }
                            else {
                                Utlity.show_toast(Login.this,object.getString("text"));
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
