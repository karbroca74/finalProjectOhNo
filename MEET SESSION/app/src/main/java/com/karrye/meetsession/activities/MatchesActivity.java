package com.karrye.meetsession.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.karrye.meetsession.C;
import com.karrye.meetsession.R;
import com.karrye.meetsession.adapters.SearchAdapter;
import com.karrye.meetsession.adapters.SearchResultItem;
import com.karrye.meetsession.net.ServerResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by student on 2016-02-23.
 */
public class MatchesActivity extends AppCompatActivity implements ServerResponse{
    private ArrayList<SearchResultItem> searchResults;
    private ListView searchList;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        searchResults = new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences(C.PREFS, Context.MODE_PRIVATE);

        try{
            JSONArray results = new JSONArray(prefs.getString(C.PREF_RESULTS,"[]"));
            for(int i=0; i<results.length(); i++){
                JSONObject result = results.getJSONObject(i);
                int userId = result.getInt("user_id");
                String myWords = result.getString("my_words");
                String city = result.getString("city");
                int age = result.getInt("my_age");
                searchResults.add(new SearchResultItem(userId,myWords,city,age));
            }
        }catch (JSONException ex){
            Log.e("JSON-PARSE",ex.getMessage());
            ex.printStackTrace();
        }


        searchList = (ListView)findViewById(R.id.listResults);
        searchAdapter = new SearchAdapter(MatchesActivity.this,searchResults);
        searchList.setAdapter(searchAdapter);
    }
    public void gotResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("RESPONSE",response);
                try{
                    JSONObject json = new JSONObject(response);
                    if(json.getBoolean("success")){
                        SharedPreferences.Editor editor = getSharedPreferences(C.PREFS, Context.MODE_PRIVATE).edit();
                        editor.putInt(C.PREF_USER_ID,json.getInt(C.PREF_USER_ID));
                        editor.commit();

                        Intent aboutMeIntent = new Intent(MatchesActivity.this,TabActivity.class);
                        aboutMeIntent.putExtra(TabActivity.START_TAB,0);
                        startActivity(aboutMeIntent);
                    }
                }catch(JSONException ex){}
            }
        });
    }
    public void goToHelp(View v) {
        Intent helpIntent = new Intent(MatchesActivity.this, HelpActivity.class);
        startActivity(helpIntent);
    }
    public void goToBecomeAPremiumUser(View v){
        Intent becomeAPremiumUserIntent = new Intent(MatchesActivity.this, BecomeAPremiumUserActivity.class);
        startActivity(becomeAPremiumUserIntent);
    }
    public void logout(View v){
        Intent backToMain = new Intent(MatchesActivity.this,MainActivity.class);
        backToMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(backToMain);
    }
}
