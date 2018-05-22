package com.promptnow.qrpayment.consumer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.promptnow.qrpayment.consumer.fragment.IconCardFragment;
import com.promptnow.qrpayment.consumer.fragment.CardFragment;
import com.promptnow.qrpayment.consumer.model.AccountCard;
import com.promptnow.qrpayment.consumer.model.AccountType;

import java.util.ArrayList;

/**
 * Created by Suriya on 28/2/2561.
 */

public class CardAdapter extends FragmentStatePagerAdapter {

    ArrayList<AccountCard> accountList;

    public CardAdapter(FragmentManager fm, ArrayList<AccountCard> accountList) {
        super(fm);
        this.accountList = accountList;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.newInstance(accountList.get(position));
    }

    @Override
    public int getCount() {
        if (accountList == null) {
            return 0;
        }
        if (accountList.size() == 0) {
            return 0;
        }
        return accountList.size();
    }

}
