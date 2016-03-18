package com.karrye.meetsession.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.karrye.meetsession.C;
import com.karrye.meetsession.R;
import com.karrye.meetsession.net.PostRequest;
import com.karrye.meetsession.net.ServerResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by student on 2016-02-23.
 */
public class SearchActivity extends AppCompatActivity implements ServerResponse {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
    public void search(View v) {
        Spinner their_gender = (Spinner) findViewById(R.id.spinGender);
        EditText min_age = (EditText) findViewById(R.id.txtMinAge);
        EditText max_age = (EditText) findViewById(R.id.txtMaxAge);

        PostRequest search = new PostRequest(SearchActivity.this);
        search.appendData("action", "search");
        search.appendData("their_gender", their_gender.getSelectedItem().toString());
        search.appendData("min_age", min_age.getText().toString());
        search.appendData("max_age", max_age.getText().toString());
        search.execute();
    }
        @Override
        public void gotResponse(final String response){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("RESPONSE", response);
                    try{
                        JSONObject json = new JSONObject(response);
                        if(json.getBoolean("success")){
                            SharedPreferences.Editor editor=
                                    getSharedPreferences(C.PREFS, Context.MODE_PRIVATE).edit();
                            editor.putInt(C.PREF_USER_ID,json.getInt(C.PREF_USER_ID));
                            editor.commit();

                            Intent matchesIntent = new Intent(SearchActivity.this,MatchesActivity.class);
                            startActivity(matchesIntent);
                        }

                    }catch (JSONException ex){}
                }

            });
        }
        public void goToMatches(View v){
            Intent matchesIntent = new Intent(SearchActivity.this, MatchesActivity.class);
            startActivity(matchesIntent);
        }

    }


