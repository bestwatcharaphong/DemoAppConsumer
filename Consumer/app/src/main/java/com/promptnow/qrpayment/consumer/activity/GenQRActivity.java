package com.promptnow.qrpayment.consumer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.model.AccountCard;
import com.promptnow.qrpayment.consumer.model.TransactionModel;
import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.type.TransactionType;
import com.promptnow.qrpayment.consumer.utill.GenQRandBar;
import com.promptnow.qrpayment.consumer.utill.MyDialogUtillity;

import org.parceler.Parcels;

public class GenQRActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener {

    private Toolbar toolbar;
    private ImageButton btnBack;
    private TextView tvCount;
    private TextView tvBarCode;
    private ImageView imgQRCode;
    private ImageView imgBarCode;
    private CountDownTimer countDownTimer;
    private CardType cardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_qr);
        cardType = Parcels.unwrap(getIntent().getParcelableExtra("cardType"));
        //accountCard = Parcels.unwrap(getIntent().getParcelableExtra("AccountCard"));
        initInstance();

    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    private void initInstance() {

        toolbar = findViewById(R.id.toolBar);
        btnBack = toolbar.findViewById(R.id.btnBack);
        imgQRCode = findViewById(R.id.imgQRCode);
        tvBarCode = findViewById(R.id.tvBarCode);
        imgBarCode = findViewById(R.id.imgBarCode);
        tvCount = findViewById(R.id.tvCount);
        imgBarCode.setClickable(false);
        imgQRCode.setClickable(false);
        btnBack.setOnClickListener(this);
        imgQRCode.setOnClickListener(this);
        imgQRCode.setOnLongClickListener(this);
        imgBarCode.setOnClickListener(this);
        imgBarCode.setOnLongClickListener(this);

        switch (cardType){
            case JCC:
                break;
            case SMCC:
                tvBarCode.setVisibility(View.GONE);
                imgBarCode.setVisibility(View.GONE);
                break;
            case MIZUHO:
                break;
        }

        initData();

    }

    private void initData() {

        String textCode = "0123456789";
        Bitmap barBitmap = null;
        Bitmap qrBitmap = null;

        try {
            barBitmap = new GenQRandBar().encodeAsBitmap(textCode, BarcodeFormat.CODE_128, 600, 300);
            qrBitmap = new GenQRandBar().encodeAsBitmap(textCode, BarcodeFormat.QR_CODE, 480, 480);
            imgBarCode.setImageBitmap(barBitmap);
            imgQRCode.setImageBitmap(qrBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        countDown();
    }

    private void countDown() {
        countDownTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                tvCount.setText("( " + l / 1000 + " )");
            }

            @Override
            public void onFinish() {

                successDialog(true);

            }
        }.start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                countDownTimer.cancel();
                break;
            case R.id.imgQRCode:
                successDialog(true);
                countDownTimer.cancel();
                break;
            case R.id.imgBarCode:
                successDialog(true);
                countDownTimer.cancel();
                break;
        }
    }

    private void successDialog(final boolean state) {
        MyDialogUtillity.defaultDialog(GenQRActivity.this, "Loading", "Merchant has sacn QR code completely",
                "Please wait for processing", null, null, null, new MyDialogUtillity.OnDialogClickListener() {
                    @Override
                    public void onDialogClickListener(final AlertDialog dialog) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (state) {
                                    TransactionModel transactionModel = new TransactionModel(158975, TransactionType.PAYMENY, +1500, "JR Pass", CardType.JCC,
                                            "4589458945894589", "2018-02-28", "12:34:00");
                                    Intent intent = new Intent();
                                    intent.putExtra("Transaction", Parcels.wrap(transactionModel));
                                    setResult(RESULT_OK, intent);
                                    dialog.dismiss();
                                    finish();
                                }else {
                                    errorDialog();
                                }
                            }
                        }, 5000);

                    }
                });
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.imgQRCode:
                successDialog(false);
                countDownTimer.cancel();
                return true;
            case R.id.imgBarCode:
                successDialog(false);
                countDownTimer.cancel();
                return true;
            default:
                return false;
        }
    }

    private void errorDialog() {
        MyDialogUtillity.defaultDialog(GenQRActivity.this, "ERROR Message", "User has add card fail",
                "Please try to add card again.", new MyDialogUtillity.OnDialogClickListener() {
                    @Override
                    public void onDialogClickListener(AlertDialog dialog) {
                        dialog.dismiss();
                        finish();
                    }
                }, null, null, null);
    }
}


