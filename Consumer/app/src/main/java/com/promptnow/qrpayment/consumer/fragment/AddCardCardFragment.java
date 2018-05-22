package com.promptnow.qrpayment.consumer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.model.AccountCard;
import com.promptnow.qrpayment.consumer.model.AccountType;
import com.promptnow.qrpayment.consumer.model.TypeCard;
import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.utill.CreditCardExpiryInputFilter;
import com.promptnow.qrpayment.consumer.utill.FourDigitCardFormatWatcher;
import com.promptnow.qrpayment.consumer.utill.MyDialogUtillity;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */

public class AddCardCardFragment extends Fragment {

    private TypeCard type;
    private TextView tvType;
    private EditText editCardNo;
    private EditText editCardDate;
    private EditText editCVV;
    private EditText editCardName;
    private EditText editAuth;
    private Button btnADD;
    private RelativeLayout dateLayout;
    private RelativeLayout cvvLayout;
    private RelativeLayout authLayout;

    public AddCardCardFragment() {
        // Required empty public constructor
    }

    public static AddCardCardFragment newInstance(TypeCard type) {
        AddCardCardFragment fragment = new AddCardCardFragment();
        Bundle args = new Bundle();
        args.putParcelable("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getParcelable("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_card_card, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {

        tvType = rootView.findViewById(R.id.tvInput);
        editCardNo = rootView.findViewById(R.id.editCardNo);
        editCardDate = rootView.findViewById(R.id.editDate);
        editCVV = rootView.findViewById(R.id.editCVV);
        editCardName = rootView.findViewById(R.id.editCardName);
        editAuth = rootView.findViewById(R.id.editAuth);
        btnADD = rootView.findViewById(R.id.btnAdd);
        dateLayout = rootView.findViewById(R.id.dateLayout);
        cvvLayout = rootView.findViewById(R.id.cvvLayout);
        authLayout = rootView.findViewById(R.id.authLayout);

        editCardNo.addTextChangedListener(new FourDigitCardFormatWatcher());
        editCardDate.setFilters(new InputFilter[]{new CreditCardExpiryInputFilter()});

        switch (type.getTypeCard()) {
            case JCC:
                dateLayout.setVisibility(View.GONE);
                cvvLayout.setVisibility(View.GONE);
                authLayout.setVisibility(View.VISIBLE);
                break;
            case SMCC:
                break;
            case MIZUHO:
                break;
        }

        initData();

    }

    private void initData() {

        switch (type.getTypeCard()) {
            case JCC:
                tvType.setText("Input Card JCB Premo Info");
                break;
            case SMCC:
                tvType.setText("Input Card SMCC Info");
                break;
            case MIZUHO:
                tvType.setText("Input Card MIZUHO Info");
                break;
        }

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
            }
        });

    }

    private void addCard() {

        MyDialogUtillity dialog = new MyDialogUtillity();
        final String cardNo = editCardNo.getText().toString();
        final String cardDate = editCardDate.getText().toString();
        final String cardCVV = editCVV.getText().toString();
        final String cardName = editCardName.getText().toString();
        final String auth = editAuth.getText().toString();

        if (!isCheckAddCard(cardNo, cardDate, cardCVV, cardName, auth)) {
            dialog.customDialog(getActivity(), getString(R.string.add_card_success), new MyDialogUtillity.OnDialogClickListener() {
                @Override
                public void onDialogClickListener(AlertDialog dialog) {
                    if (type.getTypeCard() == CardType.SMCC){
                        AccountCard accountCard = new AccountCard();
                        accountCard.setAccountCradNo(cardNo);
                        accountCard.setAccountCardName(cardName);
                        accountCard.setCardType(type.getTypeCard());
                        accountCard.setAccountCardDate(cardDate);
                        accountCard.setType(AccountType.USER_TYPE);
                        Intent intentData = new Intent();
                        intentData.putExtra("addCard", Parcels.wrap(accountCard));
                        Log.d("Account Card ", String.valueOf(accountCard.getAccountCradNo()));
                        getActivity().setResult(Activity.RESULT_OK, intentData);
                        getActivity().finish();
                    }else if (type.getTypeCard() == CardType.JCC || type.getTypeCard() == CardType.MIZUHO){
                        AccountCard accountCard = new AccountCard();
                        accountCard.setAccountCradNo(cardNo);
                        accountCard.setAccountCardName(cardName);
                        accountCard.setAuthentication(Integer.parseInt(auth));
                        accountCard.setAccountCardBalance(50);
                        accountCard.setType(AccountType.USER_TYPE);
                        accountCard.setCardType(type.getTypeCard());
                        Intent intent = new Intent();
                        intent.putExtra("addCard", Parcels.wrap(accountCard));
                        Log.d("Account Card ", String.valueOf(accountCard.getAccountCradNo()));
                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();
                    }
                    dialog.dismiss();
                }
            });
        }

    }

    private boolean isCheckAddCard(String cardNo, String cardDate, String cardCVV, String cardName, String auth) {

        final MyDialogUtillity dialog = new MyDialogUtillity();

        if (type.getTypeCard() == CardType.JCC){
            if (cardNo.isEmpty()){
                showErrorDialog(dialog);
                return true;
            }
            if (cardNo.length() < 19){
                showErrorDialog(dialog);
                return true;
            }
            if (cardName.isEmpty()){
                showErrorDialog(dialog);
                return true;
            }
            if (auth.isEmpty()){
                showErrorDialog(dialog);
                return true;
            }

        }else if (type.getTypeCard() == CardType.SMCC || type.getTypeCard() == CardType.MIZUHO){

            if (cardNo.isEmpty()){
                showErrorDialog(dialog);
                return true;
            }
            if (cardNo.length() < 19){
                showErrorDialog(dialog);
                return true;
            }

            if (cardDate.isEmpty()){
                showErrorDialog(dialog);
                return true;
            }
            if (cardCVV.isEmpty()){
                showErrorDialog(dialog);
                return true;
            }
            if (cardName.isEmpty()){
                showErrorDialog(dialog);
                return true;
            }

        }
        return false;
    }

    private void showErrorDialog(final MyDialogUtillity dialog) {
        dialog.defaultDialog(getActivity(), getString(R.string.error_message),
                getString(R.string.user_has_add_card_fail_completely),
                getString(R.string.please_try_to_add_card_again),
                new MyDialogUtillity.OnDialogClickListener() {
                    @Override
                    public void onDialogClickListener(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                }, null, null, null);
    }

}
