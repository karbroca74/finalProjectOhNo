package com.karrye.meetsession;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class TabActivity extends AppCompatActivity {
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
            getSupportActionBar().setSelectedNavigationItem(startTab);
            viewPager.setCurrentItem(startTab);
        }catch (Exception ex){}

    }

}

class TabPagerAdapter extends FragmentPagerAdapter{
    private static int[] tabLayoutIds = {
            R.layout.activity_aboutme,
            R.layout.activity_aboutdate,
            R.layout.activity_premiumusers
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
