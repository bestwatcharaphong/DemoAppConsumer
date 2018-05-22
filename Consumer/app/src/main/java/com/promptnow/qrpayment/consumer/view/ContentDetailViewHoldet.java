package com.promptnow.qrpayment.consumer.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.adapter.OnMyItemClickListener;
import com.promptnow.qrpayment.consumer.utill.MoneyTextView;

/**
 * Created by Suriya on 9/3/2561.
 */

public class ContentDetailViewHoldet extends RecyclerView.ViewHolder{

    public TextView tvContent;
    public MoneyTextView tvMoney;
    public RelativeLayout contentLayout;

    private SparseBooleanArray selectedItem = new SparseBooleanArray();

    public ContentDetailViewHoldet(final View itemView, final OnMyItemClickListener onItemClick){
        super(itemView);
        tvContent = itemView.findViewById(R.id.tvContent);
        tvMoney = itemView.findViewById(R.id.tvMoney);
        contentLayout = itemView.findViewById(R.id.contentLayout);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION){
                    onItemClick.onItemClick(position);
                }
            }
        });

    }

}
