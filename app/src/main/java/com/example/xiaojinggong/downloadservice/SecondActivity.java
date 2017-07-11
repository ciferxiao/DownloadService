package com.example.xiaojinggong.downloadservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiaojinggong.downloadservice.Activity.BaseActivity;

/**
 * Created by xiaojinggong on 7/10/17.
 */

public class SecondActivity extends BaseActivity {
    public static String CAR_NAME="car_name";
    public static String CAR_Image_ID="car_image_id";

    private CollapsibleActionView collapsibleActionView;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sec);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collbar);
        Intent intent=getIntent();
       // String carName=intent.getStringExtra(CAR_NAME);   通过intent传递的信息
        //int carId=intent.getIntExtra(CAR_Image_ID,0);     现在用不到，直接作假
        String carName="法拉利430";
        int carId=R.drawable.car;
        textView=(TextView)findViewById(R.id.cardtext);
        imageView=(ImageView)findViewById(R.id.image);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(carName);
        Glide.with(this).load(carId).into(imageView);
        //String carContent=generateCarContent(carName);
        String carContent=ContentCar();
        textView.setTextSize(30);
        textView.setText(carContent);
    }

    private String generateCarContent(String carName){
        StringBuilder carContent=new StringBuilder();
        for(int i=0;i<500;i++){
            textView.append(carName);
        }
        return carContent.toString();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String ContentCar(){
        return " 2004年全新的V8车型F430诞生，其在技术方面相比360又有了进一步的升级，比如：装备上了全球首次采用的“E-Diff”电子差速器；使用了F1式的方向盘，上面设置了安装了多个电子控制按钮，根据不同的路况对车辆的行驶稳定性进行调节，集中管理车辆动态特性。此外无论从外观还是内饰上来说，法拉利F430浑身都透露了360 Modena的影子，但F430身上却装备了更加先进的设备，可以称得上是360的改进版。  F430内饰也被重新设计，驾驶者与汽车更加协调。为了便于操作，设计师将所有主要控制装置都集中布置在驾驶者前面，各种仪表集中在新的仪表盘内，这些设计与仪表板的布局彰显了设计师所倾注的心力。这种毫不妥协的设计理念同样体现在安装在方向盘上的起动按钮和Manettino驾驶模式选择开关上。";

    }
}
