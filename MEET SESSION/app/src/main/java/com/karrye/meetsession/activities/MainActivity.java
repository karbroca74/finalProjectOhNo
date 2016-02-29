package com.karrye.meetsession.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.karrye.meetsession.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToRegister(View v){
        Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }

    public void goToSearch(View v){
        Intent searchIntent = new Intent(MainActivity.this,SearchActivity.class);
        startActivity(searchIntent);
    }


}
