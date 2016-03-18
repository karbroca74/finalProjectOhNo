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
public class MessageBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageboard);
    }

    public void goToSearch(View v) {
        Intent searchIntent = new Intent(MessageBoardActivity.this, SearchActivity.class);
        startActivity(searchIntent);
    }

    public void goToHelp(View v) {
        Intent helpIntent = new Intent(MessageBoardActivity.this, HelpActivity.class);
        startActivity(helpIntent);
    }

    public void logout(View v) {
        Intent backToMain = new Intent(MessageBoardActivity.this, MainActivity.class);
        backToMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(backToMain);
    }
}


