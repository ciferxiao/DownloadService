package com.example.xiaojinggong.downloadservice.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.xiaojinggong.downloadservice.Activity.BaseActivity;
import com.example.xiaojinggong.downloadservice.R;
import com.example.xiaojinggong.downloadservice.SecondActivity;

public class MainActivity extends BaseActivity {
    private Toolbar toolbar;
    private SwipeRefreshLayout swiperefresh;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle tagle;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.hjk);
        tagle=new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,0,0
        );
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        swiperefresh=(SwipeRefreshLayout)findViewById(R.id.swipefresh);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatbutton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setToolbar();
        setNavigation();
        setfloatingbutton();
        setSwipefresh();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        tagle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setSwipefresh(){
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swiperefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void setNavigation(){
        navigationView.setCheckedItem(R.id.nav_call);   //默认选中
    }

    private void setfloatingbutton(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"xxxxxxxx",Snackbar.LENGTH_SHORT)
                        .setAction("yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.hjk);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
