package com.promptnow.qrpayment.consumer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.Base.BaseDetailItem;
import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.item.ContentDetailItem;
import com.promptnow.qrpayment.consumer.utill.MoneyTextView;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends Fragment {

    private TextView tvTranId;
    private TextView tvTranType;
    private MoneyTextView tvTotalAmount;
    private TextView tvMerName;
    private TextView tvCardType;
    private TextView tvCardNo;
    private TextView tvDate;
    private TextView tvTime;
    private ContentDetailItem contentItem;

    public TransactionFragment() {
        // Required empty public constructor
    }

    public static TransactionFragment newInstance(BaseDetailItem content){
        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putParcelable("content", Parcels.wrap(content));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentItem = Parcels.unwrap(getArguments().getParcelable("content"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_transaction, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {

        tvTranId = rootView.findViewById(R.id.tvTranId);
        tvTranType = rootView.findViewById(R.id.tvTranType);
        tvTotalAmount = rootView.findViewById(R.id.tvTotalAmount);
        tvMerName = rootView.findViewById(R.id.tvMerName);
        tvCardType = rootView.findViewById(R.id.tvCardType);
        tvCardNo = rootView.findViewById(R.id.tvCardNo);
        tvDate = rootView.findViewById(R.id.tvTranDate);
        tvTime = rootView.findViewById(R.id.tvTranTime);
        initData();

    }

    private void initData() {

        tvTranId.setText(String.valueOf(contentItem.getTransactionID()));
        tvTranType.setText(contentItem.getTransactionType().getName());
        double amount = contentItem.getAmount();
        if (amount < 0){
            tvTotalAmount.setText(String.valueOf(amount));
            tvTotalAmount.setTextColor(getResources().getColor(R.color.red));
        }else {
            tvTotalAmount.setText(String.valueOf(amount));
        }

        tvMerName.setText(contentItem.getMerchantName());
        tvCardType.setText(contentItem.getCardType().getName());
        tvCardNo.setText("xxxx-xxxx-xxxx-" + contentItem.getCardNumber().substring(contentItem.getCardNumber().length() - 4));
        tvDate.setText(contentItem.getTransactionDate());
        tvTime.setText(contentItem.getTransactionTime());
    }

    /**private String convertDate(String strDate){
        String Months[] = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep"
        }
        return null;
    }*/
}
