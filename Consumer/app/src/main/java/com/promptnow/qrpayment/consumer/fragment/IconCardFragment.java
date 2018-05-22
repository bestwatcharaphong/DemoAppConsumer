package com.promptnow.qrpayment.consumer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.activity.AddCardActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IconCardFragment extends Fragment {

    public static final int REQUEST_ADD_CARD = 230;
    private ImageView imgAddCard;

    public IconCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_card, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {

        imgAddCard = rootView.findViewById(R.id.imgAddCard);
        imgAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCardIntent = new Intent(getActivity(), AddCardActivity.class);
                getActivity().startActivityForResult(addCardIntent, 230);
            }
        });

    }

}
