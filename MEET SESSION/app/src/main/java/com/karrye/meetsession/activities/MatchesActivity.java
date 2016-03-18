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
        searchResults.add(new SearchResultItem(1,"Bob the builder",5));
        searchResults.add(new SearchResultItem(2,"Hunky Monkey",50));
        searchResults.add(new SearchResultItem(3,"Quatre Winner",25));

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
}
