package com.karrye.meetsession.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.karrye.meetsession.C;
import com.karrye.meetsession.PagerFragment;
import com.karrye.meetsession.R;
import com.karrye.meetsession.net.PostRequest;
import com.karrye.meetsession.net.ServerResponse;

public class TabActivity extends AppCompatActivity implements ServerResponse{
    public static final String START_TAB = "START TAB";
    private TabPagerAdapter pagerAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.tabPager);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) { }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) { }
        };
        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < TabPagerAdapter.getTabCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(TabPagerAdapter.getTabName(i))
                            .setTabListener(tabListener));
        }
        try{
            int startTab = getIntent().getExtras().getInt(START_TAB);
            goToTab(startTab);
        }catch (Exception ex){}

    }
    private void goToTab(int tabNumber){
        getSupportActionBar().setSelectedNavigationItem(tabNumber);
        viewPager.setCurrentItem(tabNumber);
    }
    public void goToAboutDate(View v){
        goToTab(1);
    }
    public void goToSummary(View v){
        goToTab(2);
    }
    public void goToSearch(View v){
        goToTab(0);
        EditText city =(EditText)findViewById(R.id.txtCity);
        Spinner my_gender =(Spinner) findViewById(R.id.spinGender);
        Spinner my_status =(Spinner) findViewById(R.id.spinStatus);
        EditText my_height =(EditText)findViewById(R.id.txtHeight);
        EditText my_age =(EditText)findViewById(R.id.txtMyAge);
        EditText longest_relationship =(EditText)findViewById(R.id.txtRelationship);
        EditText my_likes =(EditText)findViewById(R.id.txtEnjoy);
        EditText my_partner_characteristics =(EditText)findViewById(R.id.txtCharacteristics);
        goToTab(1);
        EditText his_body_type =(EditText)findViewById(R.id.txtBody);
        EditText his_height =(EditText)findViewById(R.id.txtHisHeight);
        EditText his_ethnicity =(EditText)findViewById(R.id.txtEthnicity);
        EditText his_status =(EditText)findViewById(R.id.txtStatus);
        EditText his_age =(EditText)findViewById(R.id.txtAge);
        goToTab(2);
        EditText my_words =(EditText)findViewById(R.id.txtWords);


        SharedPreferences prefs = getSharedPreferences(C.PREFS, Context.MODE_PRIVATE);
        PostRequest personalInfo = new PostRequest(TabActivity.this);

        personalInfo.appendData("action", "personalInfo");
        personalInfo.appendData("user_id", String.valueOf(prefs.getInt(C.PREF_USER_ID,0)));

        personalInfo.appendData("city", city.getText().toString());
        personalInfo.appendData("my_gender", my_gender.getSelectedItem().toString());
        personalInfo.appendData("my_status", my_status.getSelectedItem().toString());
        personalInfo.appendData("my_height", my_height.getText().toString());
        personalInfo.appendData("my_age", my_age.getText().toString());
        personalInfo.appendData("longest_relationship", longest_relationship.getText().toString());
        personalInfo.appendData("my_likes", my_likes.getText().toString());
        personalInfo.appendData("my_partner_characteristics", my_partner_characteristics.getText().toString());

        personalInfo.appendData("his_body_type", his_body_type.getText().toString());
        personalInfo.appendData("his_height", his_height.getText().toString());
        personalInfo.appendData("his_ethnicity", his_ethnicity.getText().toString());
        personalInfo.appendData("his_status", his_status.getText().toString());
        personalInfo.appendData("his_age", his_age.getText().toString());

        personalInfo.appendData("my_words", my_words.getText().toString());

        personalInfo.execute();
    }
    @Override
    public void gotResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("RESPONSE",response);
                if(response.trim().equals("success")){
                    Intent searchIntent = new Intent(TabActivity.this,SearchActivity.class);
                    startActivity(searchIntent);
                }


            }
        });
    }
}

class TabPagerAdapter extends FragmentPagerAdapter{
    private static int[] tabLayoutIds = {
            R.layout.fragment_aboutme,
            R.layout.fragment_aboutdate,
            R.layout.fragment_inmyownwords
    };

    private static String[] tabNames = {
            "About Me",
            "About My Date",
            "In my own words"
    };


    public static int getTabCount() {
        return tabLayoutIds.length;
    }

    public static String getTabName(int index) {
        return tabNames[index];
    }
    public TabPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt(PagerFragment.EXTRA_LAYOUT_ID, tabLayoutIds[position]);
        Fragment pageFragment = new PagerFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return getTabCount();
    }
}
