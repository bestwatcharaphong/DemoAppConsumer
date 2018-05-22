package com.promptnow.qrpayment.consumer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.activity.AddCardActivity;
import com.promptnow.qrpayment.consumer.activity.MainActivity;
import com.promptnow.qrpayment.consumer.activity.RemoveCardActivity;
import com.promptnow.qrpayment.consumer.model.AccountCard;

import org.parceler.Parcels;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    private ImageView imgCard;
    private AccountCard accountCard;

    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance(AccountCard accountCard) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable("accountCard", Parcels.wrap(accountCard));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountCard = Parcels.unwrap(getArguments().getParcelable("accountCard"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        initInstnce(rootView);
        return rootView;
    }

    private void initInstnce(View rootView) {

        imgCard = rootView.findViewById(R.id.imgCard);
        switch (accountCard.getType()){
            case USER_TYPE:
                imgCard.setImageResource(R.drawable.ic_wallet_card1);
                imgCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent removeCardIntent = new Intent(getActivity(), RemoveCardActivity.class);
                        removeCardIntent.putExtra("Card", Parcels.wrap(accountCard));
                        Log.d("CardFragment Kyo ", String.valueOf(accountCard.getCardType()));
                        getActivity().startActivityForResult(removeCardIntent, MainActivity.REQUEST_REMOVE_CARD);
                    }
                });
                break;
            case ADD_CARD:
                imgCard.setImageResource(R.drawable.ic_wallet_card2);
                imgCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent removeCardIntent = new Intent(getActivity(), AddCardActivity.class);
                        getActivity().startActivityForResult(removeCardIntent, MainActivity.REQUEST_ADD_CARD);
                    }
                });
                break;
        }
    }

}
