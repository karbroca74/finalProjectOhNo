package com.karrye.meetsession;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
    public void goToQuestionnaire(View v){
        Intent questionnaireIntent = new Intent(MainActivity.this,QuestionnaireActivity.class);
        startActivity(questionnaireIntent);
    }
    public void goToProfile(View v){
        Intent profileIntent = new Intent(MainActivity.this,TabActivity.class);
        startActivity(profileIntent);
    }
    public void goToSearch(View v){
        Intent searchIntent = new Intent()
    }


}
