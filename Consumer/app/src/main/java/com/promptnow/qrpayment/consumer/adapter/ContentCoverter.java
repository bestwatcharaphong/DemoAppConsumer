package com.promptnow.qrpayment.consumer.adapter;

import com.promptnow.qrpayment.consumer.Base.BaseDetailItem;
import com.promptnow.qrpayment.consumer.item.ContentDetailItem;
import com.promptnow.qrpayment.consumer.item.DateDetailItem;
import com.promptnow.qrpayment.consumer.model.PaymentDetailModel;
import com.promptnow.qrpayment.consumer.model.TransactionModel;
import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.type.TransactionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suriya on 10/3/2561.
 */

public class ContentCoverter {

    public static List<BaseDetailItem> createHeadAndContent(ArrayList<PaymentDetailModel> payList){
        List<BaseDetailItem> contentDetailList = new ArrayList<>();
        if (payList != null && payList.size() > 0){
            for (PaymentDetailModel pay : payList){
                contentDetailList.addAll(getContentDetailList(pay.getTransactionList(), pay.getPaymentDate()));
            }
        }
        return contentDetailList;
    }

    private static List<BaseDetailItem> getContentDetailList(List<TransactionModel> transactionList, String date) {
        List<BaseDetailItem> contentItemList = new ArrayList<>();
        if (transactionList != null && transactionList.size() > 0){
            contentItemList.add(createHeader(date));
            for (TransactionModel tran : transactionList){
                int tranId = tran.getTransactionID();
                TransactionType transactionType = tran.getTransactionType();
                double tranAmount = tran.getAmount();
                String mechantName = tran.getMerchantName();
                CardType cardType = tran.getCardType();
                String cardNo = tran.getCardNumber();
                String tranDate = tran.getTransactionDate();
                String tranTime = tran.getTransactionTime();
                contentItemList.add(createContent(tranId, transactionType, tranAmount, mechantName,
                        cardType, cardNo, tranDate, tranTime));
            }
        }
        return contentItemList;
    }

    private static DateDetailItem createHeader(String date) {
        DateDetailItem dateItem = new DateDetailItem();
        dateItem.setDate(date);
        return dateItem;
    }

    private static ContentDetailItem createContent(int tranId, TransactionType transactionType, double tranAmount, String mechantName, CardType cardType, String cardNo,
                                                   String tranDate, String tranTime) {
        ContentDetailItem contentItem = new ContentDetailItem();
        contentItem.setTransactionID(tranId);
        contentItem.setTransactionType(transactionType);
        contentItem.setAmount(tranAmount);
        contentItem.setMerchantName(mechantName);
        contentItem.setCardType(cardType);
        contentItem.setCardNumber(cardNo);
        contentItem.setTransactionDate(tranDate);
        contentItem.setTransactionTime(tranTime);
        return contentItem;
    }

}
