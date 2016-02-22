package com.karrye.meetsession;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by student on 2016-02-17.
 */
public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void goToProfile(View v){
        Intent profileIntent = new Intent(RegisterActivity.this,TabActivity.class);
        profileIntent.putExtra(TabActivity.START_TAB,0);
        startActivity(profileIntent);
    }

}
