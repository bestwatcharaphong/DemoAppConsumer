package com.promptnow.qrpayment.consumer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.fragment.SelectCardTypeFragment;

public class AddCardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, new SelectCardTypeFragment())
                    .commit();
        }
        initInstance();

    }

    private void initInstance() {

        toolbar = findViewById(R.id.toolBar);
        btnBack = toolbar.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
