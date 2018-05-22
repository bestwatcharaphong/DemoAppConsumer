package com.promptnow.qrpayment.consumer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.model.TransactionModel;
import com.promptnow.qrpayment.consumer.utill.MoneyTextView;

import org.parceler.Parcels;

import java.text.NumberFormat;

public class TransnationSuccessActivity extends AppCompatActivity {

    private TextView tvTranId;
    private TextView tvTranType;
    private MoneyTextView tvTotalAmount;
    private TextView tvMerName;
    private TextView tvCardType;
    private TextView tvCardNo;
    private TextView tvDate;
    private TextView tvTime;
    private Button btnOk;
    private TransactionModel contentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transnation_success);

        contentItem = Parcels.unwrap(getIntent().getParcelableExtra("Transaction"));
        initInstance();
    }

    private void initInstance() {

        tvTranId = findViewById(R.id.tvTranId);
        tvTranType = findViewById(R.id.tvTranType);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        tvMerName = findViewById(R.id.tvMerName);
        tvCardType = findViewById(R.id.tvCardType);
        tvCardNo = findViewById(R.id.tvCardNo);
        tvDate = findViewById(R.id.tvTranDate);
        tvTime = findViewById(R.id.tvTranTime);
        btnOk = findViewById(R.id.btnOK);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();

    }

    private void initData() {

        NumberFormat baseFormat = NumberFormat.getInstance();

        tvTranId.setText(String.valueOf(contentItem.getTransactionID()));
        tvTranType.setText(contentItem.getTransactionType().getName());
        double amount = Math.round(contentItem.getAmount());
        tvTotalAmount.setText(String.valueOf(amount));
        tvMerName.setText(contentItem.getMerchantName());
        tvCardType.setText(contentItem.getCardType().getName());
        tvCardNo.setText("xxxx-xxxx-xxxx-"+ contentItem.getCardNumber().substring(contentItem.getCardNumber().length() - 4));
        tvDate.setText(contentItem.getTransactionDate());
        tvTime.setText(contentItem.getTransactionTime());

    }
}
