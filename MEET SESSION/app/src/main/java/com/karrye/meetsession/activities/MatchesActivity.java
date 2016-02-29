package com.karrye.meetsession.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.karrye.meetsession.R;
import com.karrye.meetsession.adapters.SearchAdapter;
import com.karrye.meetsession.adapters.SearchResultItem;

import java.util.ArrayList;

/**
 * Created by student on 2016-02-23.
 */
public class MatchesActivity extends AppCompatActivity {
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

    public void goToHelp(View v) {
        Intent helpIntent = new Intent(MatchesActivity.this, HelpActivity.class);
        startActivity(helpIntent);
    }
    public void goToBecomeAPremiumUser(View v){
        Intent becomeAPremiumUserIntent = new Intent(MatchesActivity.this, BecomeAPremiumUserActivity.class);
        startActivity(becomeAPremiumUserIntent);
    }
}
