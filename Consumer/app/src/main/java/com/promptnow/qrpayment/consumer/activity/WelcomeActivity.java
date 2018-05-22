package com.promptnow.qrpayment.consumer.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.promptnow.qrpayment.consumer.R;

public class WelcomeActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentMain = new Intent(WelcomeActivity.this, MainActivity.class);
                WelcomeActivity.this.startActivity(intentMain);
                WelcomeActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
