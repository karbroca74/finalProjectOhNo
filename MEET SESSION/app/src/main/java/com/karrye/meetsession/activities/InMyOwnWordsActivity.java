package com.karrye.meetsession.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by student on 2016-02-23.
 */
public class InMyOwnWordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void goToMatches(View v){
        Intent matchesIntent = new Intent(InMyOwnWordsActivity.this,MatchesActivity.class);
        startActivity(matchesIntent);
    }
}
