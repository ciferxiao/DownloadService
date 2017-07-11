package com.example.xiaojinggong.downloadservice.Download;

/**
 * Created by xiaojinggong on 7/7/17.
 */

public interface DownloadInterface {
    void OnProgress(int progress);

    void Success();

    void Failed();

    void Paused();

    void Cancled();
}
