package com.attors.examcorner.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toolbar;

import com.attors.examcorner.Fragment.Purchase;
import com.attors.examcorner.Fragment.Setting;
import com.attors.examcorner.Fragment.Timetable;
import com.attors.examcorner.Helper.Utlity;
import com.attors.examcorner.R;
import com.attors.examcorner.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    TabWidget tabs;
    public static TabHost tabHost;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private androidx.appcompat.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        drawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        mainBinding.navview.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mainBinding.navview.setSelectedItemId(R.id.home);

      /*  tabs = (TabWidget) findViewById(android.R.id.tabs);
        try {
            setTabs();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }

       */
      mainBinding.navigate.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              int id=menuItem.getItemId();
              switch (id){
                  case R.id.downloads:
                      loadFragment1(new Download());
                      drawerLayout.closeDrawers();

                      break;

                  case R.id.mypurchaqse:
                      loadFragment1(new Purchase());
                      drawerLayout.closeDrawers();

                      break;

                  case R.id.timetable:
                      loadFragment1(new Timetable());
                      drawerLayout.closeDrawers();

                      break;

                  case R.id.setting:
                      loadFragment1(new Setting());
                      drawerLayout.closeDrawers();

                      break;

                  case R.id.share:
                      drawerLayout.closeDrawers();

                      break;

                  case R.id.rate:
                      drawerLayout.closeDrawers();

                      break;

                  case R.id.Help:
                      loadFragment1(new Help());
                      drawerLayout.closeDrawers();

                      break;

                  case R.id.logout:
                      Utlity.Logout(MainActivity.this);
                      drawerLayout.closeDrawers();

                      break;
              }
              return false;
          }
      });


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.home:
                    loadFragment(new Home(), getResources().getString(R.string.home));
                    return true;

                case R.id.download:
                    loadFragment(new Download(),getResources().getString(R.string.download));
                    return true;

                case R.id.help:
                    loadFragment(new Help(),getResources().getString(R.string.Helps));
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("WrongConstant")
    public  void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment, tag);
        try {
            transaction.commit();
        } catch( IllegalStateException e ) {
            transaction.commitAllowingStateLoss();
        }

        try {
            getSupportActionBar().setTitle(tag);
            getSupportActionBar().setTitle(tag);

        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

    }


    public  void loadFragment1(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment);
        try {
            transaction.commit();
        } catch( IllegalStateException e ) {
            transaction.commitAllowingStateLoss();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }






   /* private void setTabs()
    {
        addTab("Home",R.drawable.home, Home.class);
        addTab("Downloads",R.drawable.downloads,Download.class);
        addTab("Help",R.drawable.help,Help.class);

    }

    private void addTab(String labelId, int drawableId, Class<?> c)
    {
        tabHost = getTabHost();
        Intent intent = new Intent(MainActivity.this, c);
        intent.putExtra("header", this.getClass().getName());
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);
        View tabIndicator =  LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
        TextView title = (TextView) tabIndicator.findViewById(R.id.tab_title);
        title.setText(labelId);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.tab_icon);
        icon.setImageResource(drawableId);
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);


        tabHost.addTab(spec);
    }

    public static TabHost getCurrentTabHost() {
        return tabHost;
    }

    */


}
