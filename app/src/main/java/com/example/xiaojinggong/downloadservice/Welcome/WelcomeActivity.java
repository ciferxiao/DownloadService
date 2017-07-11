package com.example.xiaojinggong.downloadservice.Welcome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.xiaojinggong.downloadservice.Activity.BaseActivity;
import com.example.xiaojinggong.downloadservice.MainActivity.MainActivity;
import com.example.xiaojinggong.downloadservice.R;

/**
 * Created by xiaojinggong on 7/7/17.
 */

public class WelcomeActivity extends BaseActivity{
    private Button button;
    private CustomVedio customVedio;
    Handler handler=new Handler();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);
        customVedio = (CustomVedio) findViewById(R.id.video);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        customVedio.setVideoURI(Uri.parse("android:resource://" + this.getPackageName() + "/" + R.raw.vedio));
/*
        customVedio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                handler.postDelayed(runnable,0);
            }
        });
    }
     Runnable runnable=new Runnable() {
         @Override
         public void run() {
             Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
             startActivity(intent);
         }
     };
*/
    }
}


