package com.example.xiaojinggong.downloadservice.Welcome;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.VideoView;

/**
 * Created by xiaojinggong on 7/7/17.
 */

public class CustomVedio extends VideoView {
    public CustomVedio(Context context) {
        super(context);
    }

    public CustomVedio(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVedio(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getDefaultSize(0,widthMeasureSpec);
        int height=getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        super.setOnPreparedListener(l);
    }
}

