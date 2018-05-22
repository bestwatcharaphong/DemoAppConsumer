package com.promptnow.qrpayment.consumer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.promptnow.qrpayment.consumer.Base.BaseDetailItem;
import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.item.ContentDetailItem;
import com.promptnow.qrpayment.consumer.item.DateDetailItem;
import com.promptnow.qrpayment.consumer.type.DetailType;
import com.promptnow.qrpayment.consumer.view.ContentDetailViewHoldet;
import com.promptnow.qrpayment.consumer.view.DateDetailViewHolder;

import java.util.ArrayList;

/**
 * Created by Suriya on 8/3/2561.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<BaseDetailItem> contentList;
    private OnMyItemClickListener onItemClick;
    private Context context;

    public void setOnItemClick(OnMyItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public MyRecyclerViewAdapter(ArrayList<BaseDetailItem> contentList, Context context) {
        this.contentList = contentList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return contentList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DetailType.TYPE_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_date_detail, parent, false);
            return new DateDetailViewHolder(view);
        } else if (viewType == DetailType.TYPE_CONTENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_content_detail, parent, false);
            return new ContentDetailViewHoldet(view, onItemClick);
        }

        throw new NullPointerException("View Type " + viewType + " doesn't match with any existing order detail type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseDetailItem contetnItemDetail = contentList.get(position);
        if (contentList.get(position).getType() == 0) {
            DateDetailViewHolder dateViewHolder = (DateDetailViewHolder) holder;
            DateDetailItem dateItem = (DateDetailItem) contetnItemDetail;
            setUpDate(dateViewHolder, dateItem);
        } else if (contentList.get(position).getType() == 1) {
            ContentDetailViewHoldet contentHolder = (ContentDetailViewHoldet) holder;
            ContentDetailItem contentItem = (ContentDetailItem) contetnItemDetail;
            setUpContent(contentHolder, contentItem, position);
        }
    }

    @Override
    public int getItemCount() {
        if (contentList == null) {
            return 0;
        }
        if (contentList.size() == 0) {
            return 0;
        }
        return contentList.size();
    }

    private void setUpDate(DateDetailViewHolder dateViewHolder, DateDetailItem dateItem) {
        dateViewHolder.tvDate.setText(dateItem.getDate());
    }

    private void setUpContent(ContentDetailViewHoldet contentHolder, ContentDetailItem contentItem, final int position) {
        if (contentItem.getAmount() < 0) {
            contentHolder.tvContent.setTextColor(context.getResources().getColor(R.color.red));
            contentHolder.tvContent.setText(contentItem.getMerchantName());
            contentHolder.tvMoney.setTextColor(context.getResources().getColor(R.color.red));
            contentHolder.tvMoney.setText(String.valueOf(contentItem.getAmount()));
        } else {
            contentHolder.tvContent.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            contentHolder.tvContent.setText(contentItem.getMerchantName());
            contentHolder.tvMoney.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            contentHolder.tvMoney.setText(String.valueOf(contentItem.getAmount()));
        }

    }

}
