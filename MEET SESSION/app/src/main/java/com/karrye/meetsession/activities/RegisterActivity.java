package com.karrye.meetsession.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karrye.meetsession.R;
import com.karrye.meetsession.net.PostRequest;
import com.karrye.meetsession.net.ServerResponse;

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
        EditText email = (EditText) findViewById(R.id.txtPassword);
        EditText password = (EditText) findViewById(R.id.txtPassword);
        EditText confirmPassword = (EditText) findViewById(R.id.txtConfirm);

        PostRequest register = new PostRequest(RegisterActivity.this);
        register.appendData("first_name", firstName.getText().toString());

        register.appendData("last_name", lastName.getText().toString());
        register.appendData("email", email.getText().toString());
        register.appendData("password", password.getText().toString());
        register.appendData("confirm_password", confirmPassword.getText().toString());
        register.execute();
    }
    @Override
    public void gotResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent aboutMeIntent = new Intent(RegisterActivity.this,TabActivity.class);
                aboutMeIntent.putExtra(TabActivity.START_TAB,0);
                startActivity(aboutMeIntent);
            }
        });
    }
}
