package com.karrye.meetsession.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karrye.meetsession.R;

/**
 * Created by student on 2016-02-23.
 */
public class SearchActivity extends AppCompatActivity {
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void goToViewProfiles(View v) {
        Intent searchIntent = new Intent(SearchActivity.this,MatchesActivity.class);
        startActivity(searchIntent);
    }
}
