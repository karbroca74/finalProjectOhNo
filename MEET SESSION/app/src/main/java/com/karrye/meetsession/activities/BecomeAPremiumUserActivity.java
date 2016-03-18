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
 //https://www.paypalobjects.com/en_US/i/btn/btn_buynowCC_LG.gif
// https://www.paypalobjects.com/en_US/i/scr/pixel.gif

//https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&business=3S2MNJJWGEYY2&lc=CA&item_name=Premium%20Membership&amount=19%2e99&currency_code=CAD&button_subtype=services&tax_rate=0%2e000&shipping=0%2e00&bn=PP%2dBuyNowBF%3abtn_buynowCC_LG%2egif%3aNonHosted