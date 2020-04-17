package com.attors.examcorner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivityOnboardBinding;


public class Onboard extends Activity implements View.OnClickListener {
    ActivityOnboardBinding onboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onboardBinding= DataBindingUtil.setContentView(this,R.layout.activity_onboard);

        click();

    }
    public void click(){
        onboardBinding.register.setOnClickListener(this);
        onboardBinding.login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.register){
            startActivity(new Intent(this,Register.class));
        }
        else if (v.getId()==R.id.login){
            startActivity(new Intent(this, Login.class));

        }
    }
}
