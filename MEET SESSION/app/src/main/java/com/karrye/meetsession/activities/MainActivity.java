package com.karrye.meetsession.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.karrye.meetsession.R;
import com.karrye.meetsession.net.PostRequest;
import com.karrye.meetsession.net.ServerResponse;

public class MainActivity extends AppCompatActivity implements ServerResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToRegister(View v){
        Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }
    @Override
    public void gotResponse(String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });
    }

    public void login(View v) {
        EditText email = (EditText)findViewById(R.id.txtPassword);
        EditText password = (EditText)findViewById(R.id.txtPassword);

        PostRequest login = new PostRequest(MainActivity.this);
        login.appendData("email", email.getText().toString());
        login.appendData("password", password.getText().toString());
        login.execute();
    }
}
