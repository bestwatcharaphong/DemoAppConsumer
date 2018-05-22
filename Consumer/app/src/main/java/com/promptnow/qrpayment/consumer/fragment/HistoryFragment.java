package com.promptnow.qrpayment.consumer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.promptnow.qrpayment.consumer.Base.BaseDetailItem;
import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.adapter.ContentCoverter;
import com.promptnow.qrpayment.consumer.adapter.MyRecyclerViewAdapter;
import com.promptnow.qrpayment.consumer.adapter.OnMyItemClickListener;
import com.promptnow.qrpayment.consumer.adapter.SimpleDividerItemDecoration;
import com.promptnow.qrpayment.consumer.model.PaymentDetailModel;
import com.promptnow.qrpayment.consumer.model.TransactionModel;
import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.type.TransactionType;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnNext;
    private MyRecyclerViewAdapter adapter;
    private ArrayList<BaseDetailItem> contentItemList;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {

        recyclerView = rootView.findViewById(R.id.recyclerView);
        btnNext = rootView.findViewById(R.id.btnNext);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();

    }

    private void initData() {

        PaymentDetailModel payment;
        contentItemList = new ArrayList<>();
        ArrayList<PaymentDetailModel> patmentList = new ArrayList<>();
        ArrayList<TransactionModel> transactionList1 = new ArrayList<>();
        ArrayList<TransactionModel> transactionList2 = new ArrayList<>();
        ArrayList<TransactionModel> transactionList3 = new ArrayList<>();
        ArrayList<TransactionModel> transactionList4 = new ArrayList<>();
        ArrayList<TransactionModel> transactionList5 = new ArrayList<>();


        Random random = new Random();
        int tranId = (random.nextInt(200000 - 150000) + 150000);
        String cardNo = "1234123412341234";

        transactionList1.add(new TransactionModel(tranId, TransactionType.PAYMENY, -4000, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-02-28", "12:34:00"));
        transactionList1.add(new TransactionModel(tranId, TransactionType.PAYMENY, +1500, "JR Pass", CardType.JCC,
                cardNo, "2018-02-28", "12:34:00"));
        transactionList1.add(new TransactionModel(tranId, TransactionType.PAYMENY, -300, "JR Pass", CardType.JCC,
                cardNo, "2018-02-28", "12:34:00"));
        transactionList1.add(new TransactionModel(tranId, TransactionType.PAYMENY, -250, "eSPORTS", CardType.JCC,
                cardNo, "2018-02-28", "12:34:00"));

        patmentList.add(new PaymentDetailModel("2018-02-28", transactionList1));

        transactionList2.add(new TransactionModel(tranId, TransactionType.PAYMENY, -100, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-02-20", "12:34:00"));
        transactionList2.add(new TransactionModel(tranId, TransactionType.PAYMENY, +78000, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-02-20", "12:34:00"));
        transactionList2.add(new TransactionModel(tranId, TransactionType.PAYMENY, -1000, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-02-20", "12:34:00"));

        patmentList.add(new PaymentDetailModel("2018-02-20", transactionList2));

        transactionList3.add(new TransactionModel(tranId, TransactionType.PAYMENY, -700, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-02-11", "12:34:00"));
        transactionList3.add(new TransactionModel(tranId, TransactionType.PAYMENY, -10000, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-02-11", "12:34:00"));

        patmentList.add(new PaymentDetailModel("2018-02-11", transactionList3));

        transactionList4.add(new TransactionModel(tranId, TransactionType.PAYMENY, -250, "eSPORTS", CardType.JCC,
                cardNo, "2018-01-25", "12:34:00"));
        transactionList4.add(new TransactionModel(tranId, TransactionType.PAYMENY, -100, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-01-25", "12:34:00"));
        transactionList4.add(new TransactionModel(tranId, TransactionType.PAYMENY, +78000, "eSPORTS", CardType.JCC,
                cardNo, "2018-01-25", "12:34:00"));
        transactionList4.add(new TransactionModel(tranId, TransactionType.PAYMENY, -1000, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-01-25", "12:34:00"));

        patmentList.add(new PaymentDetailModel("2018-01-25", transactionList4));

        transactionList5.add(new TransactionModel(tranId, TransactionType.PAYMENY, -500, "GUNDAM Shop", CardType.JCC,
                cardNo, "2018-01-18", "12:34:00"));
        transactionList5.add(new TransactionModel(tranId, TransactionType.PAYMENY, +60000, "eSPORTS", CardType.JCC,
                cardNo, "2018-01-18", "12:34:00"));

        patmentList.add(new PaymentDetailModel("2018-01-18", transactionList5));

        contentItemList.addAll(ContentCoverter.createHeadAndContent(patmentList));

        adapter = new MyRecyclerViewAdapter(contentItemList, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("Content Holder ", "Click " + position);
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, TransactionFragment.newInstance(contentItemList.get(position)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(contentItemList.size());
            }
        });
        adapter.notifyDataSetChanged();

    }
}
