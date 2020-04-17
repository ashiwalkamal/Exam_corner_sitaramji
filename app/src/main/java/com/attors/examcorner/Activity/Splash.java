package com.attors.examcorner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivitySplashBinding;

public class Splash extends Activity {

    ActivitySplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding= DataBindingUtil.setContentView(this,R.layout.activity_splash);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(0b101110111000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                   /* if(!TextUtils.isEmpty(Utlity.get_user(Splash.this).getId())) {
                        startActivity(new Intent(Splash.this, Help.class));
                    }
                    else
                    {
                        startActivity(new Intent(Splash.this, Login.class));
                    }

                    */


                    startActivity(new Intent(Splash.this, Onboard.class));
                    finishAffinity();

                }

            }
        };
        thread.start();
    }

}
