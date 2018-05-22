package com.promptnow.qrpayment.consumer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.promptnow.qrpayment.consumer.model.TypeCard;
import com.promptnow.qrpayment.consumer.view.CustomTypeCardList;

import java.util.ArrayList;

/**
 * Created by Suriya on 2/3/2561.
 */

public class CardTyprAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<TypeCard> cardList;

    public CardTyprAdapter(Context mContext, ArrayList<TypeCard> cardList) {
        this.mContext = mContext;
        this.cardList = cardList;
    }

    @Override
    public int getCount() {
        if (cardList == null) {
            return 0;
        }
        if (cardList.size() == 0){
            return 0;
        }
        return cardList.size();
    }

    @Override
    public Object getItem(int i) {
        return cardList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CustomTypeCardList item;
        if (view != null){
            item = (CustomTypeCardList) view;
        }else {
            item = new CustomTypeCardList(mContext);
        }
        TypeCard type = (TypeCard) getItem(i);
        item.setType(type.getTypeCard(), type.getCardName());
        return item;
    }
}
