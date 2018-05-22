package com.promptnow.qrpayment.consumer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.adapter.CardTyprAdapter;
import com.promptnow.qrpayment.consumer.model.TypeCard;
import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.view.CustomButtonJCB;
import com.promptnow.qrpayment.consumer.view.CustomButtonSMCC;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectCardTypeFragment extends Fragment {

    //private CustomButtonSMCC btnSMCC;
    //private CustomButtonJCB btnJCB;
    private ListView listView;
    private ArrayList<TypeCard> cardList = new ArrayList<>();
    private CardTyprAdapter adapter;

    public SelectCardTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_card_type, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {

        listView = rootView.findViewById(R.id.listView);
        initData();

    }

    private void initData() {

        cardList.clear();
        cardList.add(new TypeCard(CardType.JCC, "JCB PREMO"));
        cardList.add(new TypeCard(CardType.SMCC, "SCMM"));
        cardList.add(new TypeCard(CardType.MIZUHO, "MIZUHO"));

        adapter = new CardTyprAdapter(getActivity(), cardList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, AddCardCardFragment.newInstance(cardList.get(i)))
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("AddCardType", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("AddCardType", "onStop");
    }

    private void showToast(String text) {

        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

    }

}
