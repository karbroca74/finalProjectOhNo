package com.karrye.meetsession.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karrye.meetsession.R;

/**
 * Created by student on 2016-02-17.
 */
public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_aboutme);
    }
    public void goToAboutMe(View v) {
        Intent aboutMeIntent = new Intent(ProfileActivity.this,TabActivity.class);
        aboutMeIntent.putExtra(TabActivity.START_TAB, 1);
        startActivity(aboutMeIntent);

    }
}