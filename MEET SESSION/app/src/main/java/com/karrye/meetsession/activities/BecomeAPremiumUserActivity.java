package com.karrye.meetsession.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karrye.meetsession.R;

/**
 * Created by student on 2016-02-24.
 */
public class BecomeAPremiumUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_becomeapremiumuser);
    }
    public void goToMessageBoard(View v){
        Intent messageBoardIntent = new Intent(BecomeAPremiumUserActivity.this,MessageBoardActivity.class);
        startActivity(messageBoardIntent);
    }
}
