package com.promptnow.qrpayment.consumer.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.model.AccountCard;
import com.promptnow.qrpayment.consumer.utill.MoneyTextView;
import com.promptnow.qrpayment.consumer.utill.MyDialogUtillity;

import org.parceler.Parcels;

public class RemoveCardActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageButton btnBack;
    private TextView tvCardName;
    private TextView tvCardNo;
    private TextView tvCardType;
    private TextView tvCardDate;
    private MoneyTextView tvCardBalance;
    private TextView tvCardAuth;
    private Button btnRemove;
    private LinearLayout balanceLayout;
    private LinearLayout auhtLayout;
    private LinearLayout dateLayout;
    private AccountCard accountCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_card);

        accountCard = Parcels.unwrap(getIntent().getParcelableExtra("Card"));
        initInstance();

    }

    private void initInstance() {

        toolbar = findViewById(R.id.toolBar);
        btnBack = toolbar.findViewById(R.id.btnBack);
        tvCardName = findViewById(R.id.tvCardName);
        tvCardNo = findViewById(R.id.tvCardNo);
        tvCardType = findViewById(R.id.tvCardType);
        tvCardDate = findViewById(R.id.tvCardDate);
        tvCardBalance = findViewById(R.id.tvCardBalance);
        tvCardAuth = findViewById(R.id.tvCardAuth);
        btnRemove = findViewById(R.id.btnRemove);
        balanceLayout = findViewById(R.id.balanceLayout);
        auhtLayout = findViewById(R.id.authLayout);
        dateLayout = findViewById(R.id.dateLayout);

        btnBack.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        AccountCard accountCard = Parcels.unwrap(intent.getParcelableExtra("Card"));
        String cardNoLong = String.valueOf(accountCard.getAccountCradNo());
        Log.d("Remove Fragment Kyo", String.valueOf(accountCard.getCardType()));

        switch (accountCard.getCardType()){
            case SMCC:

                balanceLayout.setVisibility(View.GONE);
                auhtLayout.setVisibility(View.GONE);
                tvCardName.setText(accountCard.getAccountCardName());
                tvCardNo.setText("xxxx-xxxx-xxxx-" + cardNoLong
                .substring(cardNoLong.length() - 4));
                tvCardType.setText(accountCard.getCardType().getName());
                tvCardDate.setText(accountCard.getAccountCardDate());
                break;
            case JCC:
                dateLayout.setVisibility(View.GONE);
                tvCardName.setText(accountCard.getAccountCardName());
                tvCardNo.setText("xxxx-xxxx-xxxx-" + cardNoLong
                        .substring(cardNoLong.length() - 4));
                tvCardType.setText(accountCard.getCardType().getName());
                tvCardBalance.setText(String.valueOf(accountCard.getAccountCardBalance()));
                tvCardAuth.setText(String.valueOf(accountCard.getAuthentication()));
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnRemove:
                MyDialogUtillity.defaultDialog(RemoveCardActivity.this, "Remove Card", "Are you sure to remove this card",
                        null, null, new MyDialogUtillity.OnDialogClickListener() {
                            @Override
                            public void onDialogClickListener(AlertDialog dialog) {
                                dialog.dismiss();
                            }
                        }, new MyDialogUtillity.OnDialogClickListener() {
                            @Override
                            public void onDialogClickListener(AlertDialog dialog) {
                                dialog.dismiss();
                                Intent intent = new Intent();
                                intent.putExtra("Card", Parcels.wrap(accountCard));
                                setResult(RESULT_OK, intent);
                                MyDialogUtillity.customDialog(RemoveCardActivity.this,
                                        "Remove card success", new MyDialogUtillity.OnDialogClickListener() {
                                            @Override
                                            public void onDialogClickListener(AlertDialog dialog) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        });
                            }
                        }, null);
                break;
        }
    }
}
