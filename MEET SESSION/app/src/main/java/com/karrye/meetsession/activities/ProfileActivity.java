package com.karrye.meetsession.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.karrye.meetsession.C;
import com.karrye.meetsession.R;
import com.karrye.meetsession.net.ServerResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by student on 2016-02-17.
 */
public class ProfileActivity extends AppCompatActivity implements ServerResponse{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_aboutme);
    }
    public void goToAboutDate(View v) {
        Intent aboutMeIntent = new Intent(ProfileActivity.this, TabActivity.class);
        aboutMeIntent.putExtra(TabActivity.START_TAB, 1);
        startActivity(aboutMeIntent);

    }
    public void gotResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("RESPONSE",response);
                try{
                    JSONObject json = new JSONObject(response);
                    if(json.getBoolean("success")){
                        SharedPreferences.Editor editor = getSharedPreferences(C.PREFS, Context.MODE_PRIVATE).edit();
                        editor.putInt(C.PREF_USER_ID,json.getInt(C.PREF_USER_ID));
                        editor.commit();

                        Intent aboutMeIntent = new Intent(ProfileActivity.this,TabActivity.class);
                        aboutMeIntent.putExtra(TabActivity.START_TAB,0);
                        startActivity(aboutMeIntent);
                    }
                }catch(JSONException ex){}
            }
        });
    }
}
