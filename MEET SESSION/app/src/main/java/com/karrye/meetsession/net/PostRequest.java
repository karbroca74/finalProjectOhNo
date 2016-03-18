package com.karrye.meetsession.net;

import android.util.Log;

import com.karrye.meetsession.C;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016-03-02.
 */
public class PostRequest {
    private HttpPost post;
    private HttpClient client;
    private HttpResponse res;

    private ServerResponse responseHandler;
    private List<NameValuePair> sentData;

    public PostRequest(ServerResponse responseHandler_){
        this.responseHandler = responseHandler_;
        sentData = new ArrayList<>();
    }

    public void appendData(String key, String value){
        if(sentData == null){
            sentData = new ArrayList<NameValuePair>();
        }
        sentData.add(new BasicNameValuePair(key,value));
    }

    public void execute(){
        Runnable networkRunnable = new Runnable() {
            @Override
            public void run() {
                HttpParams params = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(params,10000);

                try {
                    client = new DefaultHttpClient(params);
                    post = new HttpPost(C.SERVER);
                    post.setEntity(new UrlEncodedFormEntity(sentData));
                    res = client.execute(post);
                    InputStream is = res.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        sb.append(line + "\n");
                        line = reader.readLine();
                    }
                    is.close();
                    responseHandler.gotResponse(sb.toString());
                }catch (IOException ex){
                    Log.e("PostRequest","Error doing post request");
                    ex.printStackTrace();
                }
            }
        };
        Thread networkThread = new Thread(networkRunnable);
        networkThread.start();
    }
}
