package com.karrye.meetsession.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.karrye.meetsession.C;
import com.karrye.meetsession.R;
import com.karrye.meetsession.net.PostRequest;
import com.karrye.meetsession.net.ServerResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ServerResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View v) {
        EditText email = (EditText)findViewById(R.id.txtEmail);
        EditText password = (EditText)findViewById(R.id.txtPassword);

        PostRequest login = new PostRequest(MainActivity.this);
        login.appendData("action", "login");
        login.appendData("email", email.getText().toString());
        login.appendData("password", password.getText().toString());
        login.execute();
    }
    @Override
    public void gotResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("RESPONSE", response);
                try{
                    JSONObject json = new JSONObject(response);
                    if(json.getBoolean("success")){
                        SharedPreferences.Editor editor=
                                getSharedPreferences(C.PREFS, Context.MODE_PRIVATE).edit();

                        editor.putInt(C.PREF_USER_ID,json.getInt(C.PREF_USER_ID));
                        editor.commit();

                        Intent searchIntent = new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(searchIntent);
                    }

                }catch(JSONException ex){}
            }
        });

    }
    public void goToRegister(View v){
        Intent goTo = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(goTo);
    }
}