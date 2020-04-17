package com.attors.examcorner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ImageView;

import com.attors.examcorner.Adapter.PaidAdapter;
import com.attors.examcorner.Adapter.Viewadapter1;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivityPaidCourseBinding;

public class PaidCourse extends AppCompatActivity {

    ActivityPaidCourseBinding paidCourseBinding;
    private androidx.appcompat.widget.Toolbar toolbar;
    PaidAdapter paidAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paidCourseBinding= DataBindingUtil.setContentView(this,R.layout.activity_paid_course);
      //  toolbar = findViewById(R.id.toolbar1);
      //  setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        paidAdapter = new PaidAdapter(getSupportFragmentManager());
        paidCourseBinding.coursepager.setAdapter(paidAdapter);
        paidCourseBinding.cources.setupWithViewPager(paidCourseBinding.coursepager);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
