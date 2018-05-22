package com.promptnow.qrpayment.consumer.model;

import java.util.ArrayList;

/**
 * Created by Suriya on 9/3/2561.
 */

public class PaymentDetailModel {

    String paymentDate;
    ArrayList<TransactionModel> transactionList;

    public PaymentDetailModel() {
    }

    public PaymentDetailModel(String paymentDate, ArrayList<TransactionModel> transactionList) {
        this.paymentDate = paymentDate;
        this.transactionList = transactionList;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public ArrayList<TransactionModel> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<TransactionModel> transactionList) {
        this.transactionList = transactionList;
    }
}
