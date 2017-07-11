package com.example.xiaojinggong.downloadservice.Download;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiaojinggong on 7/7/17.
 */

public class DownTask extends AsyncTask<String,Integer,Integer>{
    public static final int TYPE_SUCCESS=0;
    public static final int TYPE_FAILED=1;
    public static final int TYPE_PAUSE=2;
    public static final int TYPE_CANCLE=3;

    private DownloadInterface listener;
    private boolean isCancle =false;
    private boolean isPause=false;

    private int lastProgress;
    public DownTask(DownloadInterface listener){
        this.listener=listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream in=null;
        RandomAccessFile savefile=null;
        File file=null;
        try{
            long downloadlength=0;  //已下载长度
            String downloadurl=strings[0];
            String filename =downloadurl.substring(downloadurl.lastIndexOf("/"));
            String directory= Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS).getPath();
            file=new File(directory+filename);
            if(file.exists()){
                downloadlength=file.length();
            }
            long contentlength =getContentlength(downloadurl);
            if(contentlength==0){
                return TYPE_FAILED;
            }else if(contentlength == downloadlength){
                return TYPE_SUCCESS;
            }
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .addHeader("RANGE","bytes="+downloadlength+"-")
                    .url(downloadurl)
                    .build();
            Response responce=client.newCall(request).execute();
            if(responce !=null){
                in=responce.body().byteStream();
                savefile=new RandomAccessFile(file,"rw");
                savefile.seek(downloadlength);  //跳过已将下载的字节
                byte[] b=new byte[1024];
                int total=0;
                int len;
                while((len = in.read())!= -1){
                    if (isCancle){
                        return TYPE_CANCLE;
                    }else if(isPause){
                        return TYPE_PAUSE;
                    }else {
                        total += len;
                        savefile.write(b,0,len);
                        int progress=(int)((total+downloadlength) *100/
                        contentlength);
                        publishProgress(progress);
                    }
                }
                responce.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
                e.printStackTrace();
        }finally {
                try{
                    if(in !=null){
                        in.close();
                    }
                    if(savefile != null){
                        savefile.close();
                    }
                    if(isCancle &&file!= null){
                        file.delete();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
        }
                return TYPE_FAILED;
    }
    /*
     *在界面上显示下载进度
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
            int progress =values[0];
        if (progress > lastProgress){
            listener.OnProgress(progress);
            lastProgress=progress;
        }
    }

    /*
     *整数类型显示执行结果
     */
    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_CANCLE:
                listener.Cancled();
                break;
            case TYPE_FAILED:
                listener.Failed();
                break;
            case TYPE_PAUSE:
                listener.Paused();
                break;
            case TYPE_SUCCESS:
                listener.Success();
                break;
        }
    }

    public void cancledownload(){
            isCancle=true;
    }

    public void pausedownload(){
            isPause=true;
    }
    private long getContentlength(String downloadurl) throws IOException{
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(downloadurl).build();
        Response response=client.newCall(request).execute();
        if(response != null && response.isSuccessful()){
            long contentlength =response.body().contentLength();
            response.close();
            return  contentlength;
        }
            return 0;
    }

}
