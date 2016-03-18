package com.karrye.meetsession.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by student on 2016-02-17.
 */
public class RegisterActivity extends AppCompatActivity implements ServerResponse{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View v) {
        EditText firstName = (EditText) findViewById(R.id.txtFirst);
        EditText lastName = (EditText) findViewById(R.id.txtLast);
        EditText email = (EditText) findViewById(R.id.txtEmail);
        EditText password = (EditText) findViewById(R.id.txtPassword);
        //EditText confirmPassword = (EditText) findViewById(R.id.txtConfirm);

        PostRequest register = new PostRequest(RegisterActivity.this);
        register.appendData("action", "register");
        register.appendData("first_name", firstName.getText().toString());
        register.appendData("last_name", lastName.getText().toString());
        register.appendData("email", email.getText().toString());
        register.appendData("password", password.getText().toString());
        register.execute();
    }
    @Override
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

                        Intent aboutMeIntent = new Intent(RegisterActivity.this,TabActivity.class);
                        aboutMeIntent.putExtra(TabActivity.START_TAB,0);
                        startActivity(aboutMeIntent);
                    }
                }catch(JSONException ex){}
            }
        });
    }
    
}
