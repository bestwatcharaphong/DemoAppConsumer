package com.promptnow.qrpayment.consumer.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.adapter.CardAdapter;
import com.promptnow.qrpayment.consumer.model.AccountCard;
import com.promptnow.qrpayment.consumer.model.AccountType;
import com.promptnow.qrpayment.consumer.model.TransactionModel;
import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.utill.MoneyTextView;
import com.promptnow.qrpayment.consumer.view.CustomButonHiostory;
import com.promptnow.qrpayment.consumer.view.CustomButtonQR;
import com.promptnow.qrpayment.consumer.view.CustomButtonScanQR;

import org.parceler.Parcels;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_ADD_CARD = 230;
    public static final int REQUEST_REMOVE_CARD = 320;
    public static final int REQUEST_SCAN_AND_GEN_QR = 635;

    private Toolbar toolbar;
    private CustomButonHiostory btnHistory;
    private ViewPager viewPager;
    private TextView tvCardName;
    private TextView tvCardNo;
    private CustomButtonQR btnGenQR;
    private CustomButtonScanQR btnScanQR;
    private MoneyTextView tvCardBalance;
    private RelativeLayout accountLayout;
    private LinearLayout btnLayout;
    private ArrayList<AccountCard> accountList = new ArrayList<>();
    private ArrayList<String> keyAccount = new ArrayList<>();
    private AccountCard accountCard;
    private CardAdapter adapter;
    private int lastPage;
    private boolean scroollChange = false;
    private AccountCard accountCardModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();

    }

    private void initInstance() {

        toolbar = findViewById(R.id.toolBar);
        btnHistory = toolbar.findViewById(R.id.btnHistory);
        viewPager = findViewById(R.id.viewPager);
        tvCardName = findViewById(R.id.tvCardName);
        tvCardNo = findViewById(R.id.tvCardNo);
        tvCardBalance = findViewById(R.id.tvCardBalance);
        accountLayout = findViewById(R.id.accountLayout);
        btnGenQR = findViewById(R.id.btnGenQR);
        btnScanQR = findViewById(R.id.btnScanQR);
        btnLayout = findViewById(R.id.btnLayout);
        btnHistory.setOnClickListener(this);
        btnGenQR.setOnClickListener(this);
        btnScanQR.setOnClickListener(this);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32,
                getResources().getDisplayMetrics());
        viewPager.setPadding(padding, 0, padding, 0);
        initData();

    }

    private void initData() {

        AccountCard accountCard1 = new AccountCard();
        accountCard1.setAccountCardName("Natjira Honda");
        accountCard1.setAccountCradNo("1234123412341234");
        accountCard1.setAccountCardBalance(7000000);
        accountCard1.setType(AccountType.USER_TYPE);
        accountCard1.setCardType(CardType.JCC);
        accountCard1.setAuthentication(422);

        accountList.add(accountCard1);
        keyAccount.add(accountCard1.getAccountCradNo());

        AccountCard accountCard2 = new AccountCard();
        accountCard2.setType(AccountType.ADD_CARD);

        accountList.add(accountCard2);
        keyAccount.add(accountCard2.getAccountCradNo());

        adapter = new CardAdapter(getSupportFragmentManager(), accountList);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tvCardName.setText(accountList.get(0).getAccountCardName());
        tvCardNo.setText("xxxx-xxxx-xxxx-" + accountList.get(0).getAccountCradNo().substring(accountList
                .get(0).getAccountCradNo().length() - 4));
        tvCardBalance.setText(String.valueOf(accountList.get(0).getAccountCardBalance()));
        accountCardModel = accountList.get(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

                if (lastPage<position && accountList.get(position).getType() == AccountType.ADD_CARD && scroollChange == false) {
                    scroollChange = true;
                    Animation anim = new AlphaAnimation(1.0f, 0.0f);
                    anim.setDuration(400);
                    Animation animBtnLayout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_top);
                    accountLayout.setAnimation(anim);
                    accountLayout.setVisibility(View.GONE);
                    btnLayout.startAnimation(animBtnLayout);
                    btnLayout.setVisibility(View.GONE);
                } else if (lastPage > position && accountList.get(position).getType() == AccountType.USER_TYPE && scroollChange == true) {
                    scroollChange = false;
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(400);
                    Animation animBtnLayout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.from_botton);
                    tvCardName.setText(accountList.get(position).getAccountCardName());
                    tvCardNo.setText("xxxx-xxxx-xxxx-" + accountList.get(position).getAccountCradNo()
                            .substring(accountList
                                    .get(position).getAccountCradNo().length() - 4));
                    tvCardBalance.setText(String.valueOf(accountList.get(position).getAccountCardBalance()));
                    accountLayout.setAnimation(anim);
                    accountLayout.setVisibility(View.VISIBLE);
                    btnLayout.startAnimation(animBtnLayout);
                    btnLayout.setVisibility(View.VISIBLE);
                    accountCardModel = accountList.get(position);
                }else if (lastPage > position && accountList.get(position).getType() == AccountType.USER_TYPE && scroollChange == false){
                    tvCardName.setText(accountList.get(position).getAccountCardName());
                    tvCardNo.setText("xxxx-xxxx-xxxx-" + accountList.get(position).getAccountCradNo()
                            .substring(accountList
                                    .get(position).getAccountCradNo().length() - 4));
                    tvCardBalance.setText(String.valueOf(accountList.get(position).getAccountCardBalance()));
                    accountCardModel = accountList.get(position);
                }else if (lastPage < position && accountList.get(position).getType() == AccountType.USER_TYPE && scroollChange == false){
                    tvCardName.setText(accountList.get(position).getAccountCardName());
                    tvCardNo.setText("xxxx-xxxx-xxxx-" + accountList.get(position).getAccountCradNo()
                            .substring(accountList
                                    .get(position).getAccountCradNo().length() - 4));
                    tvCardBalance.setText(String.valueOf(accountList.get(position).getAccountCardBalance()));
                    accountCardModel = accountList.get(position);
                }
                lastPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHistory:
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.btnGenQR:
                Intent genQRIntent = new Intent(MainActivity.this, GenQRActivity.class);
                genQRIntent.putExtra("cardType", Parcels.wrap(accountCardModel.getCardType()));
                startActivityForResult(genQRIntent, REQUEST_SCAN_AND_GEN_QR);
                break;
            case R.id.btnScanQR:
                Intent scanQRIntent = new Intent(MainActivity.this, ScanQRActivity.class);
                startActivityForResult(scanQRIntent, REQUEST_SCAN_AND_GEN_QR);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ADD_CARD:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        accountCard = Parcels.unwrap(data.getParcelableExtra("addCard"));
                        accountList.add(0, accountCard);
                        keyAccount.add(0, accountCard.getAccountCradNo());
                        adapter.notifyDataSetChanged();
                        viewPager.setCurrentItem(0);
                        scroollChange = false;
                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(400);
                        Animation animBtnLayout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.from_botton);
                        accountLayout.setAnimation(anim);
                        accountLayout.setVisibility(View.VISIBLE);
                        btnLayout.startAnimation(animBtnLayout);
                        btnLayout.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case REQUEST_REMOVE_CARD:
                if (resultCode == RESULT_OK) {
                    accountCard = Parcels.unwrap(data.getParcelableExtra("Card"));
                    String key = accountCard.getAccountCradNo();
                    int index = keyAccount.indexOf(key);
                    accountList.remove(index);
                    keyAccount.remove(index);
                    adapter.notifyDataSetChanged();
                    if (accountList.get(0).getType() == AccountType.USER_TYPE) {
                        tvCardName.setText(accountList.get(0).getAccountCardName());
                        tvCardNo.setText("xxxx-xxxx-xxxx-" + accountList.get(0).getAccountCradNo().substring(accountList
                                .get(0).getAccountCradNo().length() - 4));
                        tvCardBalance.setText(String.valueOf(accountList.get(0).getAccountCardBalance()));
                    } else if (accountList.get(0).getType() == AccountType.ADD_CARD) {
                        Animation anim = new AlphaAnimation(1.0f, 0.0f);
                        anim.setDuration(400);
                        Animation animBtnLayout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_top);
                        accountLayout.setAnimation(anim);
                        accountLayout.setVisibility(View.GONE);
                        btnLayout.startAnimation(animBtnLayout);
                        btnLayout.setVisibility(View.GONE);
                    }
                }
                break;
            case REQUEST_SCAN_AND_GEN_QR:
                if (resultCode == RESULT_OK) {
                    TransactionModel transaction = Parcels.unwrap(data.getParcelableExtra("Transaction"));
                    Intent intent = new Intent(MainActivity.this, TransnationSuccessActivity.class);
                    intent.putExtra("Transaction", Parcels.wrap(transaction));
                    startActivity(intent);
                }
                break;
        }
    }
}
