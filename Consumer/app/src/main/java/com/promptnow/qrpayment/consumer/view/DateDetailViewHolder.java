package com.promptnow.qrpayment.consumer.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;

/**
 * Created by Suriya on 9/3/2561.
 */

public class DateDetailViewHolder extends RecyclerView.ViewHolder {

    public TextView tvDate;

    public DateDetailViewHolder(View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tvDate);
    }
}
